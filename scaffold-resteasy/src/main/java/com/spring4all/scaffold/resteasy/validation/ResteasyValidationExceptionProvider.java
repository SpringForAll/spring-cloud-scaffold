package com.spring4all.scaffold.resteasy.validation;

import com.spring4all.scaffold.common.BaseErrorCode;
import com.spring4all.scaffold.common.BaseResult;

import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolation;
import javax.validation.GroupDefinitionException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author fangzhibin
 */
public class ResteasyValidationExceptionProvider implements ExceptionMapper<ValidationException> {

    private static final Logger logger = LoggerFactory.getLogger(ResteasyValidationExceptionProvider.class);

    @Override
    public Response toResponse(ValidationException e) {
        if(e instanceof ConstraintDeclarationException || e instanceof ConstraintDeclarationException || e instanceof GroupDefinitionException) {
            return buildServerErrorResponse(e);
        }
        if(e instanceof ParamValidationException) {
            return buildParamErrorResponse(e);
        }
        if(e instanceof ResteasyViolationException) {
            ResteasyViolationException resteasyViolationException = ResteasyViolationException.class.cast(e);
            Exception exception = resteasyViolationException.getException();
            if(null != exception) {
                return buildServerErrorResponse(exception);
            } else if (null != resteasyViolationException.getReturnValueViolations() && resteasyViolationException.getReturnValueViolations().size() == 0) {
                return buildViolationResponse(resteasyViolationException, Status.BAD_REQUEST);
            } else {
                return buildViolationResponse(resteasyViolationException, Status.INTERNAL_SERVER_ERROR);
            }
        }

        BaseResult<String> baseResult = new BaseResult<>(BaseResult.ERROR_TYPE, BaseErrorCode.SYSTEM_INTERNAL_ERROR.getCode(),
                BaseErrorCode.SYSTEM_INTERNAL_ERROR.getMsg());
        return ResteasyExceptionProvider.buildResponse(e, Status.INTERNAL_SERVER_ERROR, baseResult);
    }

    private static Response buildServerErrorResponse(Exception exception) {
        BaseResult<String> baseResult = new BaseResult<>(BaseResult.ERROR_TYPE, BaseErrorCode.SYSTEM_INTERNAL_ERROR.getCode(),
                BaseErrorCode.SYSTEM_INTERNAL_ERROR.getMsg());
        return buildResponse(exception, Status.INTERNAL_SERVER_ERROR, baseResult);
    }

    private static Response buildParamErrorResponse(Exception exception) {
        BaseResult<Map<String, String>> baseResult = new BaseResult<>(BaseResult.ERROR_TYPE, BaseErrorCode.SYSTEM_INTERNAL_ERROR.getCode(),
                BaseErrorCode.SYSTEM_INTERNAL_ERROR.getMsg());
        ParamValidationException pe = (ParamValidationException) exception;
        baseResult.setData(pe.getErrorMap());
        return buildResponse(exception, Status.BAD_REQUEST, baseResult);
    }

    private static Response buildViolationResponse(ResteasyViolationException resteasyViolationException, Status badRequest) {
        Map<String ,String> errorMap = new HashMap<>();
        for(ConstraintViolation<?> error : resteasyViolationException.getConstraintViolations()) {
            String propertyPath = error.getPropertyPath().toString();
            String errorBean = StringUtils.substringAfterLast(error.getLeafBean().getClass().getName(), ".");
            String param = StringUtils.substringAfterLast(propertyPath, ".");
            errorMap.put(errorBean + "." + param, error.getMessage());
        }
        BaseResult<Map<String, String>> baseResult = new BaseResult<>(BaseResult.ERROR_TYPE, BaseErrorCode.PARAMTER_ILLEGAL.getCode(),
                BaseErrorCode.PARAMTER_ILLEGAL.getMsg());
        baseResult.setData(errorMap);
        return buildResponse(resteasyViolationException, badRequest, baseResult);
    }

    private static Response buildResponse(Exception exception, Status status, BaseResult baseResult) {
        logger.error("the interface caused error", exception);
        Response.ResponseBuilder builder = Response.status(status);
        builder.type(MediaType.APPLICATION_JSON);
        builder.entity(baseResult);
        return builder.build();
    }


}
