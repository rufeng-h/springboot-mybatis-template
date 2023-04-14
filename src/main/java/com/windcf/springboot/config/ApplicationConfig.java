package com.windcf.springboot.config;

import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

/**
 * @author HuangChunFeng
 * @time 2023-04-12 11:20
 * @package com.mjmspred.config
 * @description TODO
 */
@Configuration
public class ApplicationConfig {
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
