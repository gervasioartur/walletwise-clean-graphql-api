package com.br.walletwise.core.validation;

import com.br.walletwise.core.validation.validator.*;
import com.br.walletwise.core.validation.validator.contract.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationBuilder {
    private final List<Validator> validators = new ArrayList<>();
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

    public ValidationBuilder category() {
        this.validators.add(new CategoryValidator(this.fieldValue));
        return this;
    }

    public ValidationBuilder endDate(Date start) {
        this.validators.add(new EndDateValidator(this.fieldValue, start));
        return this;
    }

    public ValidationBuilder dueDay() {
        this.validators.add(new DueDayValidator(this.fieldValue));
        return this;
    }

    public ValidationBuilder amount() {
        this.validators.add(new AmountValidator(this.fieldValue));
        return this;
    }

    public List<Validator> build() {
        return this.validators;
    }
}
