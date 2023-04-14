package com.windcf.springboot.security.support;

/**
 * @author HuangChunFeng
 * @time 2023-01-10 8:33
 * @package com.mjmspred.security.support
 * @description TODO
 */
public interface UserInfoService {
    /**
     * userinfo
     *
     * @param userId the id
     * @return userinfo
     */
    UserInfo getUserInfo(Object userId);
}
