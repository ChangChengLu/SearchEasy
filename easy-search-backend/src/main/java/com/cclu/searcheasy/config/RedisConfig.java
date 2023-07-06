package com.cclu.searcheasy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author ChangCheng Lu
 * @date 2023/7/6 14:50
 */
@Configuration
public class RedisConfig {

    @Resource
    RedisConnectionFactory redisConnectionFactory;


}
