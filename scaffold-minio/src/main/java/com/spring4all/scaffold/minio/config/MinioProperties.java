package com.spring4all.scaffold.minio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author fangzhibin
 */
@ConfigurationProperties(prefix = "scaffold.minio")
public class MinioProperties {

  private String accessKey;

  private String secretKey;

  private int port = 0;

  private String endpoint;

  private String region;

  private boolean secure = false;

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(String endpoint) {
    this.endpoint = endpoint;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public boolean isSecure() {
    return secure;
  }

  public void setSecure(boolean secure) {
    this.secure = secure;
  }
}
