package com.spring4all.scaffold.feign.form;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;

/**
 * support the feign file upload
 *
 * @FeignClient(name = "feign-service-name",configuration = FeignFormConfiguration.class) and spring
 * mvc must use @RequestPart annotation to invoke the feign service
 *
 * @author fangzhibin
 */
public class FeignFormConfiguration {

  @Bean
  public Encoder feignFormEncoder() {
    return new SpringFormEncoder();
  }

}
