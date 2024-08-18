package com.br.walletwise.application.usecasesimpl.expense;

import com.br.walletwise.application.gateway.expense.GetFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.usecase.expense.GetFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;

import java.util.Optional;
import java.util.UUID;

public class GetFixedExpenseImpl implements GetFixedExpense {
    private final GetFixedExpenseGateway getFixedExpenseGateway;
    private final GetLoggedUser getLoggedUser;

    public GetFixedExpenseImpl(GetFixedExpenseGateway getFixedExpenseGateway, GetLoggedUser getLoggedUser) {
        this.getFixedExpenseGateway = getFixedExpenseGateway;
        this.getLoggedUser = getLoggedUser;
    }

    @Override
    public Optional<FixedExpense> get(UUID userId, long expenseCode) {
        return this.getFixedExpenseGateway.get(expenseCode, userId);
    }

    @Override
    public FixedExpenseModel get(long expenseCode) {
        User user = this.getLoggedUser.get();
        return this.getFixedExpenseGateway.getModel(expenseCode, user.getId())
                .orElseThrow(() -> new NotFoundException("Resource not found."));
    }
}