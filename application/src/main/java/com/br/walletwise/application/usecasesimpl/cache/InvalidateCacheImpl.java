package com.br.walletwise.application.usecasesimpl.cache;

import com.br.walletwise.application.gateway.cache.DeleteCacheGateway;
import com.br.walletwise.usecase.cache.InvalidateCache;

public class InvalidateCacheImpl implements InvalidateCache {
    private final DeleteCacheGateway deleteCacheGateway;

    public InvalidateCacheImpl(DeleteCacheGateway deleteCacheGateway) {
        this.deleteCacheGateway = deleteCacheGateway;
    }

    @Override
    public void delete(String key) {
        this.deleteCacheGateway.delete(key);
    }
}
