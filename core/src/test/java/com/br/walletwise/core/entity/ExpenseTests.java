package com.br.walletwise.core.entity;

import com.br.walletwise.core.domain.entity.Expense;
import com.br.walletwise.core.domain.model.CategoryEnum;
import com.br.walletwise.core.domain.model.ExpenseTypeEnum;
import com.br.walletwise.core.exception.DomainException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseTests {
    private final Faker faker = new Faker();

    @Test
    @DisplayName("Should throw DomainException if user id is null on build with all arguments")
    void shouldThrowDomainExceptionIfUserIdIsNullOnBuildWithAllArguments() {
        Throwable exception = catchThrowable(() -> new Expense(
                null,
                null,
                faker.lorem().word(),
                CategoryEnum.RENT.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(faker.number().randomNumber()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User info is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if user info is null on build with no id")
    void shouldThrowDomainExceptionIfUserIdIsNullOnBuildWithNoId() {
        Throwable exception = catchThrowable(() -> new Expense(
                null,
                faker.lorem().word(),
                CategoryEnum.RENT.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(faker.number().randomNumber()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User info is required.");
    }

}