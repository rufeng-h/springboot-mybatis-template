package com.windcf.springboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author rufeng
 * @time 2022-01-10 19:34
 * @package com.rufeng.healthman.config
 * @description Jackson配置
 */
@Configuration
public class JacksonConfig {
    @Bean
    @Primary
    ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(ApplicationConfig.DATETIME_FORMATTER));
        builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(ApplicationConfig.DATETIME_FORMATTER));
        builder.serializerByType(LocalDate.class, new LocalDateSerializer(ApplicationConfig.DATE_FORMATTER));
        builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(ApplicationConfig.DATE_FORMATTER));
        return builder.createXmlMapper(false).build();
    }
}
