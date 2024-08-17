package com.br.walletwise.infra.service.cache;

import com.br.walletwise.application.gateway.cache.GetCacheGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.exception.UnexpectedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

class GetRedisCacheGatewayImplTests {
    private GetCacheGateway getRedisCacheGateway;
    private ObjectMapper mapper;
    private Jedis jedis;

    @BeforeEach
    void setup() {
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

        Throwable exception = catchThrowable(() -> this.getRedisCacheGateway.get(key));

        assertThat(exception).isInstanceOf(UnexpectedException.class);
        verify(this.mapper, times(1)).readValue(jsonValue, Object.class);
    }

    @Test
    @DisplayName("Should return null if there not value in cache with specified key")
    void shouldReturnNullIfValueInCacheWithSpecifiedKey() throws JsonProcessingException {
        String key = "any_key";
        when(this.jedis.get(key)).thenReturn(null);
        var result = this.getRedisCacheGateway.get(key);
        assertThat(result).isEqualTo(List.of());
        verify(this.jedis, times(1)).get(key);
    }

    @Test
    @DisplayName("Should return list oc cached values")
    void shouldListOccCachedValues() throws JsonProcessingException {
        String key = "any_key";
        String jsonValue = "any_Json_value";

        List list = List.of("item1", "item2");

        when(this.jedis.get(key)).thenReturn(jsonValue);
        when(this.mapper.readValue(jsonValue, new TypeReference<List<Object>>() {
        })).thenReturn(list);

        List<FixedExpense> result = this.getRedisCacheGateway.get(key);

        assertThat(result).isNull();
        verify(this.jedis, times(1)).get(key);
    }
}