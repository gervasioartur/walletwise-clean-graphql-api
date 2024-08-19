package com.br.walletwise.infra.entrypoint.controller.user;

import com.br.walletwise.core.exception.BusinessException;
import com.br.walletwise.core.validation.ValidationBuilder;
import com.br.walletwise.core.validation.validator.contract.Validator;
import com.br.walletwise.infra.entrypoint.controller.AbstractController;
import com.br.walletwise.infra.entrypoint.dto.AuthenticateUserInput;
import com.br.walletwise.infra.entrypoint.dto.Response;
import com.br.walletwise.usecase.user.AuthenticateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthenticateUserController extends AbstractController<Response, AuthenticateUserInput> {
    private final AuthenticateUser usecase;

    @MutationMapping
    public Response authenticateUser(@Argument(name = "input") AuthenticateUserInput input) {
        String error = this.validate(input);
        if (error != null) throw new BusinessException(error);
        String token = this.usecase.authenticate(input.usernameOrEmail(), input.password());
        return Response.builder().body(token).build();
    }

    @Override
    protected List<Validator> buildValidators(AuthenticateUserInput request) {
        List<Validator> validators = new ArrayList<>();
        validators.addAll(ValidationBuilder.of("Username or E-mail", request.usernameOrEmail()).required().build());
        validators.addAll(ValidationBuilder.of("Password", request.password()).required().build());
        return validators;
    }
}