package com.windcf.springboot.common.util;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author rufeng
 * @time 2022-04-16 10:58
 * @package com.rufeng.healthman.common.util
 * @description 方便获取IOC容器信息
 */
@Component
public class SpringUtils implements EnvironmentAware {
    private static Environment env;

    public static boolean isDevMode() {
        String[] profiles = env.getActiveProfiles();
        for (String profile : profiles) {
            if ("dev".equals(profile)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        env = environment;
    }
}
