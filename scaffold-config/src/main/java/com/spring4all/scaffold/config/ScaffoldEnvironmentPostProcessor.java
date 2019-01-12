package com.spring4all.scaffold.config;

import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;

/**
 * ScaffoldEnvironmentPostProcessor
 * @author fangzhibin
 */
public class ScaffoldEnvironmentPostProcessor implements EnvironmentPostProcessor {

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment,
      SpringApplication application) {
    Properties customProperties = new Properties();
    //这里模拟获取配置信息，放入到自定的properties中
    customProperties.put("scaffold","msa");
    PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource("scaffold", customProperties);
    environment.getPropertySources().addLast(propertiesPropertySource);

  }
}
