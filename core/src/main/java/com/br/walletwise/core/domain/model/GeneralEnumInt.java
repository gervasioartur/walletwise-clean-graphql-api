package com.br.walletwise.core.domain.model;

public enum GeneralEnumInt {
    JWT_TOKEN_EXPIRATION(1);

    private final int value;

    GeneralEnumInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
