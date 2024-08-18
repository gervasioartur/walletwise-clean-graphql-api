package com.br.walletwise.application.gateway.cache;

public interface AddToCacheGateway<T> {
    void add(String key, T value);
}
