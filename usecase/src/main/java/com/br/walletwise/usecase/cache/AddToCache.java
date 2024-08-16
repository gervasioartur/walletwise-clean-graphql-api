package com.br.walletwise.usecase.cache;


public interface AddToCache<T> {
    void add (String key,T value);
}