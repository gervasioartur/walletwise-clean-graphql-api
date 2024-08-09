package com.br.walletwise.infra.config;

import com.br.walletwise.application.gateway.CreateUserGateway;
import com.br.walletwise.application.gateway.FindByEmailGateway;
import com.br.walletwise.application.gateway.FindByUsernameGateway;
import com.br.walletwise.application.usecasesimpl.CreateUserImpl;
import com.br.walletwise.application.usecasesimpl.FindByEmailImpl;
import com.br.walletwise.application.usecasesimpl.FindByUsernameImpl;
import com.br.walletwise.usecase.CreateUser;
import com.br.walletwise.usecase.EncodePassword;
import com.br.walletwise.usecase.FindByEmail;
import com.br.walletwise.usecase.FindByUsername;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public CreateUser createUser(FindByUsername findByUsername,
                                 FindByEmail findByEmail,
                                 EncodePassword encodePassword,
                                 CreateUserGateway createUserGateway) {

        return new CreateUserImpl(findByUsername, findByEmail, encodePassword, createUserGateway);
    }

    @Bean
    public FindByUsername findByUsername(FindByUsernameGateway findByUsernameGateway) {
        return new FindByUsernameImpl(findByUsernameGateway);
    }

    @Bean
    public FindByEmail findByEmail(FindByEmailGateway findByEmailGateway) {
        return new FindByEmailImpl(findByEmailGateway);
    }
}
