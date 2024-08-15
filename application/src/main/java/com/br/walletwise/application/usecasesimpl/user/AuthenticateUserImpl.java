package com.br.walletwise.application.usecasesimpl.user;

import com.br.walletwise.application.gateway.user.AuthenticateUserGateway;
import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.exception.UnauthorizedException;
import com.br.walletwise.usecase.user.*;

import java.util.Optional;

public class AuthenticateUserImpl implements AuthenticateUser {
    private final FindByUsername findByUsername;
    private final FindByEmail findByEmail;
    private final GenerateToken generateToken;
    private final AuthenticateUserGateway authenticateUserGateway;
    private final SaveSession saveSession;

    public AuthenticateUserImpl(FindByUsername findByUsername,
                                FindByEmail findByEmail,
                                GenerateToken generateToken,
                                AuthenticateUserGateway authenticateUserGateway,
                                SaveSession saveSession) {
        this.findByUsername = findByUsername;
        this.findByEmail = findByEmail;
        this.generateToken = generateToken;
        this.authenticateUserGateway = authenticateUserGateway;
        this.saveSession = saveSession;
    }

    @Override
    public String authenticate(String usernameOrEmail, String password) {
        Optional<User> userResult = usernameOrEmail.contains("@") ?
                findByEmail.find(usernameOrEmail)
                : findByUsername.find(usernameOrEmail);

        if (userResult.isEmpty()) throw new UnauthorizedException("Bad credentials.");
        String token = this.generateToken.generate(userResult.get().getUsername());
        if (token == null) throw new UnauthorizedException("Bad credentials.");
        this.authenticateUserGateway.authenticate(userResult.get().getUsername(), password);
        Session session = new Session(userResult.get().getId(), token);
        session = this.saveSession.save(session);
        return session.getToken();
    }
}