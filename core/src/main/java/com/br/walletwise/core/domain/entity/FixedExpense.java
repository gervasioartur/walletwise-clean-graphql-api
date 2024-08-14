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
    private long fixedExpenseId;
    private int dueDay;
    private Date startDate;
    private Date endDate;

    public FixedExpense(long fixedExpenseId,
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
        this.fixedExpenseId = fixedExpenseId;
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

    public long getFixedExpenseId() {
        return fixedExpenseId;
    }

    public int getDueDay() {
        return dueDay;
    }

    public void setDueDay(int dueDay) {
        this.dueDay = dueDay;
        String error = this.validate();
        if(error != null) throw new DomainException(error);
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
        List<Validator> validators = new ArrayList();
        validators.addAll(ValidationBuilder.of("Due day", this.dueDay).required().dueDay().build());
        validators.addAll(ValidationBuilder.of("Start date", this.startDate).required().build());
        validators.addAll(ValidationBuilder.of("End date", this.endDate).required().endDate(this.startDate).build());
        validators.addAll(ValidationBuilder.of("User info",super.getUserId()).required().build());
        validators.addAll(ValidationBuilder.of("Description",super.getDescription()).required().build());
        validators.addAll(ValidationBuilder.of("Category",super.getCategory()).required().category().build());
        validators.addAll(ValidationBuilder.of("Amount",super.getAmount()).required().build());
        return validators;
    }
}