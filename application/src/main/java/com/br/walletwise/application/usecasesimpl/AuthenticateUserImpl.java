package com.br.walletwise.application.usecasesimpl;

import com.br.walletwise.application.gateway.AuthenticateUserGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.exception.UnauthorizedException;
import com.br.walletwise.usecase.AuthenticateUser;
import com.br.walletwise.usecase.FindByEmail;
import com.br.walletwise.usecase.FindByUsername;
import com.br.walletwise.usecase.GenerateToken;

import java.util.Optional;

public class AuthenticateUserImpl implements AuthenticateUser {
    private final FindByUsername findByUsername;
    private final FindByEmail findByEmail;
    private GenerateToken generateToken;
    private final AuthenticateUserGateway authenticateUserGateway;

    public AuthenticateUserImpl(FindByUsername findByUsername,
                                FindByEmail findByEmail,
                                GenerateToken generateToken,
                                AuthenticateUserGateway authenticateUserGateway) {
        this.findByUsername = findByUsername;
        this.findByEmail = findByEmail;
        this.generateToken = generateToken;
        this.authenticateUserGateway = authenticateUserGateway;
    }

    @Override
    public String authenticate(String usernameOrEmail, String password) {
        Optional<User> userResult =  usernameOrEmail.contains("@") ?
                findByEmail.find(usernameOrEmail)
                : findByUsername.find(usernameOrEmail) ;

        if(userResult.isEmpty()) throw  new UnauthorizedException("Bad credentials.");
        String token = this.generateToken.generate(userResult.get().getUsername());
        if(token == null) throw  new UnauthorizedException("Bad credentials.");
        this.authenticateUserGateway.authenticate(userResult.get().getUsername(), password);
        return token;
    }
}