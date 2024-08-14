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
    @DisplayName("Should throw Domain exception if start date is null on build with all arguments")
    void shouldThrowDomainExceptionIfStartDateIsNullOnBuildWithAllArguments() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
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
        Throwable exception = catchThrowable(() -> new FixedExpense(
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

        Throwable exception = catchThrowable(() -> fixedExpense.setStartDate(null));

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
        Throwable exception = catchThrowable(() -> new FixedExpense(
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
        Throwable exception = catchThrowable(() -> new FixedExpense(
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

        Throwable exception = catchThrowable(() -> fixedExpense.setEndDate(null));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("End date is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if end date is before start date")
    void shouldThrowDomainExceptionIfEndDateIsBeforeStartDate() {
        Date startDate = new Date();
        Date endDate = Date.from(LocalDateTime.now().minusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        Throwable exception = catchThrowable(() ->  new FixedExpense(
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

        Throwable exception = catchThrowable(() -> new FixedExpense(
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


        Throwable exception = catchThrowable(() -> new FixedExpense(
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

        Throwable exception = catchThrowable(() ->  new FixedExpense(
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

    @Test
    @DisplayName("Should throw DomainException if due day is invalid")
    void shouldReturnNullOnBuildWithInvalidDueDay() {
        Date startDate = new Date();
        Date endDate = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        int dueDay = 30;
        FixedExpense fixedExpense = new FixedExpense(
                dueDay,
                startDate,
                endDate,
                32,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        assertThat(fixedExpense.getDueDay()).isEqualTo(dueDay);
    }

    @Test
    @DisplayName("Should throw DomainException if due day is zero(0) on update due day")
    void shouldThrowDomainExceptionIfDueDayIsZeroOnUpdateDueDay() {
        Date startDate = new Date();
        Date endDate = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        FixedExpense fixedExpense = new FixedExpense(
                20,
                startDate,
                endDate,
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        int updatedDueDay =  0;
        Throwable exception = catchThrowable(() -> fixedExpense.setDueDay(updatedDueDay));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Due day is required.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1,32})
    @DisplayName("Should throw DomainException if due day is invalid on update due day")
    void shouldThrowDomainExceptionIfDueDayIsInvalidOnUpdateDueDay(int dueDay) {
        Date startDate = new Date();
        Date endDate = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        FixedExpense fixedExpense = new FixedExpense(
                20,
                startDate,
                endDate,
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        Throwable exception = catchThrowable(() -> fixedExpense.setDueDay(dueDay));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Due day must be between 1 and 31.");
    }

    @Test
    @DisplayName("Should return null if due day is valid on update due day")
    void shouldReturnNullIfDueDayIsValidOnUpdateDueDay() {
        Date startDate = new Date();
        Date endDate = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        FixedExpense fixedExpense = new FixedExpense(
                20,
                startDate,
                endDate,
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true);

        int updatedDueDay =  20;
        fixedExpense.setDueDay(updatedDueDay);

        assertThat(fixedExpense.getDueDay()).isEqualTo(updatedDueDay);
    }

    @Test
    @DisplayName("Should throw DomainException if user id is null on build with all arguments")
    void shouldThrowDomainExceptionIfUserIdIsNullOnBuildWithAllArguments() {
       Throwable exception = catchThrowable(() -> new FixedExpense(
               10,
               20,
               new Date(),
               Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()),
               01,
               null,
               faker.lorem().paragraph(),
               CategoryEnum.SCHOOL.getValue(),
               ExpenseTypeEnum.FIXED.getValue(),
               new BigDecimal(200),
               true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User info is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if user id is null on build with no id")
    void shouldThrowDomainExceptionIfUserIdIsNullOnBuildWithNoId() {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                20,
                new Date(),
                Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()),
                01,
                null,
                faker.lorem().paragraph(),
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User info is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if description is null or empty on build with all arguments")
    void shouldThrowDomainExceptionIfDescriptionIsNullOnBuildWithAllArguments(String description) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                10,
                20,
                new Date(),
                Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()),
                01,
                UUID.randomUUID(),
                description,
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Description is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if description is null or empty on build with no id")
    void shouldThrowDomainExceptionIfDescriptionIsNullOnBuildWithNoId(String description) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                20,
                new Date(),
                Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()),
                01,
                UUID.randomUUID(),
                description,
                CategoryEnum.SCHOOL.getValue(),
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Description is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if category is null or empty on build with all arguments")
    void shouldThrowDomainExceptionIfCategoryIsNullOnBuildWithAllArguments(String category) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                10,
                20,
                new Date(),
                Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()),
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                category,
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if category is null or empty on build with no id")
    void shouldThrowDomainExceptionIfCategoryIsNullOnBuildWithNoId(String category) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                20,
                new Date(),
                Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()),
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                category,
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is required.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_category","any_invalid_category"})
    @DisplayName("Should throw DomainException if category is invalid on build with all arguments")
    void shouldThrowDomainExceptionIfCategoryIsInvalidOnBuildWithAllArguments(String category) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                1,
                20,
                new Date(),
                Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()),
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                category,
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is invalid. These are available categories : "
                + CategoryEnum.RENT.getValue() + "," + CategoryEnum.SCHOOL.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_category","any_invalid_category"})
    @DisplayName("Should throw DomainException if category is invalid on build with all arguments")
    void shouldThrowDomainExceptionIfCategoryIsInvalidOnBuildWithNoId(String category) {
        Throwable exception = catchThrowable(() -> new FixedExpense(
                20,
                new Date(),
                Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()),
                01,
                UUID.randomUUID(),
                faker.lorem().paragraph(),
                category,
                ExpenseTypeEnum.FIXED.getValue(),
                new BigDecimal(200),
                true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Category is invalid. These are available categories : "
                + CategoryEnum.RENT.getValue() + "," + CategoryEnum.SCHOOL.getValue());
    }

    @Test
    @DisplayName("Should build FixedExpense with correct values")
    void shouldBuildFixedExpenseWithCorrectValues() {
        long fixedExpenseId = 1;
        int dueDay = 1;
        Date startDate = new Date();
        Date endDate = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        long expenseId = 2;
        UUID userId = UUID.randomUUID();
        String description = faker.lorem().paragraph();
        String category = CategoryEnum.SCHOOL.getValue();
        String type = ExpenseTypeEnum.FIXED.getValue();
        BigDecimal amount = new BigDecimal(200.0);
        boolean active = true;


        FixedExpense fixedExpense = new FixedExpense
                (fixedExpenseId,dueDay,startDate,endDate,expenseId,userId,description,category,type,amount,active);

        assertThat(fixedExpense.getFixedExpenseId()).isEqualTo(fixedExpenseId);
        assertThat(fixedExpense.getDueDay()).isEqualTo(dueDay);
        assertThat(fixedExpense.getStartDate()).isEqualTo(startDate);
        assertThat(fixedExpense.getEndDate()).isEqualTo(endDate);
        assertThat(fixedExpense.getExpenseId()).isEqualTo(expenseId);
        assertThat(fixedExpense.getUserId()).isEqualTo(userId);
        assertThat(fixedExpense.getDescription()).isEqualTo(description);
        assertThat(fixedExpense.getCategory()).isEqualTo(category);
        assertThat(fixedExpense.getType()).isEqualTo(type);
        assertThat(fixedExpense.getType()).isEqualTo(type);
        assertThat(fixedExpense.isActive()).isEqualTo(active);
    }
}