package com.br.walletwise.application.usecasesimpl.expense;

import com.br.walletwise.application.gateway.expense.UpdateFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.usecase.expense.GetFixedExpense;
import com.br.walletwise.usecase.expense.UpdateFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;


public class UpdateFixedExpenseImpl implements UpdateFixedExpense {
    private final GetLoggedUser getLoggedUser;
    private final GetFixedExpense getFixedExpense;
    private final UpdateFixedExpenseGateway updateFixedExpenseGateway;

    public UpdateFixedExpenseImpl(GetLoggedUser getLoggedUser,
                                  GetFixedExpense getFixedExpense,
                                  UpdateFixedExpenseGateway updateFixedExpenseGateway) {

        this.getLoggedUser = getLoggedUser;
        this.getFixedExpense = getFixedExpense;
        this.updateFixedExpenseGateway = updateFixedExpenseGateway;
    }

    @Override
    public void update(FixedExpense fixedExpense) {
        User user = this.getLoggedUser.get();
        FixedExpense savedFixedExpense =  this.getFixedExpense.get(user.getId(), fixedExpense.getId())
                .orElseThrow(() -> new NotFoundException
                        ("Fixed expense with code " + fixedExpense.getId() + " not found"));

        savedFixedExpense.setDescription(fixedExpense.getDescription());
        savedFixedExpense.setDueDay(fixedExpense.getDueDay());
        savedFixedExpense.setCategory(fixedExpense.getCategory());
        savedFixedExpense.setAmount(fixedExpense.getAmount());
        savedFixedExpense.setStartDate(fixedExpense.getStartDate());
        savedFixedExpense.setEndDate(fixedExpense.getEndDate());

        this.updateFixedExpenseGateway.updated(savedFixedExpense);
    }
}
