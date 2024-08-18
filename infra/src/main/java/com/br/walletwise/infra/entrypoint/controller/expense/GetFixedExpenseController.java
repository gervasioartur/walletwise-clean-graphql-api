package com.br.walletwise.infra.entrypoint.controller.expense;

import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.infra.entrypoint.dto.Response;
import com.br.walletwise.usecase.expense.GetFixedExpense;
import com.br.walletwise.usecase.expense.GetFixedExpenses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)

@RestController
@RequestMapping("/fixed-expenses")
@Tag(name = "Fixed Expenses")
@RequiredArgsConstructor
public class GetFixedExpenseController {
    private final GetFixedExpense usecase;

    @GetMapping("/{expenseCode}")
    @Operation(summary = "Get fixed expenses")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns successful message"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred."),
    })

    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Response> perform(@PathVariable("expenseCode") long expenseCode) {
        try {
            FixedExpenseModel list = this.usecase.get(expenseCode);
            Response response = Response.builder().body(list).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (NotFoundException ex) {
            Response response = Response.builder().body(ex.getMessage()).build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            Response response = Response
                    .builder().body("An unexpected error occurred. Please try again later.").build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}