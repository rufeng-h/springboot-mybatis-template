package com.windcf.springboot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.lang.reflect.Method;

/**
 * @author rufeng
 * @time 2022-02-24 10:09
 * @package com.rufeng.healthman.config
 * @description Api文档配置
 */
@Profile("dev")
@Configuration
public class OpenApiConfig {
    public static final String JWT_SCHEME_NAME = "JWT";
    public static final String JWT_HEADER_NAME = "Authorization";

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(new Info().contact(new Contact().email("huangchunfeng@123.com").name("huangchunfeng").url("http://windcf.com")).description("餐食预测").title("餐食预测").license(new License().url("https://opensource.org/licenses/MIT")).version("v1.0")).schemaRequirement(JWT_SCHEME_NAME, new SecurityScheme().scheme("bearer").in(SecurityScheme.In.HEADER).type(SecurityScheme.Type.APIKEY).name(JWT_HEADER_NAME).bearerFormat("JWT"));
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        return (operation, handlerMethod) -> {
            Method method = handlerMethod.getMethod();
            operation.operationId(String.format("%s.%s", method.getDeclaringClass().getName(), method.getName()));
            return operation;
        };
    }
}
