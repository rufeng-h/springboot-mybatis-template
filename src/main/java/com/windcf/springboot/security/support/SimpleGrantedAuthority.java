package com.windcf.springboot.security.support;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author rufeng
 * @time 2022-04-19 18:31
 * @package com.rufeng.healthman.security.support
 */
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class SimpleGrantedAuthority implements GrantedAuthority {
    private String authority;

    public SimpleGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
