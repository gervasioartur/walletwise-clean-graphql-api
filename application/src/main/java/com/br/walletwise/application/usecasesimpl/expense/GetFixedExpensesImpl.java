package com.br.walletwise.application.usecasesimpl.expense;

import com.br.walletwise.application.gateway.expense.GetFixedExpensesGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.usecase.expense.GetFixedExpenses;
import com.br.walletwise.usecase.user.GetLoggedUser;

import java.util.List;

public class GetFixedExpensesImpl implements GetFixedExpenses {
    private final GetLoggedUser getLoggedUser;
    private final GetFixedExpensesGateway getFixedExpensesGateway;

    public GetFixedExpensesImpl(GetLoggedUser getLoggedUser, GetFixedExpensesGateway getFixedExpensesGateway) {
        this.getLoggedUser = getLoggedUser;
        this.getFixedExpensesGateway = getFixedExpensesGateway;
    }

    @Override
    public List<FixedExpenseModel> get() {
        User user = this.getLoggedUser.get();
        return this.getFixedExpensesGateway.get(user.getId());
    }
}
