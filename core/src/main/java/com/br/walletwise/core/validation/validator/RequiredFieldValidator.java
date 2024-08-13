package com.br.walletwise.core.validation.validator;

public class RequiredFieldValidator extends AbstractValidator {
    private final String returnMessage;

    public RequiredFieldValidator(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.returnMessage = this.fieldName + " is required.";
    }

    @Override
    public String validate() {
        return switch (this.fieldValue) {
            case String s -> s.trim().isEmpty() ? this.returnMessage : null;
            case null -> this.returnMessage;
            default -> null;
        };
    }
}