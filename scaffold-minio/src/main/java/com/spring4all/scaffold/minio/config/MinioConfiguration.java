package com.spring4all.scaffold.minio.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fangzhibin
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfiguration {

  @Autowired
  private MinioProperties minioProperties;

  @Bean
  public MinioClient minioClient() throws InvalidPortException, InvalidEndpointException {
    MinioClient minioClient = new MinioClient(
        minioProperties.getEndpoint(),
        minioProperties.getPort(),
        minioProperties.getAccessKey(),
        minioProperties.getSecretKey(),
        minioProperties.getRegion(),
        minioProperties.isSecure()
    );
    return minioClient;
  }
}
