package com.br.walletwise.infra.entrypoint.controller.expense;

import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.infra.entrypoint.dto.Response;
import com.br.walletwise.infra.entrypoint.dto.UpdateFixedExpenseInput;
import com.br.walletwise.infra.mapper.FixedExpenseMapper;
import com.br.walletwise.usecase.expense.UpdateFixedExpense;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UpdateFixedExpenseController {
    private final UpdateFixedExpense usecase;
    private final FixedExpenseMapper mapper;

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Response updateFixedExpense(@Argument("input") UpdateFixedExpenseInput input) {
        FixedExpenseModel fixedExpense = this.mapper.map(input);
        fixedExpense.setExpenseCode(input.expenseCode());
        this.usecase.update(fixedExpense);
        return Response.builder().body("Expense successfully  updated.").build();
    }
}
