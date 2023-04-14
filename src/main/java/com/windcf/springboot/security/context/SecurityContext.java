package com.windcf.springboot.security.context;

import com.windcf.springboot.security.authentication.Authentication;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * Security context interface.
 *
 * @author johnniang
 */
public interface SecurityContext extends Serializable {

    /**
     * Gets the currently authenticated principal.
     *
     * @return the Authentication or null if authentication information is unavailable
     */
    @Nullable
    Authentication getAuthentication();

    /**
     * Changes the currently authenticated principal, or removes the authentication information.
     *
     * @param authentication the new authentication or null if no further authentication should
     *                       not be stored
     */
    void setAuthentication(@Nullable Authentication authentication);

    /**
     * Check if the current context has authenticated or not.
     *
     * @return true if authenticate; false otherwise
     */
    default boolean isAuthenticated() {
        return getAuthentication() != null;
    }
}
