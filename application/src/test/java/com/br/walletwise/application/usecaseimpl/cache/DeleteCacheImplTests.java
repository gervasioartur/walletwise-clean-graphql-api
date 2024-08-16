package com.br.walletwise.application.usecaseimpl.cache;

import com.br.walletwise.application.gateway.cache.DeleteCacheGateway;
import com.br.walletwise.application.usecasesimpl.cache.DeleteCacheImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DeleteCacheImplTests {
    @Test
    @DisplayName("Should invalidate cache")
    void shouldInvalidateCache() {
        String key = "key";
        DeleteCacheGateway  deleteCacheGateway = mock(DeleteCacheGateway.class);
        DeleteCacheImpl deleteCacheImpl = new DeleteCacheImpl(deleteCacheGateway);
        deleteCacheImpl.delete(key);
        verify(deleteCacheGateway, times(1)).delete(key);
    }
}
