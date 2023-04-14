package com.windcf.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.windcf.springboot.common.api.ApiResponse;
import com.windcf.springboot.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author HuangChunFeng
 * @time 2023-04-12 11:40
 * @package PACKAGE_NAME
 * @description TODO
 */
@ActiveProfiles("dev")
@SpringBootTest
public class ApplicationTests {
    @Autowired
    private RedisService redisService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {

    }

    @Test
    void testRedisService() throws JsonProcessingException {
        ApiResponse<Object> apiResponse = ApiResponse.success("哈哈哈哈的发挥");
        redisService.set("res", apiResponse);
        redisService.get("res", ApiResponse.class);
        System.out.println(objectMapper.readValue(redisService.getString("res"), ApiResponse.class));
    }
}
