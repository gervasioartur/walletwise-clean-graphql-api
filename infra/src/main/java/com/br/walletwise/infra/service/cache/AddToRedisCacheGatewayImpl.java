package com.br.walletwise.infra.service.cache;

import com.br.walletwise.application.gateway.cache.AddToCacheGateway;
import com.br.walletwise.core.exception.UnexpectedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
@RequiredArgsConstructor
public class AddToRedisCacheGatewayImpl<T> implements AddToCacheGateway<T> {
    private final Jedis jedis;
    private final ObjectMapper mapper;

    @Override
    public void add(String key, T value) {
        try {
            String jsonValue = this.mapper.writeValueAsString(value);
            this.jedis.set(key, jsonValue);
        } catch (Exception ex) {
            throw new UnexpectedException(ex.getMessage());
        }
    }
}