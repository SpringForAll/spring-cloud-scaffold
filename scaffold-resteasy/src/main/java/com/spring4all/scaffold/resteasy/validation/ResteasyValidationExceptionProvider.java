package com.spring4all.scaffold.resteasy.validation;

import com.spring4all.scaffold.common.BaseErrorCode;
import com.spring4all.scaffold.common.BaseResult;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintDeclarationException;
import javax.validation.GroupDefinitionException;
import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class ResteasyValidationExceptionProvider implements ExceptionMapper<ValidationException> {

    private static final Logger logger = LoggerFactory.getLogger(ResteasyValidationExceptionProvider.class);

    @Override
    public Response toResponse(ValidationException e) {
        if(e instanceof ConstraintDeclarationException || e instanceof ConstraintDeclarationException || e instanceof GroupDefinitionException) {
            return ResteasyExceptionProvider.buildServerErrorResponse(e);
        }
        if(e instanceof ParamValidationException) {
            return ResteasyExceptionProvider.buildParamErrorResponse(e);
        }
        if(e instanceof ResteasyViolationException) {
            ResteasyViolationException resteasyViolationException = ResteasyViolationException.class.cast(e);
            Exception exception = resteasyViolationException.getException();
            if(null != exception) {
                return ResteasyExceptionProvider.buildServerErrorResponse(exception);
            } else if (null != resteasyViolationException.getReturnValueViolations() && resteasyViolationException.getReturnValueViolations().size() == 0) {
                return ResteasyExceptionProvider.buildViolationResponse(resteasyViolationException, Status.BAD_REQUEST);
            } else {
                return ResteasyExceptionProvider.buildViolationResponse(resteasyViolationException, Status.INTERNAL_SERVER_ERROR);
            }
        }

        BaseResult<String> baseResult = new BaseResult<>(BaseResult.ERROR_TYPE, BaseErrorCode.SYSTEM_INTERNAL_ERROR.getCode(),
                BaseErrorCode.SYSTEM_INTERNAL_ERROR.getMsg());
        return ResteasyExceptionProvider.buildResponse(e, Status.INTERNAL_SERVER_ERROR, baseResult);
    }


}
