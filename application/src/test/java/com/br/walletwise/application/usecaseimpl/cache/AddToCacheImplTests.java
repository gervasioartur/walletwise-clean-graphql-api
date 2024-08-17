package com.br.walletwise.application.usecaseimpl.cache;

import com.br.walletwise.application.gateway.cache.AddToCacheGateway;
import com.br.walletwise.application.usecasesimpl.cache.AddToCacheImpl;
import com.br.walletwise.usecase.cache.AddToCache;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AddToCacheImplTests {
    @Test
    @DisplayName("Should add value with ket to cache")
    void shouldAddValueWithKetToCache() {
        String key = "key";
        String value = "value";

        AddToCacheGateway addToCacheGateway = mock(AddToCacheGateway.class);
        AddToCache addToCache = new AddToCacheImpl(addToCacheGateway);

        addToCache.add(key, value);

        verify(addToCacheGateway, times(1)).add(key, value);
    }
}
