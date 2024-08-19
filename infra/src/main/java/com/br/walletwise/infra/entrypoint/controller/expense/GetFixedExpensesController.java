package com.br.walletwise.infra.entrypoint.controller.expense;

import com.br.walletwise.infra.entrypoint.dto.FixedExpenseOutput;
import com.br.walletwise.infra.mapper.FixedExpenseMapper;
import com.br.walletwise.usecase.expense.GetFixedExpenses;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GetFixedExpensesController {
    private final GetFixedExpenses usecase;
    private final FixedExpenseMapper mapper;

    @QueryMapping
    @PreAuthorize("isAuthenticated()")
    public List<FixedExpenseOutput> getFixedExpenses() {
        return this.usecase.get()
                .stream()
                .map(this.mapper::map)
                .toList();
    }
}