package com.br.walletwise.infra.entrypoint.controller.expense;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.infra.entrypoint.dto.AddFixedExpenseInput;
import com.br.walletwise.infra.entrypoint.dto.Response;
import com.br.walletwise.infra.mapper.FixedExpenseMapper;
import com.br.walletwise.usecase.expense.AddFixedExpense;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AddFixedExpenseController {
    private final AddFixedExpense usecase;
    private final FixedExpenseMapper mapper;

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Response addFixedExpense(@Argument(name = "input") AddFixedExpenseInput input) {
        FixedExpense fixedExpense = this.mapper.map(input);
        this.usecase.add(fixedExpense);
        return Response.builder().body("Expense Added.").build();
    }
}