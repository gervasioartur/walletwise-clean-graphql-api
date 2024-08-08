package com.br.walletwise.application.usecasesimpl;

import com.br.walletwise.application.gateway.CreateUserGateway;
import com.br.walletwise.core.domain.entities.User;
import com.br.walletwise.core.exception.BusinessException;
import com.br.walletwise.usecase.CreateUser;
import com.br.walletwise.usecase.EncodePassword;
import com.br.walletwise.usecase.FindByEmail;
import com.br.walletwise.usecase.FindByUsername;


public class CreateUserImpl implements CreateUser {
    private final FindByUsername findByUsername;
    private final FindByEmail findByEmail;
    private final EncodePassword encodePassword;
    private final CreateUserGateway createUserGateway;

    public CreateUserImpl(FindByUsername findByUsername,
                          FindByEmail findByEmail,
                          EncodePassword encodePassword,
                          CreateUserGateway createUserGateway) {

        this.findByUsername = findByUsername;
        this.findByEmail = findByEmail;
        this.encodePassword = encodePassword;
        this.createUserGateway = createUserGateway;
    }

    @Override
    public void create(User user) {
        if (this.findByUsername.find(user.getUsername()).isPresent())
            throw new  BusinessException("Username already exists.");

        if (this.findByEmail.find(user.getEmail()).isPresent())
            throw new  BusinessException("Username already exists.");

        String encodedPassword = this.encodePassword.encode(user.getPassword());
        user.setPassword(encodedPassword);

        this.createUserGateway.create(user);
    }
}
