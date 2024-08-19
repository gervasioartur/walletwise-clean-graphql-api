package com.br.walletwise.infra.config;

import com.br.walletwise.application.gateway.expense.*;
import com.br.walletwise.application.usecasesimpl.expense.*;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.usecase.cache.AddToCache;
import com.br.walletwise.usecase.cache.GetCache;
import com.br.walletwise.usecase.cache.InvalidateCache;
import com.br.walletwise.usecase.expense.*;
import com.br.walletwise.usecase.user.GetLoggedUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
                                             GetCache getCache,
                                             AddToCache<List<FixedExpenseModel>> addToCache) {

        return new GetFixedExpensesImpl(getFixedExpensesGateway, getLoggedUser, getCache, addToCache);
    }

    @Bean
    public GetFixedExpense getFixedExpense(GetFixedExpenseGateway getFixedExpenseGateway, GetLoggedUser getLoggedUser) {
        return new GetFixedExpenseImpl(getFixedExpenseGateway, getLoggedUser);
    }

    @Bean
    public UpdateFixedExpense updateFixedExpense(GetFixedExpense getFixedExpense,
                                                 UpdateFixedExpenseGateway updateFixedExpenseGateway,
                                                 InvalidateCache invalidateCache) {
        return new UpdateFixedExpenseImpl(getFixedExpense, updateFixedExpenseGateway, invalidateCache);
    }

    @Bean
    public DeleteFixedExpense deleteFixedExpense(GetFixedExpense getFixedExpense,
                                                 DeleteFixedExpenseGateway deleteFixedExpenseGateway,
                                                 InvalidateCache invalidateCache) {
        return new DeleteFixedExpenseImpl(getFixedExpense, deleteFixedExpenseGateway, invalidateCache);
    }
}