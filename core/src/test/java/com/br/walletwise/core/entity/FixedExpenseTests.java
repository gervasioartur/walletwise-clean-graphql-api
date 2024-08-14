package com.br.walletwise.core.entity;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.model.CategoryEnum;
import com.br.walletwise.core.exception.DomainException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class FixedExpenseTests {
    private final Faker faker = new Faker();

    @Test
    @DisplayName("Should throw DomainException if user id is null on build with all arguments")
    void shouldThrowDomainExceptionIfUserIdIsNullOnBuildWithAllArguments() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                0,
                null,
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User info is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if user id is null on build with no id")
    void shouldThrowDomainExceptionIfUserIdIsNullOnBuildWithNoId() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                null,
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User info is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if user id is null on update user id")
    void shouldThrowDomainExceptionIfUserIdIsNullOnUpdateUserId() {
        FixedExpense fixedExpense = new FixedExpense(
                0,
                UUID.randomUUID(),
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true);

        Throwable exception = catchThrowable(() -> fixedExpense.setUserId(null));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User info is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if description is null or empty on build with all arguments")
    void shouldThrowDomainExceptionIfDescriptionIsNullOrEmptyOnBuildWithAllArguments(String description) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                0,
                UUID.randomUUID(),
                description,
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Description is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if description is null or empty on build with no Id")
    void shouldThrowDomainExceptionIfDescriptionIsNullOrEmptyOnBuildWithNoId(String description) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                UUID.randomUUID(),
                description,
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Description is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if description is null or empty on update description")
    void shouldThrowDomainExceptionIfDescriptionIsNullOrEmptyOnUpdateDescription(String description) {
        FixedExpense fixedExpense = new FixedExpense(
                UUID.randomUUID(),
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true);

        Throwable exception = catchThrowable(() -> fixedExpense.setDescription(null));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Description is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if due day is zero(0) on build with all arguments")
    void shouldThrowDomainExceptionIfDueDayIsZeroOnBuildWithAllArguments() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                0,
                UUID.randomUUID(),
                faker.lorem().word(),
                0,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Due day is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if due day is zero(0) on build with no id")
    void shouldThrowDomainExceptionIfDueDayIsZeroOnBuildWithNoId() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                UUID.randomUUID(),
                faker.lorem().word(),
                0,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Due day is required.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,32})
    @DisplayName("Should throw DomainException if due day is invalid on build with all arguments")
    void shouldThrowDomainExceptionIfDueDayIsInvalidOnBuildWithAllArguments(int dueDay) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                0,
                UUID.randomUUID(),
                faker.lorem().word(),
                dueDay,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Due day must be between 1 and 31.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,32})
    @DisplayName("Should throw DomainException if due day is invalid on build with no id")
    void shouldThrowDomainExceptionIfDueDayIsInvalidOnBuildWithNoId(int dueDay) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                UUID.randomUUID(),
                faker.lorem().word(),
                dueDay,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Due day must be between 1 and 31.");
    }



    @ParameterizedTest
    @ValueSource(ints = {-1,32})
    @DisplayName("Should throw DomainException if due day is invalid on update due day")
    void shouldThrowDomainExceptionIfDueDayIsInvalidOnUpdateDueDay(int dueDay) {
        FixedExpense fixedExpense =  new FixedExpense(
                UUID.randomUUID(),
                faker.lorem().word(),
                23,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true);

        Throwable exception = catchThrowable(() -> fixedExpense.setDueDay(dueDay));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Due day must be between 1 and 31.");
    }


    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if category is null or empty on build with all arguments")
    void shouldThrowDomainExceptionIfDueDayIsZeroOnBuildWithAllArguments(String category) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                0,
                UUID.randomUUID(),
                faker.lorem().word(),
                12,
                category,
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if category is null or empty on build with no id")
    void shouldThrowDomainExceptionIfDueDayIsZeroOnBuildWithNoId(String category) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                UUID.randomUUID(),
                faker.lorem().word(),
                12,
                category,
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if category is null or empty on update category")
    void shouldThrowDomainExceptionIfCategoryOnUpdateCategory(String category) {
        FixedExpense fixedExpense = new FixedExpense(
                UUID.randomUUID(),
                faker.lorem().word(),
                12,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true);

        Throwable exception = catchThrowable(() -> fixedExpense.setCategory(category));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is required.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid_category","category", "any_category"})
    @DisplayName("Should throw DomainException if category is invalid on build with all arguments")
    void shouldThrowDomainExceptionIfCategoryIsInvalidOnBuildWithAllArguments(String category) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                0,
                UUID.randomUUID(),
                faker.lorem().word(),
                12,
                category,
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is invalid. These are available categories : " +
                          CategoryEnum.RENT.getValue() + ","+ CategoryEnum.SCHOOL.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid_category","category", "any_category"})
    @DisplayName("Should throw DomainException if category is invalid on build with no id")
    void shouldThrowDomainExceptionIfCategoryIsInvalidOnBuildWithNoId(String category) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                UUID.randomUUID(),
                faker.lorem().word(),
                12,
                category,
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is invalid. These are available categories : " +
                CategoryEnum.RENT.getValue() + ","+ CategoryEnum.SCHOOL.getValue());
    }









    @Test
    @DisplayName("Should throw Domain exception if start date is null on build with all arguments")
    void shouldThrowDomainExceptionIfStartDateIsNullOnBuildWithAllArguments() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                0,
                UUID.randomUUID(),
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                null,
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Start date is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if start date is null on build with no id")
    void shouldThrowDomainExceptionIfStartDateIsNullOnBuildWithNoId() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                UUID.randomUUID(),
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                null,
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Start date is required.");
    }

    @Test
    @DisplayName("Should build FixedExpense with correct values")
    void shouldBuildFixedExpenseWithCorrectValues() {
        long id = 1;
        UUID userId = UUID.randomUUID();
        int dueDay = 1;
        String description = faker.lorem().paragraph();
        String category = CategoryEnum.SCHOOL.getValue();
        BigDecimal amount = new BigDecimal(200);
        Date startDate = new Date();
        Date endDate = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        boolean active = true;

        FixedExpense fixedExpense = new FixedExpense
                (id,userId,description,dueDay,category,amount,startDate,endDate,active);

        assertThat(fixedExpense.getId()).isEqualTo(id);
        assertThat(fixedExpense.getUserId()).isEqualTo(userId);
        assertThat(fixedExpense.getDescription()).isEqualTo(description);
        assertThat(fixedExpense.getDueDay()).isEqualTo(dueDay);
        assertThat(fixedExpense.getCategory()).isEqualTo(category);
        assertThat(fixedExpense.getStartDate()).isEqualTo(startDate);
        assertThat(fixedExpense.getEndDate()).isEqualTo(endDate);
        assertThat(fixedExpense.isActive()).isEqualTo(active);
    }
}