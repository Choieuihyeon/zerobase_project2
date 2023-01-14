package com.example.project2.Account5.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@Profile("test")
public class LocalRedisConfig {
    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;


    @PostConstruct
    public void startRedis() {
        redisServer = new RedisServer(redisPort);
        redisServer.start();
    }



/*
    @PostConstruct
    public void startRedis() {
        redisServer = RedisServer.builder()
                .port(redisPort)
                .setting("maxmemory 128M")
                .build();
        redisServer.start();
    }

 */


    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
