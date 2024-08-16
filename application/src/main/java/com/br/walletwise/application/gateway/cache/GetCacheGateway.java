package com.br.walletwise.application.gateway.cache;

import java.util.List;

public interface GetCacheGateway<T> {
    List<T> get(String key);
}
