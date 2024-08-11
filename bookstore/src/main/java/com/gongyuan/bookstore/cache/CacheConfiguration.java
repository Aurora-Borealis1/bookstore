package com.gongyuan.bookstore.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author: gongyuan
 * @date: 2024/8/11 20:11
 */
@Configuration
public class CacheConfiguration {

    @Bean
    public CaffeineCacheManager cacheManager1() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("user", "book");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(500)
                .expireAfterWrite(5, TimeUnit.MINUTES));
        return cacheManager;
    }


}
