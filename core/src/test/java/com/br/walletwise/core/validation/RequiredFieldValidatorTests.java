package com.br.walletwise.core.validation;

import com.br.walletwise.core.validation.validator.RequiredFieldValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class RequiredFieldValidatorTests {
    private RequiredFieldValidator validator;

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should return error message if the field value is empty")
    void shouldReturnErrorMessageIfFieldValueIsEmpty(String fieldValue) {
        String fieName = "any_field_name";
        this.validator = new RequiredFieldValidator(fieName,fieldValue );
        String error  = this.validator.validate();

        Assertions.assertThat(error).isNotEmpty();
        Assertions.assertThat(error).isEqualTo("any_field_name is required.");
    }
}
