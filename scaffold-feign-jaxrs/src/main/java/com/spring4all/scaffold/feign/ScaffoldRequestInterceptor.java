package com.spring4all.scaffold.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author fangzhibin
 */
public class ScaffoldRequestInterceptor implements RequestInterceptor {

  private String token;

  public ScaffoldRequestInterceptor(String token) {
    this.token = token;
  }

  @Override
  public void apply(RequestTemplate template) {
    template.header("scaffold-token", token);
  }
}
