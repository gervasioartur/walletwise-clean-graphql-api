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

    @Test
    @DisplayName("Should build expense with correct values")
    void shouldBuildExpenseWithCorrectValues() {
        long id =  0;
        UUID userId =  UUID.randomUUID();
        String description = faker.lorem().word();
        String category = CategoryEnum.RENT.getValue();
        String type = ExpenseTypeEnum.FIXED.getValue();
        BigDecimal amount = new BigDecimal(200);
        boolean isActive = true;

        Expense expense = new Expense(id, userId, description, category, type, amount, isActive);

        assertThat(expense.getExpenseId()).isEqualTo(id);
        assertThat(expense.getUserId()).isEqualTo(userId);
        assertThat(expense.getDescription()).isEqualTo(description);
        assertThat(expense.getCategory()).isEqualTo(category);
        assertThat(expense.getType()).isEqualTo(type);
        assertThat(expense.getAmount()).isEqualTo(amount);
        assertThat(expense.isActive()).isEqualTo(isActive);
    }
}