package com.br.walletwise.infra.config;

import com.br.walletwise.application.gateway.EncodePasswordGateway;
import com.br.walletwise.application.usecasesimpl.EncodePasswordImpl;
import com.br.walletwise.usecase.EncodePassword;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public EncodePassword encodePassword(EncodePasswordGateway encodePasswordGateway) {
        return new EncodePasswordImpl(encodePasswordGateway);
    }
}
