package com.br.walletwise.application.usecasesimpl.expense;

import com.br.walletwise.application.gateway.expense.UpdateFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.usecase.cache.InvalidateCache;
import com.br.walletwise.usecase.expense.GetFixedExpense;
import com.br.walletwise.usecase.expense.UpdateFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;


public class UpdateFixedExpenseImpl implements UpdateFixedExpense {
    private final GetLoggedUser getLoggedUser;
    private final GetFixedExpense getFixedExpense;
    private final UpdateFixedExpenseGateway updateFixedExpenseGateway;
    private final InvalidateCache invalidateCache;

    public UpdateFixedExpenseImpl(GetLoggedUser getLoggedUser,
                                  GetFixedExpense getFixedExpense,
                                  UpdateFixedExpenseGateway updateFixedExpenseGateway,
                                  InvalidateCache invalidateCache) {

        this.getLoggedUser = getLoggedUser;
        this.getFixedExpense = getFixedExpense;
        this.updateFixedExpenseGateway = updateFixedExpenseGateway;
        this.invalidateCache = invalidateCache;
    }

    @Override
    public void update(FixedExpenseModel fixedExpense) {
        User user = this.getLoggedUser.get();
        FixedExpense savedFixedExpense = this.getFixedExpense.get(user.getId(), fixedExpense.getExpenseCode())
                .orElseThrow(() -> new NotFoundException
                        ("Fixed expense with code " + fixedExpense.getExpenseCode() + " not found"));

        savedFixedExpense.setDescription(fixedExpense.getDescription() == null ? savedFixedExpense.getDescription() : fixedExpense.getDescription());
        savedFixedExpense.setDueDay(fixedExpense.getDueDay() == 0 ? savedFixedExpense.getDueDay() : fixedExpense.getDueDay());
        savedFixedExpense.setCategory(fixedExpense.getCategory() == null ? savedFixedExpense.getCategory() : fixedExpense.getCategory());
        savedFixedExpense.setAmount(fixedExpense.getAmount() == null ? savedFixedExpense.getAmount() : fixedExpense.getAmount());
        savedFixedExpense.setStartDate(fixedExpense.getStartDate() == null ? savedFixedExpense.getStartDate() : fixedExpense.getStartDate());
        savedFixedExpense.setEndDate(fixedExpense.getEndDate() == null ? savedFixedExpense.getEndDate() : fixedExpense.getEndDate());

        this.updateFixedExpenseGateway.updated(savedFixedExpense);
        this.invalidateCache.delete("fixedExpenses:" + user.getId());
    }
}
