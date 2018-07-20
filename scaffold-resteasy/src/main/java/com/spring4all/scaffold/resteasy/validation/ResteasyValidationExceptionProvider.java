package com.spring4all.scaffold.resteasy.validation;

import com.spring4all.scaffold.common.BaseErrorCode;
import com.spring4all.scaffold.common.BaseResult;
import java.util.Map;
import javax.validation.ConstraintDeclarationException;
import javax.validation.GroupDefinitionException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fangzhibin
 */
public class ResteasyValidationExceptionProvider implements ExceptionMapper<ValidationException> {

  private static final Logger logger = LoggerFactory
      .getLogger(ResteasyValidationExceptionProvider.class);

  @Override
  public Response toResponse(ValidationException e) {
    if (e instanceof ConstraintDeclarationException || e instanceof ConstraintDeclarationException
        || e instanceof GroupDefinitionException) {
      logger.error("system internal exception caused, the exception message is {}", e.getMessage());
      BaseResult<String> baseResult = new BaseResult<>(
          BaseErrorCode.SYSTEM_INTERNAL_ERROR.getCode(),
          BaseErrorCode.SYSTEM_INTERNAL_ERROR.getMsg());
      return buildResponse(Status.INTERNAL_SERVER_ERROR, baseResult);
    }
    if (e instanceof ParamValidationException) {
      logger
          .error("param validation exception caused, the exception message is {}", e.getMessage());
      BaseResult<Map<String, String>> baseResult = new BaseResult<>(
          BaseErrorCode.SYSTEM_INTERNAL_ERROR.getCode(),
          BaseErrorCode.SYSTEM_INTERNAL_ERROR.getMsg());
      ParamValidationException pe = (ParamValidationException) e;
      baseResult.setData(pe.getErrorMap());
      return buildResponse(Status.BAD_REQUEST, baseResult);
    }
    if (e instanceof ResteasyViolationException) {
      ResteasyViolationException resteasyViolationException = ResteasyViolationException.class
          .cast(e);
      Exception exception = resteasyViolationException.getException();
      logger.error("resteasy violation exception caused, the exception message is {}",
          exception.getMessage());
      BaseResult<Map<String, String>> baseResult = new BaseResult<>(
          BaseErrorCode.PARAMETER_ILLEGAL.getCode(),
          BaseErrorCode.PARAMETER_ILLEGAL.getMsg());
      if (null != resteasyViolationException.getReturnValueViolations()
          && resteasyViolationException.getReturnValueViolations().size() == 0) {
        return buildResponse(Status.BAD_REQUEST, baseResult);
      } else {
        return buildResponse(Status.INTERNAL_SERVER_ERROR, baseResult);
      }
    }

    BaseResult<String> baseResult = new BaseResult<>(
        BaseErrorCode.SYSTEM_INTERNAL_ERROR.getCode(),
        BaseErrorCode.SYSTEM_INTERNAL_ERROR.getMsg());
    return buildResponse(Status.INTERNAL_SERVER_ERROR, baseResult);
  }

  private static Response buildResponse(Status status, BaseResult baseResult) {
    Response.ResponseBuilder builder = Response.status(status);
    builder.type(MediaType.APPLICATION_JSON);
    builder.entity(baseResult);
    return builder.build();
  }


}
