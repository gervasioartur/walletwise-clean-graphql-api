package com.br.walletwise.application.usecasesimpl.cache;

import com.br.walletwise.application.gateway.cache.GetCacheGateway;
import com.br.walletwise.usecase.cache.GetCache;

import java.util.LinkedHashMap;
import java.util.List;

public class GetCacheImpl implements GetCache {
    private final GetCacheGateway getCacheGateway;

    public GetCacheImpl(GetCacheGateway getCacheGateway) {
        this.getCacheGateway = getCacheGateway;
    }

    @Override
    public List<LinkedHashMap<String, Object>> get(String key) {
        return this.getCacheGateway.get(key);
    }
}
