package com.br.walletwise.core.entity;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.model.CategoryEnum;
import com.br.walletwise.core.domain.model.ExpenseTypeEnum;
import com.br.walletwise.core.exception.DomainException;
import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class FixedExpenseTests {
    private final Faker faker = new Faker();

    @Test
    @DisplayName("Should throw Domain exception if start date is null on build with all arguments")
    void shouldThrowDomainExceptionIfStartDateIsNullOnBuildWithAllArguments() {
        Throwable exception = Assertions.catchThrowable(() -> new FixedExpense(
                null,
                faker.number().randomNumber(),
                UUID.randomUUID(),
                faker.lorem().word(),
                CategoryEnum.RENT.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true,null, Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant())));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Start date is required.");
    }
}
