package com.br.walletwise.infra.service.cache;

import com.br.walletwise.application.gateway.cache.InvalidateCacheGateway;
import com.br.walletwise.usecase.cache.InvalidateCache;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import redis.clients.jedis.Jedis;

import static org.mockito.Mockito.*;

@SpringBootTest
class InvalidateRedisCacheGatewayImplTests {
    @Autowired
    private InvalidateCacheGateway invalidateCacheGateway;
    @MockBean
    private Jedis jedis;

    @Test
    @DisplayName("Should invalidate cache")
    void invalidateCache() {
        String key = "key";
        when(this.jedis.del(key)).thenReturn(1L);
        this.jedis.del(key);
        verify(this.jedis, times(1)).del(key);
    }
}