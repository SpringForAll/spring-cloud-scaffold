package com.spring4all.scaffold.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author fangzhibin
 * @param <T>
 */
public class ScaffoldRedisSessionSerializer<T> implements RedisSerializer<T> {

  private static final Logger log = LoggerFactory.getLogger(ScaffoldRedisSessionSerializer.class);
  private Converter<Object, byte[]> serializer = new SerializingConverter();
  private Converter<byte[], Object> deserializer = new DeserializingConverter();

  static final byte[] EMPTY_ARRAY = new byte[0];

  public T deserialize(byte[] bytes) {
    if (!isEmpty(bytes)) {
      try {
        T t = (T)deserializer.convert(bytes);
        return t;
      } catch (Exception e) {
        log.warn("can not deserialize, the error message:{}", e.getMessage());
      }
    }
    return null;
  }

  public byte[] serialize(Object object) {
    if (object != null) {
      try {
        return serializer.convert(object);
      } catch (Exception e) {
        log.warn("can not serialize, the error message:{}", e.getMessage());
      }
    }
    return EMPTY_ARRAY;
  }

  private boolean isEmpty(byte[] data) {
    return (data == null || data.length == 0);
  }
}
