package com.br.walletwise.application.usecasesimpl.user;

import com.br.walletwise.application.gateway.EncodePasswordGateway;
import com.br.walletwise.usecase.user.EncodePassword;

public class EncodePasswordImpl implements EncodePassword {
    private final EncodePasswordGateway encodePasswordGateway;

    public EncodePasswordImpl(EncodePasswordGateway encodePasswordGateway) {
        this.encodePasswordGateway = encodePasswordGateway;
    }

    @Override
    public String encode(String password) {
        return this.encodePasswordGateway.encode(password);
    }
}