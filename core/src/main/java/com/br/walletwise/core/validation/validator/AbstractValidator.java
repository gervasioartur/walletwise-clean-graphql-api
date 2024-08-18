package com.br.walletwise.core.validation.validator;

import com.br.walletwise.core.validation.validator.contract.Validator;

public abstract class AbstractValidator implements Validator {
    protected String fieldName;
    protected Object fieldValue;

    @Override
    public String validate() {
        return null;
    }
}
