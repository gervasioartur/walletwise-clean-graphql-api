package com.br.walletwise.infra.entrypoint.controller.user;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.exception.ConflictException;
import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.infra.entrypoint.dto.CreateUserRequest;
import com.br.walletwise.infra.entrypoint.dto.Response;
import com.br.walletwise.infra.mappers.UserMapper;
import com.br.walletwise.usecase.CreateUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "User")
@RequiredArgsConstructor
public class CreateUserController {
    private final CreateUser usecase;
    private final UserMapper mapper;

    @PostMapping
    @Operation(summary = "Create User")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns successful message"),
            @ApiResponse(responseCode = "400", description = "Bad request happened"),
            @ApiResponse(responseCode = "409", description = "Conflict with business rules"),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred."),
    })
    public ResponseEntity<Response> creteUser(@RequestBody CreateUserRequest request) {
        try {
            User user = mapper.map(request);
            this.usecase.create(user);
            Response response = Response.builder().body("User Created.").build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DomainException ex) {
            Response response = Response.builder().body(ex.getMessage()).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (ConflictException ex) {
            Response response = Response.builder().body(ex.getMessage()).build();
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (Exception ex) {
            Response response = Response
                    .builder().body("An unexpected error occurred. Please try again later.").build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}