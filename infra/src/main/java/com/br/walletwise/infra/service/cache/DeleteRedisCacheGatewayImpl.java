package com.br.walletwise.infra.service.cache;

import com.br.walletwise.application.gateway.cache.DeleteCacheGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
@RequiredArgsConstructor
public class DeleteRedisCacheGatewayImpl implements DeleteCacheGateway {
    private final Jedis jedis;

    @Override
    public void delete(String key) {
        this.jedis.del(key);
    }
}
