package com.br.walletwise.infra.entrypoint.controller.user;

import com.br.walletwise.core.exception.ConflictException;
import com.br.walletwise.core.exception.DomainException;
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

@RestController
@RequestMapping("/users/authenticate")
@Tag(name = "User")
@RequiredArgsConstructor
public class AuthenticateUserController {
    private final AuthenticateUser usecase;

    @PostMapping
    @Operation(summary = "Authenticate User")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns successful message"),
            @ApiResponse(responseCode = "400", description = "Bad request happened"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred."),
    })

    public ResponseEntity<Response> authenticateUser(@RequestBody AuthenticateUserRequest request) {
        try {
            String token = this.usecase.authenticate(request.usernameOrEmail(), request.password());
            Response response = Response.builder().body(token).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DomainException ex) {
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
}