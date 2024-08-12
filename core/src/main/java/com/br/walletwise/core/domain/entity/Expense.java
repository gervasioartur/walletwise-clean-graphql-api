package com.br.walletwise.core.domain.entity;

import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.core.validation.ValidationBuilder;
import com.br.walletwise.core.validation.validator.contracts.IValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Expense extends AbstractEntity {
    private Long id;
    private UUID userId;
    private String description;
    private String category;
    private String type;
    private BigDecimal amount;
    private boolean isActive;

    public Expense(Long id, UUID userId, String description, String category, String type, BigDecimal amount, boolean isActive) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.category = category;
        this.type = type;
        this.amount = amount;
        this.isActive = isActive;

        String error =  this.validate();
        if(error != null) throw new DomainException(error);
    }

    public Expense(UUID userId, String description, String category, String type, BigDecimal amount, boolean isActive) {
        this.userId = userId;
        this.description = description;
        this.category = category;
        this.type = type;
        this.amount = amount;
        this.isActive = isActive;

        String error =  this.validate();
        if(error != null) throw new DomainException(error);
    }

    public Long getId() {
        return id;
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
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    protected List<IValidator> buildValidators() {
        List<IValidator> validators = new ArrayList<IValidator>();
        validators.addAll(ValidationBuilder.of("User info",this.userId).required().build());
        validators.addAll(ValidationBuilder.of("Description",this.description).required().build());
        validators.addAll(ValidationBuilder.of("Category",this.category).required().build());

        return validators;
    }
}