package com.windcf.springboot.security.support;

import java.io.Serializable;

/**
 * @author rufeng
 * @time 2022-03-09 18:29
 * @package com.rufeng.healthman.pojo.BO
 * @description 返回的userInfo
 */

public interface UserInfo extends Serializable {
    /**
     * gets user id
     *
     * @return userid
     */
    Object getUserId();

    /**
     * gets username
     *
     * @return username
     */
    String getUsername();
}
