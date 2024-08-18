package com.br.walletwise.core.validation.validator;

import com.br.walletwise.core.domain.enums.CategoryEnum;

public class CategoryValidator extends AbstractValidator {
    private final String returnMessage;

    public CategoryValidator(Object fieldValue) {
        this.fieldValue = fieldValue;
        this.returnMessage = "Category is invalid. These are available categories : "
                + CategoryEnum.RENT.getValue() + "," + CategoryEnum.SCHOOL.getValue();
    }

    @Override
    public String validate() {
        String category = (String) fieldValue;
        if (!CategoryEnum.RENT.getValue().equalsIgnoreCase(category)
                && !CategoryEnum.SCHOOL.getValue().equalsIgnoreCase(category))
            return returnMessage;
        return null;
    }
}