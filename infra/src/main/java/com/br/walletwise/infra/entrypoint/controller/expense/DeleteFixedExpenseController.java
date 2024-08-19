package com.br.walletwise.infra.entrypoint.controller.expense;

import com.br.walletwise.infra.entrypoint.dto.Response;
import com.br.walletwise.usecase.expense.DeleteFixedExpense;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class DeleteFixedExpenseController {
    private final DeleteFixedExpense usecase;

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Response deleteFixedExpense(@Argument("fixedExpenseCode") long fixedExpenseCode) {
        this.usecase.delete(fixedExpenseCode);
        return Response.builder().body("OK").build();
    }
}
