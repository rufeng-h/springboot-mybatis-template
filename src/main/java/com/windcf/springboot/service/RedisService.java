package com.windcf.springboot.service;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author HuangChunFeng
 * @time 2023-04-12 11:14
 * @package com.mjmspred.service
 * @description TODO
 */
public interface RedisService {
    /**
     * set string key, object vlaue
     *
     * @param key   string
     * @param value object
     */
    void set(@NonNull String key, @Nullable Object value);


    /**
     * get
     *
     * @param key          string
     * @param requiredType class
     * @param <T>          required java type
     * @return T
     */
    <T> T get(@NonNull String key, Class<T> requiredType);

    /**
     * gets string
     *
     * @param key string key
     * @return string
     */
    String getString(@NonNull String key);
}
