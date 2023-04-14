package com.windcf.springboot.security.authentication;

import com.windcf.springboot.security.support.UserInfo;
import org.springframework.lang.NonNull;

import java.io.Serializable;

/**
 * @author rufeng
 * @time 2022-04-19 17:45
 * @package com.rufeng.healthman.security.authentication
 * @description TODO
 */
public interface Authentication extends Serializable {
    /**
     * userinfo
     *
     * @return userinfo
     */
    @NonNull
    UserInfo getUserInfo();
}
