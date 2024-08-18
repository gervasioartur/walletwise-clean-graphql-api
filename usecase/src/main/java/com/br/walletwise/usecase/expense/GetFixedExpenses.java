package com.br.walletwise.usecase.expense;

import com.br.walletwise.core.domain.model.FixedExpenseModel;

import java.util.List;

public interface GetFixedExpenses {
    List<FixedExpenseModel> get();
}