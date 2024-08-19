package com.br.walletwise.infra.service.cache;

import com.br.walletwise.application.gateway.cache.GetCacheGateway;
import com.br.walletwise.core.exception.UnexpectedException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.LinkedHashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GetRedisCacheGatewayImpl implements GetCacheGateway {
    private final Jedis jedis;
    private final ObjectMapper mapper;

    @Override
    public List<LinkedHashMap<String, Object>> get(String key) {
        try {
            String value = this.jedis.get(key);
            return value == null ? List.of() : this.mapper.readValue(value, new TypeReference<>() {
            });
        } catch (Exception ex) {
            throw new UnexpectedException(ex.getMessage());
        }
    }
}