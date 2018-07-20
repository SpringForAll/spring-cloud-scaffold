package com.spring4all.scaffold.resteasy.validation;

import com.spring4all.scaffold.common.AuthorizationException;
import com.spring4all.scaffold.common.BaseErrorCode;
import com.spring4all.scaffold.common.BaseResult;
import com.spring4all.scaffold.common.BusinessException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fangzhibin
 */
public class ResteasyExceptionProvider implements ExceptionMapper<Exception> {

  private static final Logger logger = LoggerFactory.getLogger(ResteasyExceptionProvider.class);

  @Override
  public Response toResponse(Exception e) {
    if (e instanceof BusinessException) {
      BusinessException businessException = (BusinessException) e;
      logger.error("business exception caused, the exception message is {}", e.getMessage());
      BaseResult baseResult = new BaseResult(businessException.getCode(),
          businessException.getMsg(), businessException.getData());
      return buildResponse(Status.BAD_REQUEST, baseResult);
    } else if (e instanceof AuthorizationException) {
      AuthorizationException be = (AuthorizationException) e;
      logger.error("authorization exception caused, the exception message is {}", e.getMessage());
      BaseResult baseResult = new BaseResult<>(
          be.getCode(), be.getMsg());
      return buildResponse(Status.FORBIDDEN, baseResult);
    } else {
      logger.error("exception caused, the exception message is {}", e.getMessage());
      BaseResult<String> baseResult = new BaseResult<>(
          BaseErrorCode.SYSTEM_INTERNAL_ERROR.getCode(),
          BaseErrorCode.SYSTEM_INTERNAL_ERROR.getMsg());
      return buildResponse(Status.INTERNAL_SERVER_ERROR, baseResult);
    }
  }

  public static Response buildResponse(Status status, BaseResult baseResult) {
    ResponseBuilder builder = Response.status(status);
    builder.type(MediaType.APPLICATION_JSON);
    builder.entity(baseResult);
    return builder.build();
  }

}
