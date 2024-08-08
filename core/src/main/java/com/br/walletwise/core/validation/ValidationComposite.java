package com.br.walletwise.core.validation;

import com.br.walletwise.core.exception.DomainException;

import java.util.List;

public class ValidationComposite implements IValidator{
    private final List<IValidator> validators;

    public ValidationComposite(List<IValidator> validators) {
        this.validators = validators;
    }

    @Override
    public String validate() {
       for (IValidator validator : validators) {
           String error = validator.validate();
           if (error != null) {
               return error;
           }
       }
       return null;
    }
}
