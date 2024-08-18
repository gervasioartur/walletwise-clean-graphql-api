package com.br.walletwise.application.usecasesimpl.cache;

import com.br.walletwise.application.gateway.cache.GetCacheGateway;
import com.br.walletwise.usecase.cache.GetCache;

import java.util.List;

public class GetCacheImpl<T> implements GetCache<T> {
    private final GetCacheGateway<T> getCacheGateway;

    public GetCacheImpl(GetCacheGateway getCacheGateway) {
        this.getCacheGateway = getCacheGateway;
    }

    @Override
    public List<T> get(String key) {
        return this.getCacheGateway.get(key);
    }
}
