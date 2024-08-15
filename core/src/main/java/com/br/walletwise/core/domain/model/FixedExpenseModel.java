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
}