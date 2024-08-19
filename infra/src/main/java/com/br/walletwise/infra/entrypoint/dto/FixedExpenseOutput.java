package com.br.walletwise.infra.entrypoint.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FixedExpenseOutput(int expenseCode,
                                 String ownerFullName,
                                 String description,
                                 int dueDay,
                                 String category,
                                 BigDecimal amount,
                                 LocalDate starDate,
                                 LocalDate endDate) {
}

