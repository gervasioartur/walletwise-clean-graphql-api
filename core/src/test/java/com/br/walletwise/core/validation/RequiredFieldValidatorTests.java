package com.br.walletwise.core.validation;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.validation.validator.RequiredFieldValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class RequiredFieldValidatorTests {
    private RequiredFieldValidator validator;

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should return error message if the field value is empty")
    void shouldReturnErrorMessageIfFieldValueIsEmpty(String fieldValue) {
        String fieName = "any_field_name";
        this.validator = new RequiredFieldValidator(fieName, fieldValue);
        String error = this.validator.validate();

        Assertions.assertThat(error).isNotEmpty();
        Assertions.assertThat(error).isEqualTo("any_field_name is required.");
    }


    @ParameterizedTest
    @ValueSource(doubles = {0.0})
    @DisplayName("Should return error message if the field value is zero")
    void shouldReturnErrorMessageIfFieldValueIsZero(double fieldValue) {
        String fieName = "any_field_name";
        this.validator = new RequiredFieldValidator(fieName, fieldValue);
        String error = this.validator.validate();

        Assertions.assertThat(error).isNotEmpty();
        Assertions.assertThat(error).isEqualTo("any_field_name is required.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0})
    @DisplayName("Should return error message if the field value is zero")
    void shouldReturnErrorMessageIfFieldValueIsZero(int fieldValue) {
        String fieName = "any_field_name";
        this.validator = new RequiredFieldValidator(fieName, fieldValue);
        String error = this.validator.validate();

        Assertions.assertThat(error).isNotEmpty();
        Assertions.assertThat(error).isEqualTo("any_field_name is required.");
    }

    @Test
    @DisplayName("Should return null on success string")
    void shouldReturnNullOnSuccessString() {
        String fieName = "any_field_name";
        String fieldValue = "any_value";

        this.validator = new RequiredFieldValidator(fieName, fieldValue);
        String error = this.validator.validate();

        Assertions.assertThat(error).isNull();
    }

    @Test
    @DisplayName("Should return null on success double value")
    void shouldReturnNullOnSuccessDoubleValue() {
        String fieName = "any_field_name";
        double fieldValue = 20.0;

        this.validator = new RequiredFieldValidator(fieName, fieldValue);
        String error = this.validator.validate();

        Assertions.assertThat(error).isNull();
    }

    @Test
    @DisplayName("Should return null on success in value")
    void shouldReturnNullOnSuccessIntValue() {
        String fieName = "any_field_name";
        int fieldValue = 20;

        this.validator = new RequiredFieldValidator(fieName, fieldValue);
        String error = this.validator.validate();

        Assertions.assertThat(error).isNull();
    }

    @Test
    @DisplayName("Should return null on success in value")
    void shouldReturnNullOnSuccessObjectValue() {
        String fieName = "any_field_name";
        Object fieldValue = User.class;

        this.validator = new RequiredFieldValidator(fieName, fieldValue);
        String error = this.validator.validate();

        Assertions.assertThat(error).isNull();
    }

}
