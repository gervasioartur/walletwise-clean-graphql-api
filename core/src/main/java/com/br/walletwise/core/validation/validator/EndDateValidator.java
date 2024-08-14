package com.br.walletwise.core.validation.validator;

import java.util.Date;

public class EndDateValidator extends AbstractValidator {
    private final String returnMessage;
    private final Date startDate;

    public EndDateValidator(Object fieldValue,Date startDate) {
        this.fieldValue = fieldValue;
        this.startDate = startDate;
        this.returnMessage = "End date must be after start date.";
    }

    @Override
    public String validate() {
        Date endDate = (Date) fieldValue;
        if (endDate.before(startDate))
            return returnMessage;
        return null;
    }
}