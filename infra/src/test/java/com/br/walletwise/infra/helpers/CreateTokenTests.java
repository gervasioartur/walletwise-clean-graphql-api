package com.br.walletwise.infra.helpers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class CreateTokenTests {
    @Autowired
    private CreateToken createToken;

    @Test
    @DisplayName("Should create token")
    public void shouldCreateToken() {
        String username = "any_username";
        Map<String, Object> claims = new HashMap<>();
        String resul =  this.createToken.create(claims,username);
        assertThat(resul).isNotNull();
    }
}
