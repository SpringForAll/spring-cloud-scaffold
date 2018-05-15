package com.spring4all.scaffold.exception.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring4all.scaffold.exception.advice.BeanValidationExceptionAdvice;
import com.spring4all.scaffold.exception.advice.ExceptionFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author fangzhibin
 */
@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class ExceptionConfig {

  @Bean
  public BeanValidationExceptionAdvice beanValidationExceptionAdvice() {
    return new BeanValidationExceptionAdvice();
  }

  @Bean
  @ConditionalOnMissingBean
  public ExceptionFilter exceptionFilter() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
    objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
    objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    return new ExceptionFilter(objectMapper);
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBean(
      @Value("${exception.filter.url.pattern:/*}") String[] urlPattern) {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(exceptionFilter());
    filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    filterRegistrationBean.addUrlPatterns(urlPattern);
    return filterRegistrationBean;
  }
}
