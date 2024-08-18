package com.br.walletwise.application.usecaseimpl.cache;

import com.br.walletwise.application.gateway.cache.InvalidateCacheGateway;
import com.br.walletwise.application.usecasesimpl.cache.InvalidateCacheImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class InvalidateCacheImplTests {
    @Test
    @DisplayName("Should invalidate cache")
    void shouldInvalidateCache() {
        String key = "key";
        InvalidateCacheGateway invalidateCacheGateway = mock(InvalidateCacheGateway.class);
        InvalidateCacheImpl invalidateCacheImpl = new InvalidateCacheImpl(invalidateCacheGateway);
        invalidateCacheImpl.delete(key);
        verify(invalidateCacheGateway, times(1)).delete(key);
    }
}
