package com.br.walletwise.core.entity;

import com.br.walletwise.core.domain.entity.Expense;
import com.br.walletwise.core.domain.model.CategoryEnum;
import com.br.walletwise.core.domain.model.ExpenseTypeEnum;
import com.br.walletwise.core.exception.DomainException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.UUID;

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
                new BigDecimal(200),
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
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User info is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if description is empty or null on build with all arguments")
    void shouldThrowDomainExceptionIfDescriptionIsEmptyOrNullOnBuildWithAllArguments(String description) {
        Throwable exception = catchThrowable(() -> new Expense(
                null,
                UUID.randomUUID(),
                description,
                CategoryEnum.RENT.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Description is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if description is empty or null on build with no id")
    void shouldThrowDomainExceptionIfDescriptionIsEmptyOrNullOnBuildWithNoId(String description) {
        Throwable exception = catchThrowable(() -> new Expense(
                UUID.randomUUID(),
                description,
                CategoryEnum.RENT.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Description is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if description is empty or null on update description")
    void shouldThrowDomainExceptionIfDescriptionIsEmptyOrNullOnUpdateDescription(String description) {
        Expense expense = new Expense(
                UUID.randomUUID(),
                faker.lorem().word(),
                CategoryEnum.RENT.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        Throwable exception = catchThrowable(() -> expense.setDescription(description));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Description is required.");
    }

    @Test
    @DisplayName("Should return null when trying to update description with correct values")
    void shouldReturnNullWhenTryingToUpdateDescriptionWithCorrectValues() {
        Expense expense = new Expense(
                UUID.randomUUID(),
                faker.lorem().word(),
                CategoryEnum.RENT.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        String updatedDescription = faker.lorem().paragraph();
        expense.setDescription(updatedDescription);

        assertThat(expense.getDescription()).isEqualTo(updatedDescription);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if category empty or null on build with all arguments")
    void shouldThrowDomainExceptionIfCategoryIsNullOnBuildWithAllArguments(String category) {
        Throwable exception = catchThrowable(() -> new Expense(
                null,
                UUID.randomUUID(),
                faker.lorem().word(),
                category,
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if category empty or null on build with no id")
    void shouldThrowDomainExceptionIfCategoryIsNullOnBuildWithNoId(String category) {
        Throwable exception = catchThrowable(() -> new Expense(
                UUID.randomUUID(),
                faker.lorem().word(),
                category,
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if category is empty or null on update category")
    void shouldThrowDomainExceptionIfIsCategoryIsNullOnUpdateCategory(String category) {
        Expense expense = new Expense(
                UUID.randomUUID(),
                faker.lorem().word(),
                CategoryEnum.RENT.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        Throwable exception = catchThrowable(() -> expense.setCategory(category) );

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is required.");
    }

    @Test
    @DisplayName("Should return null when trying to update category with correct values")
    void shouldReturnNullWhenTryingToUpdateCategoryWithCorrectValues() {
        Expense expense = new Expense(
                UUID.randomUUID(),
                faker.lorem().word(),
                CategoryEnum.RENT.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        String updatedCategory = CategoryEnum.SCHOOL.getValue();
        expense.setCategory(updatedCategory);

        assertThat(expense.getCategory()).isEqualTo(updatedCategory);
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_category","invalid_category"})
    @DisplayName("Should throw DomainException if category is invalid on build with all arguments")
    void shouldThrowDomainExceptionIfCategoryIsInvalidOnBuildWithAllArguments(String category) {
        Throwable exception = catchThrowable(() ->  new Expense(
                null,
                UUID.randomUUID(),
                faker.lorem().word(),
                category,
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true) );

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage())
                .isEqualTo("Category is invalid. These are available categories : "
                +  CategoryEnum.RENT.getValue() +"," + CategoryEnum.SCHOOL.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_category","invalid_category"})
    @DisplayName("Should throw DomainException if category is invalid on build with no id")
    void shouldThrowDomainExceptionIfCategoryIsInvalidOnBuildWithNoId(String category) {
        Throwable exception = catchThrowable(() ->  new Expense(
                UUID.randomUUID(),
                faker.lorem().word(),
                category,
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true) );

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage())
                .isEqualTo("Category is invalid. These are available categories : "
                        +  CategoryEnum.RENT.getValue() +"," + CategoryEnum.SCHOOL.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_category","invalid_category"})
    @DisplayName("Should throw DomainException if category is invalid on update category")
    void shouldThrowDomainExceptionIfCategoryIsInvalidOnUpdateCategory(String category) {
        Expense expense = new Expense(
                UUID.randomUUID(),
                faker.lorem().word(),
                CategoryEnum.RENT.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        Throwable exception = catchThrowable(() -> expense.setCategory(category));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage())
                .isEqualTo("Category is invalid. These are available categories : "
                        +  CategoryEnum.RENT.getValue() +"," + CategoryEnum.SCHOOL.getValue());
    }

    @ParameterizedTest
    @ValueSource(longs = {0})
    @DisplayName("Should throw DomainException if amount is zero(0) on build with all arguments")
    void shouldThrowDomainExceptionIfAmountIsZeroOnBuildWithAllArguments(Long amount) {
        Throwable exception = catchThrowable(() ->  new Expense(
                null,
                UUID.randomUUID(),
                faker.lorem().word(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(amount),
                true) );

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Amount is required.");
    }

    @ParameterizedTest
    @ValueSource(longs = {0})
    @DisplayName("Should throw DomainException if amount is zero(0) on build with no id")
    void shouldThrowDomainExceptionIfAmountIsZeroOnBuildWithNoId(Long amount) {
        Throwable exception = catchThrowable(() ->  new Expense(
                UUID.randomUUID(),
                faker.lorem().word(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(amount),
                true) );

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Amount is required.");
    }

    @ParameterizedTest
    @ValueSource(longs = {0})
    @DisplayName("Should throw DomainException if amount is zero(0) on update amount")
    void shouldThrowDomainExceptionIfAmountIsZeroOnUpdateAmount(Long amount) {
        Expense expense = new Expense(
                UUID.randomUUID(),
                faker.lorem().word(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        Throwable exception = catchThrowable(() -> expense.setAmount(new BigDecimal(amount)));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Amount is required.");
    }

    @Test
    @DisplayName("Should return null when trying to update amount with correct values")
    void shouldReturnNullWhenTryingToUpdateAmountWithCorrectValues() {
        Expense expense = new Expense(
                UUID.randomUUID(),
                faker.lorem().word(),
                CategoryEnum.RENT.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        BigDecimal updatedAmount = new BigDecimal(200);
        expense.setAmount(updatedAmount);

        assertThat(expense.getAmount()).isEqualTo(updatedAmount);
    }
}