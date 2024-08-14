package com.br.walletwise.infra.config;

import com.br.walletwise.application.gateway.*;
import com.br.walletwise.application.usecasesimpl.user.*;
import com.br.walletwise.usecase.user.*;
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

    @Bean
    public GenerateToken generateToken(GenerateTokenGateway generateTokenGateway){
        return new GenerateTokenImpl(generateTokenGateway);
    }

    @Bean
    public SaveSession saveSession(SaveSessionGateway saveSessionGateway){
        return new SaveSessionImpl(saveSessionGateway);
    }

    @Bean
    public AuthenticateUser authenticateUser(FindByUsername findByUsername,
                                             FindByEmail findByEmail,
                                             GenerateToken generateToken,
                                             AuthenticateUserGateway authenticateUserGateway,
                                             SaveSession saveSession){
        return new AuthenticateUserImpl(findByUsername, findByEmail, generateToken, authenticateUserGateway, saveSession);
    }
}
