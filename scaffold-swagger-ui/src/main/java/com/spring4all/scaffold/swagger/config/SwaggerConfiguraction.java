package com.spring4all.scaffold.swagger.config;

import com.spring4all.scaffold.swagger.listing.ScaffoldApiListingResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fangzhibin
 */
@Configuration
public class SwaggerConfiguraction {

  @Value("${swagger.title:nope}")
  private String title;
  @Value("${swagger.version:nope}")
  private String version;
  @Value("${swagger.basePath:nope}")
  private String basePath;
  @Value("${swagger.resourcePackage:nope}")
  private String resourcePackage;

  @Bean
  @ConditionalOnMissingBean(BeanConfig.class)
  public BeanConfig beanConfig() {
    BeanConfig beanConfig = new BeanConfig();
    beanConfig.setTitle(title);
    beanConfig.setVersion(version);
    beanConfig.setSchemes(new String[]{"http"});
    beanConfig.setBasePath(basePath);
    beanConfig.setResourcePackage(resourcePackage);
    beanConfig.setScan(true);
    return beanConfig;
  }

  @Bean
  public SwaggerSerializers swaggerSerializers() {
    return new SwaggerSerializers();
  }

  @Bean
  public ScaffoldApiListingResource apiListingResource() {
    return new ScaffoldApiListingResource();
  }
}
