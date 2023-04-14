package com.windcf.springboot.security.filter;

import com.windcf.springboot.exception.AccessDeniedException;
import com.windcf.springboot.exception.AuthenticationException;
import com.windcf.springboot.security.context.SecurityContextHolder;
import com.windcf.springboot.security.handler.AccessDeniedHandler;
import com.windcf.springboot.security.handler.AuthenticateFailHandler;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rufeng
 * @time 2022-04-20 20:07
 * @package com.rufeng.healthman.security
 */
@Component
@Order(2)
public class AuthenticationFilter extends OncePerRequestFilter {
    protected final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final AuthenticateFailHandler authenticateFailHandler;
    private final AccessDeniedHandler accessDeniedHandler;
    private final UrlPathHelper urlPathHelper = new UrlPathHelper();
    private final Set<String> excludeUrlPatterns = new HashSet<>(16);
    private final Set<String> urlPatterns = new HashSet<>(16);

    public AuthenticationFilter(AuthenticateFailHandler authenticateFailHandler, AccessDeniedHandler accessDeniedHandler) {
        this.authenticateFailHandler = authenticateFailHandler;
        this.accessDeniedHandler = accessDeniedHandler;
//        addUrlPatterns("/api/**", "/test/api/**");
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            if (SecurityContextHolder.getContext().isAuthenticated()) {
                filterChain.doFilter(request, response);
            } else {
                throw new AuthenticationException("需要认证！");
            }
        } catch (AuthenticationException ex) {
            authenticateFailHandler.onAuthenticateFail(request, response, ex);
        } catch (AccessDeniedException ex) {
            accessDeniedHandler.onAccessDenied(request, response, ex);
        }
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        Assert.notNull(request, "Http servlet request must not be null");

        // check white list
        boolean result = excludeUrlPatterns.stream()
                .anyMatch(p -> antPathMatcher.match(p, urlPathHelper.getRequestUri(request)));

        return result || urlPatterns.stream()
                .noneMatch(p -> antPathMatcher.match(p, urlPathHelper.getRequestUri(request)));

    }

    public void addExcludeUrlPatterns(@NonNull String... excludeUrlPatterns) {
        Assert.notNull(excludeUrlPatterns, "Exclude url patterns must not be null");
        Collections.addAll(this.excludeUrlPatterns, excludeUrlPatterns);
    }

    public void addUrlPatterns(String... urlPatterns) {
        Assert.notNull(urlPatterns, "UrlPatterns must not be null");
        Collections.addAll(this.urlPatterns, urlPatterns);
    }
}
