package com.br.walletwise.usecase.cache;

import java.util.LinkedHashMap;
import java.util.List;

public interface GetCache {
    List<LinkedHashMap<String, Object>> get(String key);
}