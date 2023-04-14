package com.windcf.springboot.security.repositry;

import com.windcf.springboot.security.authentication.AuthenticationImpl;
import com.windcf.springboot.security.context.SecurityContext;
import com.windcf.springboot.security.context.SecurityContextHolder;
import com.windcf.springboot.security.support.JwtTokenManager;
import com.windcf.springboot.security.support.UserInfo;
import com.windcf.springboot.security.support.UserInfoService;
import com.windcf.springboot.config.OpenApiConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author rufeng
 */
@Component
@Slf4j
public class TokenSecurityContextRepository implements SecurityContextRepository {
    private final UserInfoService userService;
    private final JwtTokenManager jwtTokenManager;

    public TokenSecurityContextRepository(UserInfoService userService, JwtTokenManager jwtTokenManager) {
        this.userService = userService;
        this.jwtTokenManager = jwtTokenManager;
    }

    @Override
    @NonNull
    public SecurityContext loadContext(HttpServletRequest request) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        String token = request.getHeader(OpenApiConfig.JWT_HEADER_NAME);
        if (!StringUtils.hasText(token)) {
            return context;
        }
        String id = jwtTokenManager.getId(token);
        if (id == null) {
            return context;
        }
        UserInfo user = userService.getUserInfo(Long.parseLong(id));
        if (user == null) {
            return context;
        }
        context.setAuthentication(new AuthenticationImpl(user));
        return context;
    }

    /**
     * 完全存储在客户端，服务端空实现
     */
    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

    }
}