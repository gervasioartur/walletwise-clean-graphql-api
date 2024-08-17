package com.br.walletwise.core.domain.entity;

import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.core.validation.ValidationBuilder;
import com.br.walletwise.core.validation.validator.contract.Validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FixedExpense extends AbstractEntity {
    private long id;
    private UUID userId;
    private String description;
    private int dueDay;
    private String category;
    private BigDecimal amount;
    private Date startDate;
    private Date endDate;
    private boolean isActive;

    public FixedExpense(long id,
                        UUID userId,
                        String description,
                        int dueDay,
                        String category,
                        BigDecimal amount,
                        Date startDate,
                        Date endDate,
                        boolean isActive) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.dueDay = dueDay;
        this.category = category;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;

        String error = this.validate();
        if (error != null) throw new DomainException(error);
    }



    public FixedExpense(
            String description,
            int dueDay,
            String category,
            BigDecimal amount,
            Date startDate,
            Date endDate) {

        this.description = description;
        this.dueDay = dueDay;
        this.category = category;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;

        String error = this.validate();
        if (error != null) throw new DomainException(error);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        String error = this.validate();
        if (error != null) throw new DomainException(error);
    }

    public int getDueDay() {
        return dueDay;
    }

    public void setDueDay(int dueDay) {
        this.dueDay = dueDay;
        String error = this.validate();
        if (error != null) throw new DomainException(error);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        String error = this.validate();
        if (error != null) throw new DomainException(error);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
        String error = this.validate();
        if (error != null) throw new DomainException(error);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        String error = this.validate();
        if (error != null) throw new DomainException(error);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        String error = this.validate();
        if (error != null) throw new DomainException(error);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    protected List<Validator> buildValidators() {
        List<Validator> validators = new ArrayList<>();
        validators.addAll(ValidationBuilder.of("Description", this.description).required().build());
        validators.addAll(ValidationBuilder.of("Category", this.category).required().category().build());
        validators.addAll(ValidationBuilder.of("Amount", this.amount).required().amount().build());
        validators.addAll(ValidationBuilder.of("Due day", this.dueDay).required().dueDay().build());
        validators.addAll(ValidationBuilder.of("Start date", this.startDate).required().build());
        validators.addAll(ValidationBuilder.of("End date", this.endDate).required().endDate(this.startDate).build());
        return validators;
    }
}