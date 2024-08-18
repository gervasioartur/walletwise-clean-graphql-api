package com.br.walletwise.core.domain.entity;

import com.br.walletwise.core.validation.ValidationComposite;
import com.br.walletwise.core.validation.validator.contract.Validator;

import java.util.List;

public class AbstractEntity {
    protected List<Validator> buildValidators() {
        return List.of();
    }

    protected String validate() {
        List<Validator> validators = this.buildValidators();
        return new ValidationComposite(validators).validate();
    }
}
