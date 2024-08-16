package com.br.walletwise.application.usecasesimpl.cache;

import com.br.walletwise.application.gateway.cache.DeleteCacheGateway;
import com.br.walletwise.usecase.cache.DeleteCache;

public class DeleteCacheImpl implements DeleteCache {
    private final DeleteCacheGateway deleteCacheGateway;

    public DeleteCacheImpl(DeleteCacheGateway deleteCacheGateway) {
        this.deleteCacheGateway = deleteCacheGateway;
    }

    @Override
    public void delete(String key) {
        this.deleteCacheGateway.delete(key);
    }
}
