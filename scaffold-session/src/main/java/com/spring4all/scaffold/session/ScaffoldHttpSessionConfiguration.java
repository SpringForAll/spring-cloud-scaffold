package com.spring4all.scaffold.session;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * session config
 * custom sessionRedisSerializer for the sessionRedisTemplate and connectionFactory
 * 
 * @author fangzhibin
 * @since 1.0.0
 */
@Configuration
public class ScaffoldHttpSessionConfiguration {

  @Bean
  @Qualifier("springSessionDefaultRedisSerializer")
  public RedisSerializer<Object> sessionRedisSerializer() {
    ScaffoldRedisSessionSerializer<Object> serializer = new ScaffoldRedisSessionSerializer<>();
    return serializer;
  }


}
