package com.br.walletwise.core.domain.entity;

import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.core.validation.ValidationBuilder;
import com.br.walletwise.core.validation.validator.contract.Validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FixedExpense extends Expense {
    private Long id;
    private int dueDay;
    private Date startDate;
    private Date endDate;

    public FixedExpense(Long id,Long expenseId, UUID userId, String description, String category, String type, BigDecimal amount, boolean isActive,Date startDate, Date endDate) {
        super(expenseId, userId, description, category, type, amount, isActive);
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;

        String error = this.validate();
        if(error !=  null) throw new DomainException(error);
    }

    public FixedExpense(UUID userId, String description, String category, String type, BigDecimal amount, boolean isActive, Date startDate, Date endDate) {
        super(userId, description, category, type, amount, isActive);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    public int getDueDay() {
        return dueDay;
    }

    public void setDueDay(int dueDay) {
        this.dueDay = dueDay;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    protected List<Validator> buildValidators() {
        List<Validator> validators = new ArrayList<Validator>();
        validators.addAll(ValidationBuilder.of("Start date", this.startDate).required().build());
        return validators;
    }
}