package com.br.walletwise.core.validation.validator;

import com.br.walletwise.core.domain.model.GeneralEnumString;
import com.br.walletwise.core.validation.AbstractValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends AbstractValidator {
    private final String returnMessage;

    public EmailValidator(Object fieldValue) {
        this.fieldValue = fieldValue;
        this.returnMessage = "E-mail is invalid.";
    }

    @Override
    public String validate() {
        String value = (String) this.fieldValue;
        value = value.trim();

        String regex = GeneralEnumString.EMAIL_REGEX_EXPRESSION.getValue();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) return this.returnMessage;
        return null;
    }
}