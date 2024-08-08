package com.br.walletwise.core.validation.validators;

import com.br.walletwise.core.domain.enums.GeneralEnumString;
import com.br.walletwise.core.validation.AbstractValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator extends AbstractValidator {
    private final String returnMessage;

    public PasswordValidator(Object fieldValue) {
        this.fieldValue = fieldValue;
        this.returnMessage = "Password too weak! Must contain at least 8 characters," +
                "one uppercase letter, a special character and a number.";
    }

    @Override
    public String validate() {
        String value = (String) this.fieldValue;
        value = value.trim();

        String regex = GeneralEnumString.PASSWORD_REGEX_EXPRESSION.getValue();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches())
            return this.returnMessage;
        return null;
    }
}