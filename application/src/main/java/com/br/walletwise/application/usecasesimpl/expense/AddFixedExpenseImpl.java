package com.br.walletwise.application.usecasesimpl.expense;

import com.br.walletwise.application.gateway.expense.AddFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.cache.InvalidateCache;
import com.br.walletwise.usecase.expense.AddFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;

public class AddFixedExpenseImpl implements AddFixedExpense {
    private final AddFixedExpenseGateway addFixedExpenseGateway;
    private final GetLoggedUser getLoggedUser;
    private final InvalidateCache invalidateCache;

    public AddFixedExpenseImpl(AddFixedExpenseGateway addFixedExpenseGateway,
                               GetLoggedUser getLoggedUser,
                               InvalidateCache invalidateCache) {

        this.addFixedExpenseGateway = addFixedExpenseGateway;
        this.getLoggedUser = getLoggedUser;
        this.invalidateCache = invalidateCache;
    }

    @Override
    public void add(FixedExpense fixedExpense) {
        User user = this.getLoggedUser.get();
        fixedExpense.setActive(true);
        fixedExpense.setUserId(user.getId());
        this.addFixedExpenseGateway.add(fixedExpense);
        this.invalidateCache.delete("fixedExpenses:" + user.getId());
    }
}