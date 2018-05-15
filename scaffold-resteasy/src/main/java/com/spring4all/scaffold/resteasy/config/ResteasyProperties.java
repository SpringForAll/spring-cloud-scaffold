package com.spring4all.scaffold.resteasy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fangzhibin
 */

@ConfigurationProperties(prefix = "scaffold.rest")
public class ResteasyProperties {

  private Integer exTokenRefreshInterval = 20 * 60;

  private boolean enableTokenCheck = true;

  private boolean enableTokenApply = true;

  public Integer getExTokenRefreshInterval() {
    return exTokenRefreshInterval;
  }

  public void setExTokenRefreshInterval(Integer exTokenRefreshInterval) {
    this.exTokenRefreshInterval = exTokenRefreshInterval;
  }

  public boolean isEnableTokenCheck() {
    return enableTokenCheck;
  }

  public void setEnableTokenCheck(boolean enableTokenCheck) {
    this.enableTokenCheck = enableTokenCheck;
  }

  public boolean isEnableTokenApply() {
    return enableTokenApply;
  }

  public void setEnableTokenApply(boolean enableTokenApply) {
    this.enableTokenApply = enableTokenApply;
  }
}
