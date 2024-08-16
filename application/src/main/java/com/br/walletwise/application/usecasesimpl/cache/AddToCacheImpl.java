package com.br.walletwise.application.usecasesimpl.cache;

import com.br.walletwise.application.gateway.cache.AddToCacheGateway;
import com.br.walletwise.usecase.cache.AddToCache;


public class AddToCacheImpl<T> implements AddToCache<T> {
    private final AddToCacheGateway<T> addToCacheGateway;

    public AddToCacheImpl(AddToCacheGateway addToCacheGateway) {
        this.addToCacheGateway = addToCacheGateway;
    }

    @Override
    public void add(String key,T value) {
        this.addToCacheGateway.add(key, value);
    }
}