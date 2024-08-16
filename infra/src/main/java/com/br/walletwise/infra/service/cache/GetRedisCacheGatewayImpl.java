package com.br.walletwise.infra.service.cache;

import com.br.walletwise.application.gateway.cache.GetCacheGateway;
import com.br.walletwise.core.exception.UnexpectedException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRedisCacheGatewayImpl<T> implements GetCacheGateway<T> {
    private final ObjectMapper mapper;
    private final Jedis jedis;

    @Override
    public List<T> get(String key) {
        try {
            String value = jedis.get(key);
            return value == null ? List.of() : this.mapper.readValue(value, new TypeReference<List<T>>() {});
        } catch (Exception ex) {
            throw new UnexpectedException(ex.getMessage());
        }
    }
}