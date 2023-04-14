package com.windcf.springboot.exception;

/**
 * @author HuangChunFeng
 * @time 2023-01-09 18:52
 * @package com.mjmspred.exception
 * @description TODO
 */
public class AuthenticationException extends MjmsException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthenticationException() {
        this("认证失败");
    }
}
