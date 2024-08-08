package com.br.walletwise.core.validation;

public abstract class AbstractValidator implements IValidator{
    protected String fieldName;
    protected Object fieldValue;

    @Override
    public String validate() {
        return null;
    }
}
