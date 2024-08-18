package com.br.walletwise.application.usecasesimpl.cache;

import com.br.walletwise.application.gateway.cache.InvalidateCacheGateway;
import com.br.walletwise.usecase.cache.InvalidateCache;

public class InvalidateCacheImpl implements InvalidateCache {
    private final InvalidateCacheGateway invalidateCacheGateway;

    public InvalidateCacheImpl(InvalidateCacheGateway invalidateCacheGateway) {
        this.invalidateCacheGateway = invalidateCacheGateway;
    }

    @Override
    public void delete(String key) {
        this.invalidateCacheGateway.delete(key);
    }
}
