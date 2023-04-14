package com.windcf.springboot.common;

import com.windcf.springboot.common.api.ApiResponse;
import com.windcf.springboot.exception.AccessDeniedException;
import com.windcf.springboot.exception.AuthenticationException;
import com.windcf.springboot.exception.MjmsException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author rufeng
 * @time 2022-02-28 14:48
 * @package com.rufeng.healthman.common
 * @description 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 未知异常
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> unknownError(Exception e) {
        e.printStackTrace();
        return ApiResponse.unknownError();
    }

    /**
     * TODO 如何处理异常消息
     */
    @ExceptionHandler(DataAccessException.class)
    public ApiResponse<Void> duplicateError(DataAccessException e) {
        String messge = "未知异常";
        if (e instanceof DuplicateKeyException) {
            messge = "系统存在重复记录！";
        } else if (e instanceof DataIntegrityViolationException) {
            messge = "资源正在被使用，无法进行此操作！";
        }
        return ApiResponse.clientError(messge);
    }

    /**
     * 项目自定义异常
     */
    @ExceptionHandler(MjmsException.class)
    public ApiResponse<Void> ptError(MjmsException e) {
        e.printStackTrace();
        return ApiResponse.clientError(e.getMessage());
    }

    /**
     * 认证异常
     *
     * @param e exp
     */
    @ExceptionHandler(AuthenticationException.class)
    public ApiResponse<Void> authenticationError(AuthenticationException e) {
        return ApiResponse.authenticateFailed(e.getMessage());
    }

    /**
     * 请求参数、请求体异常
     */
    @ExceptionHandler({BindException.class})
    public ApiResponse<Void> validateFailed() {
        return ApiResponse.validateFailed();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ApiResponse<Void> accessDenied(AccessDeniedException ex) {
        return ApiResponse.accessDenied(ex.getMessage());
    }
}
