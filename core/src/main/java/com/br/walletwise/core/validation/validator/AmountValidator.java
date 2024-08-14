package com.br.walletwise.core.validation.validator;

import java.math.BigDecimal;

public class AmountValidator extends AbstractValidator {
    private final String returnMessage;

    public AmountValidator(Object fieldValue) {
        this.fieldValue = fieldValue;
        this.returnMessage = "Invalid value for amount.";
    }

    @Override
    public String validate() {
        BigDecimal amount = (BigDecimal) fieldValue;
        if (amount.doubleValue() < 0) return returnMessage;
        return null;
    }
}