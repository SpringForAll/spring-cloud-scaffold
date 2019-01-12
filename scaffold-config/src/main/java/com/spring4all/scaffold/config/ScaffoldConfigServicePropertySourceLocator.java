package com.spring4all.scaffold.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

/**
 * ScaffoldConfigServicePropertySourceLocator
 * @author fangzhibin
 */
@Order(0)
public class ScaffoldConfigServicePropertySourceLocator implements PropertySourceLocator {

  @Override
  public PropertySource<?> locate(Environment environment) {
    //这里模拟获取配置信息，放入到自定的properties中
    Map<String, Object> properties = new HashMap<>();
    properties.put("scaffold", "sc");
    CompositePropertySource compositePropertySource = new CompositePropertySource("scaffold");

    compositePropertySource.addPropertySource(new MapPropertySource("scaffold", properties));
    return compositePropertySource;
  }
}
