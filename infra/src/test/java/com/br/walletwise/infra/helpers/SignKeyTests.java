package com.br.walletwise.infra.helpers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Key;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SignKeyTests {
    @Autowired
    private SignKey signKey;

    @Test
    @DisplayName("Should return key")
    void shouldReturnKey() {
        Key result = this.signKey.getSignKey();
        result.getAlgorithm();
        assertThat(result).isNotNull();
        assertThat(result.getAlgorithm()).isEqualTo("HmacSHA384");
    }
}
