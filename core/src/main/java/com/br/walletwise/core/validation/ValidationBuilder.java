package com.br.walletwise.core.validation;

import com.br.walletwise.core.validation.validator.EmailValidator;
import com.br.walletwise.core.validation.validator.RequiredFieldValidator;
import com.br.walletwise.core.validation.validator.UsernameValidator;
import com.br.walletwise.core.validation.validator.contracts.IValidator;

import java.util.ArrayList;
import java.util.List;

public class ValidationBuilder {
    private final List<IValidator> validators = new ArrayList<>();
    private final String fieldName;
    private final Object fieldValue;

    private ValidationBuilder(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public static ValidationBuilder of(String fieldName, Object fieldValue) {
        return new ValidationBuilder(fieldName, fieldValue);
    }

    public ValidationBuilder required() {
        this.validators.add(new RequiredFieldValidator(this.fieldName, this.fieldValue));
        return this;
    }

    public ValidationBuilder username() {
        this.validators.add(new UsernameValidator(this.fieldValue));
        return this;
    }

    public ValidationBuilder email() {
        this.validators.add(new EmailValidator(this.fieldValue));
        return this;
    }

    public List<IValidator> build() {
        return this.validators;
    }
}
