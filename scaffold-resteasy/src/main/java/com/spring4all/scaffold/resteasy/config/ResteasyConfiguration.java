package com.spring4all.scaffold.resteasy.config;

import com.spring4all.scaffold.resteasy.filter.ScaffoldTokenRequestFilter;
import com.spring4all.scaffold.resteasy.validation.ResteasyExceptionProvider;
import com.spring4all.scaffold.resteasy.validation.ResteasyValidationExceptionProvider;
import javax.ws.rs.core.Application;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fangzhibin
 */
@Configuration
@EnableConfigurationProperties(ResteasyProperties.class)
public class ResteasyConfiguration {

  @Bean
  public JacksonConfig jackson2Provider() {
    return new JacksonConfig();
  }

  @Bean
  public ResteasyExceptionProvider resteasyExceptionProvider() {
    return new ResteasyExceptionProvider();
  }

  @Bean
  public ResteasyValidationExceptionProvider resteasyValidationExceptionProvider() {
    return new ResteasyValidationExceptionProvider();
  }

  @Bean
  @ConditionalOnMissingBean(Application.class)
  public Application jaxRsApplication() {
    return new JaxRsApplication();
  }

  @Bean
  @ConditionalOnProperty(prefix = "scaffold.rest", name = "enableTokenCheck", havingValue = "true", matchIfMissing = true)
  public ScaffoldTokenRequestFilter hikTokenRequestFilter(ResteasyProperties resteasyProperties) {
    return new ScaffoldTokenRequestFilter(resteasyProperties.getExTokenRefreshInterval());
  }
}
