package com.br.walletwise.infra.entrypoint.controller.expense;

import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.infra.entrypoint.dto.Response;
import com.br.walletwise.infra.entrypoint.dto.UpdateFixedExpenseRequest;
import com.br.walletwise.infra.mappers.FixedExpenseMapper;
import com.br.walletwise.usecase.expense.UpdateFixedExpense;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
public class UpdateFixedExpenseController {
    private final UpdateFixedExpense usecase;
    private final FixedExpenseMapper mapper;

    public ResponseEntity<Response> perform(@PathVariable("expenseCode") long expenseCode, @RequestBody UpdateFixedExpenseRequest request) {
        try {
            FixedExpenseModel fixedExpense = this.mapper.map(request);
            fixedExpense.setExpenseCode(expenseCode);
            this.usecase.update(fixedExpense);
            Response response = Response.builder().body("Expense successfully  updated.").build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DomainException ex) {
            Response response = Response.builder().body(ex.getMessage()).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException ex) {
            Response response = Response.builder().body(ex.getMessage()).build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            Response response = Response
                    .builder().body("An unexpected error occurred. Please try again later.").build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
