package com.br.walletwise.core.domain.enums;

public enum GeneralEnumString {
    PASSWORD_REGEX_EXPRESSION("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
    ),
    EMAIL_REGEX_EXPRESSION("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private final String value;

    GeneralEnumString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
