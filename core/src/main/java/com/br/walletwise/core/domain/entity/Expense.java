package com.br.walletwise.core.domain.entity;

import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.core.validation.ValidationBuilder;
import com.br.walletwise.core.validation.validator.contract.Validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Expense extends AbstractEntity {
    private long expenseId;
    private final UUID userId;
    private String description;
    private String category;
    private final String type;
    private BigDecimal amount;
    private boolean isActive;

    public Expense(long expenseId, UUID userId, String description, String category, String type, BigDecimal amount, boolean isActive) {
        this.expenseId = expenseId;
        this.userId = userId;
        this.description = description;
        this.category = category;
        this.type = type;
        this.amount = amount;
        this.isActive = isActive;
    }

    public Expense(UUID userId, String description, String category, String type, BigDecimal amount, boolean isActive) {
        this.userId = userId;
        this.description = description;
        this.category = category;
        this.type = type;
        this.amount = amount;
        this.isActive = isActive;
    }

    public long getExpenseId() {
        return expenseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        String error =  this.validate();
        if(error != null) throw new DomainException(error);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        String error =  this.validate();
        if(error != null) throw new DomainException(error);
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
        String error =  this.validate();
        if(error != null) throw new DomainException(error);
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
        validators.addAll(ValidationBuilder.of("User info",this.userId).required().build());
        validators.addAll(ValidationBuilder.of("Description",this.description).required().build());
        validators.addAll(ValidationBuilder.of("Category",this.category).required().category().build());
        validators.addAll(ValidationBuilder.of("Amount",this.amount).required().build());
        return validators;
    }
}