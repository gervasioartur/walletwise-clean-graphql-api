package com.br.walletwise.core.validation;

import com.br.walletwise.core.validation.validators.contracts.IValidator;

public abstract class AbstractValidator implements IValidator {
    protected String fieldName;
    protected Object fieldValue;

    @Override
    public String validate() {
        return null;
    }
}
