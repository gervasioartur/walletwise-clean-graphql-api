package com.br.walletwise.infra.config;

import com.br.walletwise.application.gateway.expense.AddFixedExpenseGateway;
import com.br.walletwise.application.gateway.expense.GetFixedExpenseGateway;
import com.br.walletwise.application.gateway.expense.GetFixedExpensesGateway;
import com.br.walletwise.application.gateway.expense.UpdateFixedExpenseGateway;
import com.br.walletwise.application.usecasesimpl.expense.AddFixedExpenseImpl;
import com.br.walletwise.application.usecasesimpl.expense.GetFixedExpenseImpl;
import com.br.walletwise.application.usecasesimpl.expense.GetFixedExpensesImpl;
import com.br.walletwise.application.usecasesimpl.expense.UpdateFixedExpenseImpl;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.usecase.cache.AddToCache;
import com.br.walletwise.usecase.cache.GetCache;
import com.br.walletwise.usecase.cache.InvalidateCache;
import com.br.walletwise.usecase.expense.AddFixedExpense;
import com.br.walletwise.usecase.expense.GetFixedExpense;
import com.br.walletwise.usecase.expense.GetFixedExpenses;
import com.br.walletwise.usecase.expense.UpdateFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpenseConfig {

    @Bean
    public AddFixedExpense addFixedExpense(AddFixedExpenseGateway addFixedExpenseGateway,
                                           GetLoggedUser getLoggedUser,
                                           InvalidateCache invalidateCache) {
        return new AddFixedExpenseImpl(addFixedExpenseGateway, getLoggedUser, invalidateCache);
    }

    @Bean
    public GetFixedExpenses getFixedExpenses(GetFixedExpensesGateway getFixedExpensesGateway,
                                             GetLoggedUser getLoggedUser,
                                             GetCache<FixedExpenseModel> getCache,
                                             AddToCache addToCache) {
        return new GetFixedExpensesImpl(getFixedExpensesGateway, getLoggedUser, getCache, addToCache);
    }

    @Bean
    public GetFixedExpense getFixedExpense(GetFixedExpenseGateway getFixedExpenseGateway, GetLoggedUser getLoggedUser) {
        return new GetFixedExpenseImpl(getFixedExpenseGateway, getLoggedUser);
    }

    @Bean
    public UpdateFixedExpense updateFixedExpense(GetLoggedUser getLoggedUser,
                                                 GetFixedExpense getFixedExpense,
                                                 UpdateFixedExpenseGateway updateFixedExpenseGateway,
                                                 InvalidateCache invalidateCache) {
        return new UpdateFixedExpenseImpl(getLoggedUser, getFixedExpense, updateFixedExpenseGateway, invalidateCache);
    }
}