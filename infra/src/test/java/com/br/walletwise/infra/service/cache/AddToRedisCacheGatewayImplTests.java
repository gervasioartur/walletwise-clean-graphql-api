package com.br.walletwise.infra.service.cache;

import com.br.walletwise.application.gateway.cache.AddToCacheGateway;
import com.br.walletwise.core.exception.UnexpectedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import redis.clients.jedis.Jedis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@SpringBootTest
class AddToRedisCacheGatewayImplTests {
    private AddToCacheGateway addToCacheGateway;
    private Jedis jedis;
    private ObjectMapper mapper;


    @BeforeEach
    void setUp() {
        this.jedis =mock(Jedis.class);
        this.mapper =  mock(ObjectMapper.class);
        this.addToCacheGateway =  new AddToRedisCacheGatewayImpl(jedis,mapper);
    }

    @Test
    @DisplayName("Should throw UnexpectedException if mapper throws on add to redis cache")
    void shouldThrowUnexpectedExceptionOnAddToRedisCache() throws JsonProcessingException {
        String key = "key";
        String value = "value";
        String jsonValue = "any_json_value";
        when(this.mapper.writeValueAsString(value)).thenThrow(JsonProcessingException.class);
        Throwable exception = catchThrowable(() -> this.addToCacheGateway.add(key,value));
        assertThat(exception).isInstanceOf(UnexpectedException.class);
        verify(this.mapper, times(1)).writeValueAsString(value);
    }
}
