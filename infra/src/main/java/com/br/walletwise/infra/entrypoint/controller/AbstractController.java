package com.br.walletwise.infra.entrypoint.controller;

import com.br.walletwise.core.validation.ValidationComposite;
import com.br.walletwise.core.validation.validator.contract.Validator;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class AbstractController<T,E> {
    public abstract ResponseEntity<T> perform(E request);

    protected List<Validator> buildValidators(E request) {
        return List.of();
    }

    protected String validate(E request) {
        List<Validator> validators = this.buildValidators(request);
        return new ValidationComposite(validators).validate();
    }
}
