package com.br.walletwise.application.usecasesimpl.expense;

import com.br.walletwise.application.gateway.expense.AddFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.expense.AddFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;

public class AddFixedExpenseImpl implements AddFixedExpense {
    private final GetLoggedUser getLoggedUser;
    private final AddFixedExpenseGateway addFixedExpenseGateway;

    public AddFixedExpenseImpl(GetLoggedUser getLoggedUser, AddFixedExpenseGateway addFixedExpenseGateway) {
        this.getLoggedUser = getLoggedUser;
        this.addFixedExpenseGateway = addFixedExpenseGateway;
    }

    @Override
    public void add(FixedExpense fixedExpense) {
        User user =  this.getLoggedUser.get();
        fixedExpense.setUserId(user.getId());
        this.addFixedExpenseGateway.add(fixedExpense);
    }
}