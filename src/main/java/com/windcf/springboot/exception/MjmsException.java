package com.windcf.springboot.exception;

/**
 * @author HuangChunFeng
 * @time 2023-01-09 18:49
 * @package com.mjmspred.exception
 * @description TODO
 */
public class MjmsException extends RuntimeException {
    public MjmsException(String message) {
        super(message);
    }

    public MjmsException(String message, Throwable cause) {
        super(message, cause);
    }
}
