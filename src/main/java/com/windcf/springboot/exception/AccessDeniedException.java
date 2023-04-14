package com.windcf.springboot.exception;

/**
 * @author HuangChunFeng
 * @time 2023-01-09 18:53
 * @package com.mjmspred.exception
 * @description TODO
 */
public class AccessDeniedException extends MjmsException {
    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
