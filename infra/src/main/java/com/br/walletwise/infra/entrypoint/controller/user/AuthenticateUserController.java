package com.br.walletwise.infra.entrypoint.controller.user;

import com.br.walletwise.core.exception.BusinessException;
import com.br.walletwise.core.exception.ConflictException;
import com.br.walletwise.core.validation.ValidationBuilder;
import com.br.walletwise.core.validation.validator.contracts.IValidator;
import com.br.walletwise.infra.entrypoint.controller.AbstractController;
import com.br.walletwise.infra.entrypoint.dto.AuthenticateUserRequest;
import com.br.walletwise.infra.entrypoint.dto.Response;
import com.br.walletwise.usecase.AuthenticateUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users/authenticate")
@Tag(name = "User")
@RequiredArgsConstructor
public class AuthenticateUserController extends AbstractController<Response, AuthenticateUserRequest> {
    private final AuthenticateUser usecase;

    @Override
    @PostMapping
    @Operation(summary = "Authenticate User")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns successful message"),
            @ApiResponse(responseCode = "400", description = "Bad request happened"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred."),
    })

    public ResponseEntity<Response> perform(@RequestBody AuthenticateUserRequest request) {
        String error = this.validate(request);
        if(error != null) {
            Response response = Response.builder().body(error).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            String token = this.usecase.authenticate(request.usernameOrEmail(), request.password());
            Response response = Response.builder().body(token).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BusinessException ex) {
            Response response = Response.builder().body(ex.getMessage()).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (ConflictException ex) {
            Response response = Response.builder().body(ex.getMessage()).build();
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            Response response = Response
                    .builder().body("An unexpected error occurred. Please try again later.").build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected List<IValidator> buildValidators(AuthenticateUserRequest request) {
        List<IValidator> validators = new ArrayList<>();
        validators.addAll(ValidationBuilder.of("Username or E-mail",request.usernameOrEmail()).required().build());
        validators.addAll(ValidationBuilder.of("Password",request.password()).required().build());
        return validators;
    }
}