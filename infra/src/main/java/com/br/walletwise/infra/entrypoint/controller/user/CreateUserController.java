package com.br.walletwise.infra.entrypoint.controller.user;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.entrypoint.dto.CreateUserInput;
import com.br.walletwise.infra.entrypoint.dto.Response;
import com.br.walletwise.infra.mapper.UserMapper;
import com.br.walletwise.usecase.user.CreateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CreateUserController {
    private final CreateUser usecase;
    private final UserMapper mapper;

    @MutationMapping
    public Response createUser(@Argument(name = "input") CreateUserInput input) {
        User user = mapper.map(input);
        this.usecase.create(user);
        return Response.builder().body("User Created.").build();
    }
}