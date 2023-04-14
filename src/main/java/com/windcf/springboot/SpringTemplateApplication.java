package com.windcf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author HuangChunFeng
 * @time 2023-01-09 16:00
 * @package com.mjms
 * @description TODO
 */
@SpringBootApplication
public class SpringTemplateApplication {
    public static void main(String[] args) {
        /* apollo env */
        System.setProperty("env", "pro");
        SpringApplication.run(SpringTemplateApplication.class);
    }
}
