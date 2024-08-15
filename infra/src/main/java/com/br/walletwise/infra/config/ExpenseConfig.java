package com.br.walletwise.infra.config;

import com.br.walletwise.application.gateway.expense.AddFixedExpenseGateway;
import com.br.walletwise.application.usecasesimpl.expense.AddFixedExpenseImpl;
import com.br.walletwise.usecase.expense.AddFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpenseConfig {

    @Bean
    public AddFixedExpense addFixedExpense(GetLoggedUser getLoggedUser, AddFixedExpenseGateway addFixedExpenseGateway) {
        return new AddFixedExpenseImpl(getLoggedUser, addFixedExpenseGateway);
    }
}
