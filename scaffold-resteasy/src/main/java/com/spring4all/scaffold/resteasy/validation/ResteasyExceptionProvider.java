package com.spring4all.scaffold.resteasy.validation;

import com.spring4all.scaffold.common.BaseErrorCode;
import com.spring4all.scaffold.common.BaseResult;
import com.spring4all.scaffold.common.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.validation.ConstraintViolation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.HashMap;
import java.util.Map;

public class ResteasyExceptionProvider implements ExceptionMapper<Exception> {

    private static final Logger logger = LoggerFactory.getLogger(ResteasyExceptionProvider.class);

    @Override
    public Response toResponse(Exception e) {
        if(e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            BaseResult baseResult = new BaseResult(BaseResult.FAIL_TYPE, businessException.getCode(), businessException.getMsg(), businessException.getData());
            return buildResponse(e, Status.BAD_REQUEST, baseResult);
        } else {
            BaseResult<String> baseResult = new BaseResult<>(BaseResult.ERROR_TYPE, BaseErrorCode.SYSTEM_INTERNAL_ERROR.getCode(), BaseErrorCode.SYSTEM_INTERNAL_ERROR.getMsg());
            return buildResponse(e, Status.INTERNAL_SERVER_ERROR, baseResult);
        }
    }

    public static Response buildResponse(Exception exception, Status status, BaseResult baseResult) {
        logger.error("the interface caused error", exception);
        ResponseBuilder builder = Response.status(status);
        builder.type(MediaType.APPLICATION_JSON);
        builder.entity(baseResult);
        return builder.build();
    }

    public static Response buildServerErrorResponse(Exception exception) {
        BaseResult<String> baseResult = new BaseResult<>(BaseResult.ERROR_TYPE, BaseErrorCode.SYSTEM_INTERNAL_ERROR.getCode(),
                BaseErrorCode.SYSTEM_INTERNAL_ERROR.getMsg());
        return buildResponse(exception, Status.INTERNAL_SERVER_ERROR, baseResult);
    }

    public static Response buildParamErrorResponse(Exception exception) {
        BaseResult<Map<String, String>> baseResult = new BaseResult<>(BaseResult.ERROR_TYPE, BaseErrorCode.SYSTEM_INTERNAL_ERROR.getCode(),
                BaseErrorCode.SYSTEM_INTERNAL_ERROR.getMsg());
        ParamValidationException pe = (ParamValidationException) exception;
        baseResult.setData(pe.getErrorMap());
        return buildResponse(exception, Status.BAD_REQUEST, baseResult);
    }

    public static Response buildViolationResponse(ResteasyViolationException resteasyViolationException, Status badRequest) {
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
}
