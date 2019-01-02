package com.spring4all.scaffold.resteasy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring4all.scaffold.common.BaseConstants;
import java.text.SimpleDateFormat;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * @author fangzhibin
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonConfig implements ContextResolver<ObjectMapper> {

  private ObjectMapper objectMapper;

  public JacksonConfig() {
    objectMapper = new ObjectMapper();
    objectMapper.setDateFormat(new SimpleDateFormat(BaseConstants.DATE_FORMAT_UTC));
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }

  @Override
  public ObjectMapper getContext(Class<?> aClass) {
    return objectMapper;
  }
}
