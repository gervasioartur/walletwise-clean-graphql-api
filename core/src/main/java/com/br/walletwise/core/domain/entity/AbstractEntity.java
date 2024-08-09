package com.br.walletwise.core.domain.entity;

import com.br.walletwise.core.validation.ValidationComposite;
import com.br.walletwise.core.validation.validator.contracts.IValidator;

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
