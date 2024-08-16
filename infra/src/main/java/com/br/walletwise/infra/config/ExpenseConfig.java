package com.br.walletwise.infra.config;

import com.br.walletwise.application.gateway.expense.AddFixedExpenseGateway;
import com.br.walletwise.application.gateway.expense.GetFixedExpensesGateway;
import com.br.walletwise.application.usecasesimpl.expense.AddFixedExpenseImpl;
import com.br.walletwise.application.usecasesimpl.expense.GetFixedExpensesImpl;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.usecase.cache.AddToCache;
import com.br.walletwise.usecase.cache.DeleteCache;
import com.br.walletwise.usecase.cache.GetCache;
import com.br.walletwise.usecase.expense.AddFixedExpense;
import com.br.walletwise.usecase.expense.GetFixedExpenses;
import com.br.walletwise.usecase.user.GetLoggedUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpenseConfig {

    @Bean
    public AddFixedExpense addFixedExpense(AddFixedExpenseGateway addFixedExpenseGateway,
                                           GetLoggedUser getLoggedUser,
                                           DeleteCache deleteCache) {
        return new AddFixedExpenseImpl(addFixedExpenseGateway, getLoggedUser, deleteCache);
    }

    @Bean
    public GetFixedExpenses getFixedExpenses(GetFixedExpensesGateway getFixedExpensesGateway,
                                             GetLoggedUser getLoggedUser,
                                             GetCache<FixedExpenseModel> getCache,
                                             AddToCache addToCache) {
        return new GetFixedExpensesImpl(getFixedExpensesGateway, getLoggedUser, getCache, addToCache);
    }
}