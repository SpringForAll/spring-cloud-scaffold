package com.spring4all.scaffold.logger.processor;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * @author fangzhibin
 */
public class ScaffoldLoggerTraceEnvironmentPostProcessor implements EnvironmentPostProcessor {

  private static final String PROPERTY_SOURCE_NAME = "defaultProperties";


  public ScaffoldLoggerTraceEnvironmentPostProcessor() {
  }

  @Override
  public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment,
      SpringApplication springApplication) {
    Map<String, Object> map = new HashMap<>();
    map.put("logging.pattern.level", "5p");
    addOrReplace(configurableEnvironment.getPropertySources(), map);
  }

  private void addOrReplace(MutablePropertySources propertySources, Map<String, Object> map) {
    MapPropertySource target = null;
    if (propertySources.contains(PROPERTY_SOURCE_NAME)) {
      PropertySource<?> source = propertySources.get(PROPERTY_SOURCE_NAME);
      if (source instanceof MapPropertySource) {
        target = (MapPropertySource) source;
        for (String key : map.keySet()) {
          if (!target.containsProperty(key)) {
            target.getSource().put(key, map.get(key));
          }
        }
      }
    }
    if (target == null) {
      target = new MapPropertySource(PROPERTY_SOURCE_NAME, map);
    }
    if (!propertySources.contains(PROPERTY_SOURCE_NAME)) {
      propertySources.addLast(target);
    }
  }
}
