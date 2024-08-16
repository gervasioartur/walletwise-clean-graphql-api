package com.br.walletwise.application.usecasesimpl.expense;

import com.br.walletwise.application.gateway.expense.GetFixedExpensesGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.usecase.cache.AddToCache;
import com.br.walletwise.usecase.cache.GetCache;
import com.br.walletwise.usecase.expense.GetFixedExpenses;
import com.br.walletwise.usecase.user.GetLoggedUser;

import java.util.List;

public class GetFixedExpensesImpl implements GetFixedExpenses {
    private final GetFixedExpensesGateway getFixedExpensesGateway;
    private final GetLoggedUser getLoggedUser;
    private final GetCache<FixedExpenseModel> getCache;
    private final AddToCache<List<FixedExpenseModel>> addToCache;

    public GetFixedExpensesImpl(GetFixedExpensesGateway getFixedExpensesGateway,
                                GetLoggedUser getLoggedUser,
                                GetCache<FixedExpenseModel> getCache,
                                AddToCache addToCache) {

        this.getFixedExpensesGateway = getFixedExpensesGateway;
        this.getLoggedUser = getLoggedUser;
        this.getCache = getCache;
        this.addToCache = addToCache;
    }

    @Override
    public List<FixedExpenseModel> get() {
        User user = this.getLoggedUser.get();
        List<FixedExpenseModel> cachedFixedExpenses = this.getCache.get(user.getId().toString());
        if (cachedFixedExpenses.size() != 0) {
            return cachedFixedExpenses;
        } else {
            List<FixedExpenseModel> fixedExpenseModelList = this.getFixedExpensesGateway.get(user.getId());
            this.addToCache.add("fixedExpenses:" + user.getId(), fixedExpenseModelList);
            return fixedExpenseModelList;
        }
    }
}
