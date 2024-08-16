package com.br.walletwise.application.usecasesimpl.expense;

import com.br.walletwise.application.gateway.expense.AddFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.cache.DeleteCache;
import com.br.walletwise.usecase.expense.AddFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;

public class AddFixedExpenseImpl implements AddFixedExpense {
    private final AddFixedExpenseGateway addFixedExpenseGateway;
    private final GetLoggedUser getLoggedUser;
    private final DeleteCache deleteCache;

    public AddFixedExpenseImpl(AddFixedExpenseGateway addFixedExpenseGateway,
                               GetLoggedUser getLoggedUser,
                               DeleteCache deleteCache) {

        this.addFixedExpenseGateway = addFixedExpenseGateway;
        this.getLoggedUser = getLoggedUser;
        this.deleteCache = deleteCache;
    }

    @Override
    public void add(FixedExpense fixedExpense) {
        User user = this.getLoggedUser.get();
        fixedExpense.setUserId(user.getId());
        fixedExpense.setActive(true);
        this.addFixedExpenseGateway.add(fixedExpense);
        this.deleteCache.delete("fixedExpenses:" + user.getId());
    }
}