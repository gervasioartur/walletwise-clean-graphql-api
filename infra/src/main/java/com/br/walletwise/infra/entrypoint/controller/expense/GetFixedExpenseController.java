package com.br.walletwise.infra.entrypoint.controller.expense;

import com.br.walletwise.core.domain.model.FixedExpenseModel;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    @Operation(summary = "List fixed expenses")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns successful message"),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred."),
    })

    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Response> perform() {
        try {
            List<FixedExpenseModel> list = this.usecase.getModel();
            Response response = Response.builder().body(list).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            Response response = Response
                    .builder().body("An unexpected error occurred. Please try again later.").build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}