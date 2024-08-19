package com.br.walletwise.application.gateway.cache;

import java.util.LinkedHashMap;
import java.util.List;

public interface GetCacheGateway {
    List<LinkedHashMap<String, Object>> get(String key);
}
