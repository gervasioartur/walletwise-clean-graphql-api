package com.br.walletwise.usecase.cache;

import java.util.List;

public interface GetCache<T> {
    List<T> get(String key);
}