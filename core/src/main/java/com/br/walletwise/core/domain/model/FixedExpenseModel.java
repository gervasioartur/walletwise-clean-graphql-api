package com.br.walletwise.core.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class FixedExpenseModel {
    private String ownerFullName;
    private String description;
    private int dueDay;
    private String category;
    private BigDecimal amount;
    private Date startDate;
    private Date endDate;

    public FixedExpenseModel(String ownerFullName,
                             String description,
                             int dueDay,
                             String category,
                             BigDecimal amount,
                             Date startDate,
                             Date endDate) {

        this.ownerFullName = ownerFullName;
        this.description = description;
        this.dueDay = dueDay;
        this.category = category;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDueDay() {
        return dueDay;
    }

    public void setDueDay(int dueDay) {
        this.dueDay = dueDay;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
}