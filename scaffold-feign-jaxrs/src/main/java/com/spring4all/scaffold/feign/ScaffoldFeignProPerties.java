package com.spring4all.scaffold.feign;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fangzhibin
 */
@ConfigurationProperties(prefix = "scaffold.feign")
public class ScaffoldFeignProPerties {

  private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
