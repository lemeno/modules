/**   
 * All rights Reserved, Designed By 林溪  
 * Copyright:    Copyright(C) 2016-2030  
 */

package com.modules.cache.caffeine.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**   
 * caffeine配置
 * @author：林溪 
 * @date：2020年10月6日   
 */
@Configuration
@EnableCaching
public class CaffeineConfig {

    @Bean
    public Cache<String, Object> cache() {
        return Caffeine.newBuilder()
                // 设置最后一次访问多长时间后过期
                .expireAfterAccess(300, TimeUnit.SECONDS)
                // 初始化
                .initialCapacity(500)
                .maximumSize(2000)
                .build();
    }

    
    
}
