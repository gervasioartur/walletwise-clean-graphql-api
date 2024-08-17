package com.br.walletwise.application.usecaseimpl.cache;

import com.br.walletwise.application.gateway.cache.GetCacheGateway;
import com.br.walletwise.application.usecasesimpl.cache.GetCacheImpl;
import com.br.walletwise.usecase.cache.GetCache;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GetCacheImplTests {
    @Test
    @DisplayName("Should return values from cache")
    void shouldValueFromCache() {
        String key = "key";
        GetCacheGateway getCacheGateway = mock(GetCacheGateway.class);
        when(getCacheGateway.get(key)).thenReturn(List.of());
        GetCache getCache = new GetCacheImpl(getCacheGateway);
        var result = getCache.get(key);
        assertThat(result.size()).isEqualTo(0);
        verify(getCacheGateway, times(1)).get(key);
    }
}
