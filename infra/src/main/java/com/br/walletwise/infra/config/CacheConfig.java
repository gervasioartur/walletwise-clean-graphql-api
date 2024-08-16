package com.br.walletwise.infra.config;

import com.br.walletwise.application.gateway.cache.AddToCacheGateway;
import com.br.walletwise.application.gateway.cache.DeleteCacheGateway;
import com.br.walletwise.application.gateway.cache.GetCacheGateway;
import com.br.walletwise.application.usecasesimpl.cache.AddToCacheImpl;
import com.br.walletwise.application.usecasesimpl.cache.InvalidateCacheImpl;
import com.br.walletwise.application.usecasesimpl.cache.GetCacheImpl;
import com.br.walletwise.usecase.cache.AddToCache;
import com.br.walletwise.usecase.cache.InvalidateCache;
import com.br.walletwise.usecase.cache.GetCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class CacheConfig {
    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Bean
    public Jedis jedis() {
        return new Jedis(this.redisHost, this.redisPort);
    }

    @Bean
    public AddToCache addToCache(AddToCacheGateway addToCacheGateway) {
        return new AddToCacheImpl(addToCacheGateway);
    }

    @Bean
    public InvalidateCache deleteCache(DeleteCacheGateway deleteCacheGateway) {
        return new InvalidateCacheImpl(deleteCacheGateway);
    }

    @Bean
    public GetCache getCache(GetCacheGateway getCacheGateway) {
        return new GetCacheImpl(getCacheGateway);
    }
}