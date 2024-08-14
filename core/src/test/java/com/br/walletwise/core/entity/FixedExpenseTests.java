package com.br.walletwise.core.entity;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.model.CategoryEnum;
import com.br.walletwise.core.domain.model.ExpenseTypeEnum;
import com.br.walletwise.core.exception.DomainException;
import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
                0,
                20,
                null,
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Start date is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if start date is null on build with no id")
    void shouldThrowDomainExceptionIfStartDateIsNullOnBuildWithNoId() {
        Throwable exception = Assertions.catchThrowable(() -> new FixedExpense(
                20,
                null,
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                 01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Start date is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if start date is null on update start date")
    void shouldThrowDomainExceptionIfStartDateIsNullOnUpdateStartDate() {
        FixedExpense fixedExpense =  new FixedExpense(
                20,
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        Throwable exception = Assertions.catchThrowable(() -> fixedExpense.setStartDate(null));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Start date is required.");
    }

    @Test
    @DisplayName("Should return null if start date is not null on update stat date")
    void shouldReturnNullIfStartDateIsNullOnUpdateStatDate() {
        FixedExpense fixedExpense =  new FixedExpense(
                20,
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

            Date updatedStartDate =  new Date();
            fixedExpense.setStartDate(updatedStartDate);

        assertThat(fixedExpense.getStartDate()).isEqualTo(updatedStartDate);
    }

    @Test
    @DisplayName("Should throw Domain exception if end date is null on build with all arguments")
    void shouldThrowDomainExceptionIfEndDateIsNullOnBuildWithAllArguments() {
        Throwable exception = Assertions.catchThrowable(() -> new FixedExpense(
                0,
                20,
                new Date(),
                null,
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("End date is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if end date is null on build with no id")
    void shouldThrowDomainExceptionIfEndDateIsNullOnBuildWithNoId() {
        Throwable exception = Assertions.catchThrowable(() -> new FixedExpense(
                20,
                new Date(),
                null,
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("End date is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if end date is null on update end date")
    void shouldThrowDomainExceptionIfEndDateIsNullOnUpdateStartDate() {
        FixedExpense fixedExpense =  new FixedExpense(
                20,
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        Throwable exception = Assertions.catchThrowable(() -> fixedExpense.setEndDate(null));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("End date is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if end date is before start date")
    void shouldThrowDomainExceptionIfEndDateIsBeforeStartDate() {
        Date startDate = new Date();
        Date endDate = Date.from(LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        Throwable exception = Assertions.catchThrowable(() ->  new FixedExpense(
                20,
                startDate,
                endDate,
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("End date must be after start date.");
    }

    @Test
    @DisplayName("Should return null if end date is not null on update end date")
    void shouldReturnNullIfEndDateIsNullOnUpdateStatDate() {
        FixedExpense fixedExpense =  new FixedExpense(
                20,
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        Date updatedEndDate =  new Date();
        fixedExpense.setEndDate(updatedEndDate);

        assertThat(fixedExpense.getEndDate()).isEqualTo(updatedEndDate);
    }


    @Test
    @DisplayName("Should throw Domain exception if due day is zero(0) on build with all arguments")
    void shouldThrowDomainExceptionIfDueDayIsZeroOnBuildWithAllArguments() {
        Date startDate = new Date();
        Date endDate = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        Throwable exception = Assertions.catchThrowable(() -> new FixedExpense(
                0,
                0,
                startDate,
                endDate,
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Due day is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if due day is zero(0) on build with no id")
    void shouldThrowDomainExceptionIfDueDayIsZeroOnBuildWithNoId() {
        Date startDate = new Date();
        Date endDate = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());


        Throwable exception = Assertions.catchThrowable(() -> new FixedExpense(
                0,
                startDate,
                endDate,
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Due day is required.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,32})
    @DisplayName("Should throw DomainException if due day is invalid")
    void shouldThrowDomainExceptionIfDueDayIsInvalid(int dueDay) {
        Date startDate = new Date();
        Date endDate = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        Throwable exception = Assertions.catchThrowable(() ->  new FixedExpense(
                dueDay,
                startDate,
                endDate,
                32,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Due day must be between 1 and 31.");
    }
}