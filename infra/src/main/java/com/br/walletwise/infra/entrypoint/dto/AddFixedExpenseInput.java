package com.br.walletwise.infra.entrypoint.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AddFixedExpenseInput(String description,
                                   String category,
                                   BigDecimal amount,
                                   int dueDay,
                                   LocalDate starDate,
                                   LocalDate endDate) {
}

