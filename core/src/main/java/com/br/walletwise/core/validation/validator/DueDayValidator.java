package com.br.walletwise.core.validation.validator;

public class DueDayValidator extends AbstractValidator {
    private final String returnMessage;

    public DueDayValidator(Object fieldValue) {
        this.fieldValue = fieldValue;
        this.returnMessage = "Due day must be between 1 and 31.";
    }

    @Override
    public String validate() {
        int dueDay = (int) fieldValue;

        if (dueDay < 1 || dueDay > 31)
            return returnMessage;
        return null;
    }
}