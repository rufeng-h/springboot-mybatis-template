package com.windcf.springboot.security.authentication;

import com.windcf.springboot.security.support.UserInfo;
import org.springframework.lang.NonNull;

/**
 * @author rufeng
 * @time 2022-04-19 17:47
 * @package com.rufeng.healthman.security.authentication
 * @description TODO
 */
public class AuthenticationImpl implements Authentication {
    private final UserInfo baseUserInfo;

    public AuthenticationImpl(UserInfo baseUserInfo) {
        this.baseUserInfo = baseUserInfo;
    }

    @Override
    @NonNull
    public UserInfo getUserInfo() {
        return baseUserInfo;
    }
}
