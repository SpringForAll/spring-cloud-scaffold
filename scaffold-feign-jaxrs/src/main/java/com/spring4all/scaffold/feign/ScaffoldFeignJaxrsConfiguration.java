package com.spring4all.scaffold.feign;

import feign.Contract;
import feign.RequestInterceptor;
import feign.jaxrs.JAXRSContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fangzhibin
 */
@Configuration
@EnableConfigurationProperties(ScaffoldFeignProPerties.class)
public class ScaffoldFeignJaxrsConfiguration {

  @Autowired
  ScaffoldFeignProPerties scaffoldFeignProPerties;

  @Bean
  public Contract feignContract() {
    return new JAXRSContract();
  }

  @Bean
  public ScaffoldErrorDecoder errorDecoder() {
    return new ScaffoldErrorDecoder();
  }

  @Bean
  @ConditionalOnProperty(value = "scaffold.rest.enableTokenApply", havingValue = "true", matchIfMissing = true)
  public RequestInterceptor requestInterceptor() {
    return new ScaffoldRequestInterceptor(scaffoldFeignProPerties.getToken());
  }
}
