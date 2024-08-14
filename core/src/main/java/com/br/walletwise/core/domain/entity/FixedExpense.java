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
    private long id;
    private int dueDay;
    private Date startDate;
    private Date endDate;

    public FixedExpense(long id,
                        int dueDay,
                        Date startDate,
                        Date endDate,
                        long expenseId,
                        UUID userId,
                        String description,
                        String category,
                        String type,
                        BigDecimal amount,
                        boolean isActive) {
        super(expenseId, userId, description, category, type, amount, isActive);
        this.id =  id;
        this.dueDay = dueDay;
        this.startDate = startDate;
        this.endDate = endDate;

        String error =  this.validate();
        if(error != null) throw new DomainException(error);
    }

    public FixedExpense(int dueDay,
                        Date startDate,
                        Date endDate,
                        long expenseId,
                        UUID userId,
                        String description,
                        String category,
                        String type,
                        BigDecimal amount,
                        boolean isActive) {

        super(expenseId, userId, description, category, type, amount, isActive);
        this.dueDay = dueDay;
        this.startDate = startDate;
        this.endDate = endDate;

        String error =  this.validate();
        if(error != null) throw new DomainException(error);
    }

    @Override
    public long getId() {
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
        String error = this.validate();
        if(error != null) throw new DomainException(error);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
        String error = this.validate();
        if(error != null) throw new DomainException(error);
    }

    @Override
    protected List<Validator> buildValidators() {
        List<Validator> validators = new ArrayList<Validator>();
        validators.addAll(ValidationBuilder.of("Due day", this.dueDay).required().build());
        validators.addAll(ValidationBuilder.of("Start date", this.startDate).required().build());
        validators.addAll(ValidationBuilder.of("End date", this.endDate).required().build());
        validators.addAll(ValidationBuilder.of("End date", this.endDate).required().endDate(this.startDate).build());
        return validators;
    }
}