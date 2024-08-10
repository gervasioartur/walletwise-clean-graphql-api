package com.br.walletwise.core.entity;

import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.core.exception.DomainException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class SessionTests {
    private Faker faker = new Faker();

    @Test
    @DisplayName("Should throw DomainException if user id is null on build session with session id")
    void shouldThrowDomainExceptionIfUserIdIsNullOnBuildSessionWithSessionId() {
        UUID id = UUID.randomUUID();
        UUID userId = null;
        String token = UUID.randomUUID().toString();
        LocalDateTime expirationDate = LocalDateTime.now();
        LocalDateTime creationDate = LocalDateTime.now().plusMinutes(15);

        Throwable exception = catchThrowable(() -> new  Session(id ,userId, token, expirationDate, creationDate));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if user id is null on build session with no session id")
    void shouldThrowDomainExceptionIfUserIdIsNullOnBuildSessionWithNoSessionId() {
        UUID userId = null;
        String token = UUID.randomUUID().toString();
        LocalDateTime expirationDate = LocalDateTime.now();
        LocalDateTime creationDate = LocalDateTime.now().plusMinutes(15);

        Throwable exception = catchThrowable(() -> new  Session(userId, token, expirationDate, creationDate));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User is required.");
    }

    @Test
    @DisplayName("Should throw DomainException user id is null on update user id")
    void shouldThrowDomainExceptionIfUserIdIsNullOnUpdateUserId() {
        UUID userId = UUID.randomUUID();
        String token = UUID.randomUUID().toString();
        LocalDateTime expirationDate = LocalDateTime.now();
        LocalDateTime creationDate = LocalDateTime.now().plusMinutes(15);

        Session session =  new Session(userId, token, expirationDate, creationDate);
        Throwable exception = catchThrowable(() -> session.setUserId(null));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User is required.");
    }
}
