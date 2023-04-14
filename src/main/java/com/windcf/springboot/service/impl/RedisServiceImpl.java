package com.windcf.springboot.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.windcf.springboot.config.ApplicationConfig;
import com.windcf.springboot.service.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author HuangChunFeng
 * @time 2023-04-12 11:16
 * @package com.mjmspred.service.impl
 * @description TODO
 */
@Service
public class RedisServiceImpl implements RedisService {
    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    public RedisServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectMapper = buildObjectMapper();
    }

    private ObjectMapper buildObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(ApplicationConfig.DATE_FORMATTER));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(ApplicationConfig.DATETIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(ApplicationConfig.DATE_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(ApplicationConfig.DATETIME_FORMATTER));
        mapper.registerModule(javaTimeModule);
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    @Override
    public void set(@NonNull String key, Object value) {
        Assert.notNull(key, "key cant not be null");
        try {
            stringRedisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(value));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T get(@NonNull String key, Class<T> requiredType) {
        try {
            return objectMapper.readValue(stringRedisTemplate.opsForValue().get(key), requiredType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
