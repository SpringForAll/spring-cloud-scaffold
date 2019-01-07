package com.spring4all.scaffold.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring4all.scaffold.common.AuthorizationException;
import com.spring4all.scaffold.common.BaseResult;
import com.spring4all.scaffold.common.BusinessException;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fangzhibin
 */
public class ScaffoldErrorDecoder extends ErrorDecoder.Default {

  private static final Logger logger = LoggerFactory.getLogger(ScaffoldErrorDecoder.class);

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public Exception decode(String methodKey, Response response) {
    try {
      if (null != response.body()) {
        BaseResult result = objectMapper.readValue(response.body().asReader(), BaseResult.class);
        logger.warn("feign invoker caused an exception: [{}]", result.getCode());
        int status = response.status();
        if (status >= 500) {
          throw new BusinessException(result.getCode(), result.getMsg(), result.getData(), null);
        }  else if (status == 403) {
          throw new AuthorizationException(result.getCode(), result.getMsg());
        } else if (status >= 400) {
          throw new BusinessException(result.getCode(), result.getMsg(), result.getData(), null);
        }
      }
    } catch (IOException e) {
    }

    return super.decode(methodKey, response);
  }
}
