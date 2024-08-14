package com.br.walletwise.application.usecasesimpl.expense;

import com.br.walletwise.application.gateway.expense.AddFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.Expense;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.expense.AddExpense;
import com.br.walletwise.usecase.expense.AddFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;

public class AddFixedExpenseImpl implements AddFixedExpense {
    private final AddExpense addExpense;
    private final GetLoggedUser getLoggedUser;
    private final AddFixedExpenseGateway addFixedExpenseGateway;

    public AddFixedExpenseImpl(AddExpense addExpense,
                               GetLoggedUser getLoggedUser,
                               AddFixedExpenseGateway addFixedExpenseGateway) {
        this.addExpense = addExpense;
        this.getLoggedUser = getLoggedUser;
        this.addFixedExpenseGateway = addFixedExpenseGateway;
    }

    @Override
    public void add(FixedExpense fixedExpense) {
        User user =  this.getLoggedUser.get();

        Expense expense = new Expense(user.getId(),
                fixedExpense.getDescription(),
                fixedExpense.getCategory(),
                fixedExpense.getType(),
                fixedExpense.getAmount(),
                fixedExpense.isActive());

        expense = this.addExpense.add(expense);
        fixedExpense.setExpenseId(expense.getExpenseId());
        this.addFixedExpenseGateway.add(fixedExpense);
    }
}
