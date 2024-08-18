package com.br.walletwise.application.usecasesimpl.expense;

import com.br.walletwise.application.gateway.expense.DeleteFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.usecase.expense.DeleteFixedExpense;
import com.br.walletwise.usecase.expense.GetFixedExpense;


public class DeleteFixedExpenseImpl implements DeleteFixedExpense {
    private final GetFixedExpense getFixedExpense;
    private final DeleteFixedExpenseGateway deleteFixedExpenseGateway;

    public DeleteFixedExpenseImpl(GetFixedExpense getFixedExpense,
                                  DeleteFixedExpenseGateway deleteFixedExpenseGateway) {

        this.getFixedExpense = getFixedExpense;
        this.deleteFixedExpenseGateway = deleteFixedExpenseGateway;
    }

    @Override
    public void delete(long expenseCode) {
        FixedExpense result = this.getFixedExpense.get(expenseCode)
                .orElseThrow(() -> new NotFoundException("Resource not found."));
        result.setActive(false);
        this.deleteFixedExpenseGateway.delete(result);
    }
}
