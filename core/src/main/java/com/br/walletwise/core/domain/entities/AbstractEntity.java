package com.br.walletwise.core.domain.entities;

import com.br.walletwise.core.validation.validators.contracts.IValidator;
import com.br.walletwise.core.validation.ValidationComposite;

import java.util.List;

public class AbstractEntity {
    protected List<IValidator> buildValidators() {
        return List.of();
    }

    protected String validate() {
        List<IValidator> validators = this.buildValidators();
        return new ValidationComposite(validators).validate();
    }
}
