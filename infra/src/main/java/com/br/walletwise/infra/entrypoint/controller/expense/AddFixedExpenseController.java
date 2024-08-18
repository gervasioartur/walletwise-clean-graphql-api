package com.br.walletwise.infra.entrypoint.controller.expense;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.infra.entrypoint.dto.AddFixedExpenseRequest;
import com.br.walletwise.infra.entrypoint.dto.Response;
import com.br.walletwise.infra.mappers.FixedExpenseMapper;
import com.br.walletwise.usecase.expense.AddFixedExpense;
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
public class AddFixedExpenseController {
    private final AddFixedExpense usecase;
    private final FixedExpenseMapper mapper;

    @PostMapping
    @Operation(summary = "Add fixed expense")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns successful message"),
            @ApiResponse(responseCode = "400", description = "Bad request happened"),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred."),
    })

    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Response> perform(@RequestBody AddFixedExpenseRequest request) {
        try {
            FixedExpense fixedExpense = this.mapper.map(request);
            this.usecase.add(fixedExpense);
            Response response = Response.builder().body("Expense Added.").build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DomainException ex) {
            Response response = Response.builder().body(ex.getMessage()).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            Response response = Response
                    .builder().body("An unexpected error occurred. Please try again later.").build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}