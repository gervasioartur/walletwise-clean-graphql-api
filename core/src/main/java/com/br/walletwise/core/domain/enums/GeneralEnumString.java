package com.br.walletwise.core.domain.enums;

public enum GeneralEnumString {
    EMAIL_EXPRESSION("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private String value;

    GeneralEnumString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
