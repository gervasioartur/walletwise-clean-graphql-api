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
    @DisplayName("Should return null if user id is valid")
    void shouldReturnNulIfUserIdIsValid() {
        UUID userId = UUID.randomUUID();

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

        fixedExpense.setUserId(userId);

        assertThat(fixedExpense.getUserId()).isEqualTo(userId);
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
                description,
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant())));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Description is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if description is null or empty on update description")
    void shouldThrowDomainExceptionIfDescriptionIsNullOrEmptyOnUpdateDescription(String description) {
        FixedExpense fixedExpense = new FixedExpense(
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()));

        Throwable exception = catchThrowable(() -> fixedExpense.setDescription(null));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Description is required.");
    }

    @Test
    @DisplayName("Should return null if description is valid on update description")
    void shouldReturnNUllIfDescriptionIsValidaOnUpdateDescription() {
        String description =  faker.lorem().word();

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

         fixedExpense.setDescription(description);

        assertThat(fixedExpense.getDescription()).isEqualTo(description);
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
                faker.lorem().word(),
                0,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant())));

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
                faker.lorem().word(),
                dueDay,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant())));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Due day must be between 1 and 31.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,32})
    @DisplayName("Should throw DomainException if due day is invalid on update due day")
    void shouldThrowDomainExceptionIfDueDayIsInvalidOnUpdateDueDay(int dueDay) {
        FixedExpense fixedExpense =  new FixedExpense(
                faker.lorem().word(),
                23,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()));

        Throwable exception = catchThrowable(() -> fixedExpense.setDueDay(dueDay));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Due day must be between 1 and 31.");
    }

    @Test
    @DisplayName("Should return null if due day is valid on update due day")
    void shouldReturnNUllIfDueDayIsValidaOnUpdateDueDay() {
        int dueDay =  20;

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

        fixedExpense.setDueDay(dueDay);

        assertThat(fixedExpense.getDueDay()).isEqualTo(dueDay);
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
                faker.lorem().word(),
                12,
                category,
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant())));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if category is null or empty on update category")
    void shouldThrowDomainExceptionIfCategoryOnUpdateCategory(String category) {
        FixedExpense fixedExpense = new FixedExpense(
                faker.lorem().word(),
                12,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()));

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
                faker.lorem().word(),
                12,
                category,
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant())));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is invalid. These are available categories : " +
                CategoryEnum.RENT.getValue() + ","+ CategoryEnum.SCHOOL.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid_category","category", "any_category"})
    @DisplayName("Should throw DomainException if category is invalid on update category")
    void shouldThrowDomainExceptionIfCategoryIsInvalidOnUpdateCategory(String category) {
        FixedExpense fixedExpense = new FixedExpense(
                faker.lorem().word(),
                12,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()));

        Throwable exception = catchThrowable(() -> fixedExpense.setCategory(category));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is invalid. These are available categories : " +
                CategoryEnum.RENT.getValue() + ","+ CategoryEnum.SCHOOL.getValue());
    }

    @Test
    @DisplayName("Should return null if category is valid on update category")
    void shouldReturnNUllIfCategoryIsValidaOnUpdateCategory() {
        String category =  CategoryEnum.RENT.getValue();

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

        fixedExpense.setCategory(category);

        assertThat(fixedExpense.getCategory()).isEqualTo(category);
    }

    @Test
    @DisplayName("Should throw DomainException if amount is zero on build with all arguments")
    void shouldThrowDomainExceptionIfAmountIsZeroOnBuildWithAllArguments() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                0,
                UUID.randomUUID(),
                faker.lorem().word(),
                12,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(0),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Amount is required.");
    }


    @Test
    @DisplayName("Should throw DomainException if amount is zero on build with no id")
    void shouldThrowDomainExceptionIfAmountIsZeroOnBuildWithNoId() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                faker.lorem().word(),
                12,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(0),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant())));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Amount is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if amount is zero on update amount")
    void shouldThrowDomainExceptionIfAmountIsZeroOnUpdateAmount() {
        FixedExpense fixedExpense =  new FixedExpense(
                faker.lorem().word(),
                12,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()));

        Throwable exception = catchThrowable(() -> fixedExpense.setAmount(new BigDecimal(0)));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Amount is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if amount is less than zero on build with all arguments")
    void shouldThrowDomainExceptionIfAmountIsLessThanZeroOnBuildWithAllArguments() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                0,
                UUID.randomUUID(),
                faker.lorem().word(),
                12,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(-1),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Invalid value for amount.");
    }

    @Test
    @DisplayName("Should throw DomainException if amount is less than zero on build with no id")
    void shouldThrowDomainExceptionIfAmountIsLessThanZeroOnBuildWithNoId() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                faker.lorem().word(),
                12,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(-1),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant())));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Invalid value for amount.");
    }

    @Test
    @DisplayName("Should throw DomainException if amount is less than zero on update amount")
    void shouldThrowDomainExceptionIfAmountIsLessThanZeroOnUpdateAmount() {
        FixedExpense fixedExpense = new FixedExpense(
                faker.lorem().word(),
                12,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()));

        Throwable exception = catchThrowable(() -> fixedExpense.setAmount(new BigDecimal(-1)));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Invalid value for amount.");
    }

    @Test
    @DisplayName("Should return null if amount is valid on update amount")
    void shouldReturnNullIfAmountIsValidOnUpdateAmount() {
        BigDecimal amount = new BigDecimal(200);

        FixedExpense fixedExpense = new FixedExpense(
                0,
                UUID.randomUUID(),
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                amount,
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()),
                true);

        fixedExpense.getAmount();

        assertThat(fixedExpense.getAmount()).isEqualTo(amount);
    }

    @Test
    @DisplayName("Should return null if amount is valid on update amount")
    void shouldReturnNUllIfAmountIsValidaOnUpdateAmount() {
        BigDecimal amount =  new BigDecimal(300);

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

        fixedExpense.setAmount(amount);

        assertThat(fixedExpense.getAmount()).isEqualTo(amount);
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
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                null,
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant())));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Start date is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if start date is null on update start date")
    void shouldThrowDomainExceptionIfStartDateIsNullOnUpdateStartDate() {

        FixedExpense fixedExpense = new FixedExpense(
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()));

        Throwable exception = catchThrowable(() -> fixedExpense.setStartDate(null));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Start date is required.");
    }

    @Test
    @DisplayName("Should return null if start date is valid")
    void shouldReturnNullIfStartDateIsValid() {
        Date startDate = new Date();

        FixedExpense fixedExpense = new FixedExpense(
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()));

        fixedExpense.setStartDate(startDate);
        assertThat(fixedExpense.getStartDate()).isEqualTo(startDate);
    }

    @Test
    @DisplayName("Should throw Domain exception if end date is null on build with all arguments")
    void shouldThrowDomainExceptionIfEndDateIsNullOnBuildWithAllArguments() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                0,
                UUID.randomUUID(),
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                null,
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("End date is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if end date is null on build with no id")
    void shouldThrowDomainExceptionIfEndDateIsNullOnBuildWithNoId() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                null));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("End date is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if End date is null on update end date")
    void shouldThrowDomainExceptionIfEndDateIsNullOnUpdateENdDate() {

        FixedExpense fixedExpense = new FixedExpense(
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant()));

        Throwable exception = catchThrowable(() -> fixedExpense.setEndDate(null));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("End date is required.");
    }

    @Test
    @DisplayName("Should throw Domain exception if end date is invalid on build with all arguments")
    void shouldThrowDomainExceptionIfEndDateIsInvalidOnBuildWithAllArguments() {
        Date endDate = Date.from(LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        Throwable exception = catchThrowable(() -> new FixedExpense(
                0,
                UUID.randomUUID(),
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                endDate,
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("End date must be after start date.");
    }

    @Test
    @DisplayName("Should throw DomainException if end date is invalid on build with no id")
    void shouldThrowDomainExceptionIfEndDateIsInvalidOnBuildWithNoId() {
        Date endDate = Date.from(LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        Throwable exception = catchThrowable(() -> new FixedExpense(
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                endDate));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("End date must be after start date.");
    }

    @Test
    @DisplayName("Should throw DomainException if End date is invalid on update end date")
    void shouldThrowDomainExceptionIfEndDateIsInvalidOnUpdateENdDate() {
        Date endDate = Date.from(LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        FixedExpense fixedExpense = new FixedExpense(
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()));

        Throwable exception = catchThrowable(() -> fixedExpense.setEndDate(endDate));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("End date must be after start date.");
    }

    @Test
    @DisplayName("Should return null if end date is valid")
    void shouldReturnNullIfEndDateIsValid() {
        Date endDate = Date.from(LocalDateTime.now().plusDays(20).atZone(ZoneId.systemDefault()).toInstant());

        FixedExpense fixedExpense = new FixedExpense(
                faker.lorem().word(),
                1,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(10).atZone(ZoneId.systemDefault()).toInstant()));

        fixedExpense.setEndDate(endDate);
        assertThat(fixedExpense.getEndDate()).isEqualTo(endDate);
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

        fixedExpense.setActive(false);
        assertThat(fixedExpense.getId()).isEqualTo(id);
        assertThat(fixedExpense.getUserId()).isEqualTo(userId);
        assertThat(fixedExpense.getDescription()).isEqualTo(description);
        assertThat(fixedExpense.getDueDay()).isEqualTo(dueDay);
        assertThat(fixedExpense.getCategory()).isEqualTo(category);
        assertThat(fixedExpense.getStartDate()).isEqualTo(startDate);
        assertThat(fixedExpense.getEndDate()).isEqualTo(endDate);
        assertThat(fixedExpense.isActive()).isFalse();
    }
}