package com.spring4all.scaffold.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * ScaffoldConfigConfiguration
 * @author fangzhibin
 */
public class ScaffoldConfigConfiguration {

  @Bean
  @ConditionalOnMissingBean(ScaffoldConfigServicePropertySourceLocator.class)
  public ScaffoldConfigServicePropertySourceLocator scaffoldConfigServicePropertySourceLocator() {
    return new ScaffoldConfigServicePropertySourceLocator();
  }

}
