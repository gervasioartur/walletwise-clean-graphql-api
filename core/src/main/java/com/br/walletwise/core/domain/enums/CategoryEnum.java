package com.br.walletwise.core.domain.enums;

public enum CategoryEnum {
    SCHOOL("SCHOOL"),
    RENT("RENT");

    private final String value;

    CategoryEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
