package com.spring4all.scaffold.feign;

import feign.Contract;
import feign.RequestInterceptor;
import feign.jaxrs.JAXRSContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
@EnableConfigurationProperties(ScaffoldFeignProPerties.class)
public class FeignJaxrsConfiguration {

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
    public RequestInterceptor requestInterceptor() {

        return new ScaffoldRequestInterceptor(scaffoldFeignProPerties.getToken());
    }}
