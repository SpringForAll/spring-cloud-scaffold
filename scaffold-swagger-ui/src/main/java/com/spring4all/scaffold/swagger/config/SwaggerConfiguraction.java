package com.spring4all.scaffold.swagger.config;

import com.spring4all.scaffold.swagger.listing.ScaffoldApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fangzhibin
 */
@Configuration
public class SwaggerConfiguraction {

    @Bean
    public SwaggerSerializers swaggerSerializers() {
        return new SwaggerSerializers();
    }

    @Bean
    public ScaffoldApiListingResource apiListingResource() {
        return new ScaffoldApiListingResource();
    }
}
