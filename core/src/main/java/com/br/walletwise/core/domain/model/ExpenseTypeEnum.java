package com.br.walletwise.core.domain.model;

public enum ExpenseTypeEnum {
    FIXED("FIXED"),
    VARIABLE("VARIABLE");

    private final String value;

    ExpenseTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
