package com.br.walletwise.infra.entrypoint.dto;

import java.math.BigDecimal;
import java.util.Date;

public record UpdateFixedExpenseInput(long expenseCode,
                                      String description,
                                      String category,
                                      BigDecimal amount,
                                      int dueDay,
                                      Date starDate,
                                      Date endDate) {
}
