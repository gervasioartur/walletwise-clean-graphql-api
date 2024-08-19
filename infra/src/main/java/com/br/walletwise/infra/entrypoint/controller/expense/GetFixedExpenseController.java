package com.br.walletwise.infra.entrypoint.controller.expense;

import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.infra.entrypoint.dto.FixedExpenseOutput;
import com.br.walletwise.infra.mapper.FixedExpenseMapper;
import com.br.walletwise.usecase.expense.GetFixedExpense;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GetFixedExpenseController {
    private final GetFixedExpense usecase;
    private final FixedExpenseMapper mapper;

    @QueryMapping
    @PreAuthorize("isAuthenticated()")
    public FixedExpenseOutput getFixedExpense(@Argument("fixedExpenseCode") long fixedExpenseCode) {
        FixedExpenseModel fixedExpenseModel = this.usecase.getModel(fixedExpenseCode);
        return this.mapper.map(fixedExpenseModel);
    }
}