package com.br.walletwise.infra.service.cache;

import com.br.walletwise.application.gateway.cache.GetCacheGateway;
import com.br.walletwise.core.exception.UnexpectedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

class GetRedisCacheGatewayImplTests {
    private GetCacheGateway getRedisCacheGateway;
    private ObjectMapper mapper;
    private Jedis jedis;

    @BeforeEach
    void setup(){
        this.mapper = mock(ObjectMapper.class);
        this.jedis = mock(Jedis.class);
        this.getRedisCacheGateway = new GetRedisCacheGatewayImpl(this.mapper, this.jedis);
    }

    @Test
    @DisplayName("Should throw UnexpectedException if mapper throws")
    void shouldThrowUnexpectedExceptionIfMapperThrows() throws JsonProcessingException {
        String key = "any_key";
        String jsonValue = "any_Json_value";

        when(this.jedis.get(key)).thenReturn(jsonValue);
        when(this.mapper.readValue(jsonValue, Object.class)).thenThrow();

        Throwable exception = catchThrowable(() ->  this.getRedisCacheGateway.get(key));

        assertThat(exception).isInstanceOf(UnexpectedException.class);
        verify(this.mapper, times(1)).readValue(jsonValue, Object.class);
    }
}
