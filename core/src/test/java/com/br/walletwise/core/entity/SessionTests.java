package com.br.walletwise.core.entity;

import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.core.exception.DomainException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

class SessionTests {
    private Faker faker = new Faker();

    @Test
    @DisplayName("Should throw DomainException if user id is null on build session with session id")
    void shouldThrowDomainExceptionIfUserIdIsNullOnBuildSessionWithSessionId() {
        UUID id = UUID.randomUUID();
        UUID userId = null;
        String token = UUID.randomUUID().toString();
        LocalDateTime creationDate = LocalDateTime.now();

        Throwable exception = catchThrowable(() -> new Session(id, userId, token, creationDate, true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if user id is null on build session with no session id")
    void shouldThrowDomainExceptionIfUserIdIsNullOnBuildSessionWithNoSessionId() {
        UUID userId = null;
        String token = UUID.randomUUID().toString();

        Throwable exception = catchThrowable(() -> new Session(userId, token));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User is required.");
    }

    @Test
    @DisplayName("Should throw DomainException if user id is null on update user id")
    void shouldThrowDomainExceptionIfUserIdIsNullOnUpdateUserId() {
        UUID userId = UUID.randomUUID();
        String token = UUID.randomUUID().toString();

        Session session = new Session(userId, token);
        Throwable exception = catchThrowable(() -> session.setUserId(null));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("User is required.");
    }

    @Test
    @DisplayName("Should return null if user id is not blank on update user id")
    void shouldReturnNullIfUserIdIsNotBlankOnUpdateUserId() {
        UUID userId = UUID.randomUUID();
        String token = UUID.randomUUID().toString();
        UUID updatedUserId = UUID.randomUUID();

        Session session = new Session(userId, token);
        session.setUserId(updatedUserId);

        assertThat(session.getUserId()).isEqualTo(updatedUserId);
        assertThat(session.getToken()).isEqualTo(token);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if token is null or empty on build session with id")
    void shouldThrowsDomainExceptionIfTokenIsNullOrEmptyOnBuildSessionWithId(String tokenParam) {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String token = tokenParam;
        LocalDateTime creationDate = LocalDateTime.now();

        Throwable exception = catchThrowable(() -> new Session(id, userId, token, creationDate, true));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Token is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if token is null or empty on build session with no session id")
    void shouldThrowDomainExceptionIfTokenIsNullOrEmptyOnBuildSessionWithNoSessionId(String tokenParam) {
        UUID userId = UUID.randomUUID();
        String token = null;

        Throwable exception = catchThrowable(() -> new Session(userId, token));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Token is required.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw DomainException if token is null or empty on update token")
    void shouldThrowDomainExceptionIfTokenIsNullOrEmptyOnUpdateToken(String tokenParam) {
        UUID userId = UUID.randomUUID();
        String token = UUID.randomUUID().toString();

        Session session = new Session(userId, token);
        Throwable exception = catchThrowable(() -> session.setToken(null));

        assertThat(exception).isInstanceOf(DomainException.class);
        assertThat(exception.getMessage()).isEqualTo("Token is required.");
    }

    @Test
    @DisplayName("Should return null if token is not blank on update token")
    void shouldReturnNullIfTokenIsNotBlankOnUpdateToken() {
        UUID userId = UUID.randomUUID();
        String token = UUID.randomUUID().toString();
        String updatedString = UUID.randomUUID().toString();

        Session session = new Session(userId, token);
        session.setToken(updatedString);

        assertThat(session.getUserId()).isEqualTo(userId);
        assertThat(session.getToken()).isEqualTo(updatedString);
    }

    @Test
    @DisplayName("Should return session with correct values on build success")
    void shouldReturnSessionWithCorrectValuesOnBuildSuccess() {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String token = UUID.randomUUID().toString();
        LocalDateTime creationDate = LocalDateTime.now();

        Session session = new Session(id, userId, token, creationDate, true);

        assertThat(session.getId()).isEqualTo(id);
        assertThat(session.getUserId()).isEqualTo(userId);
        assertThat(session.getToken()).isEqualTo(token);
        assertThat(session.getCreationDate()).isEqualTo(creationDate);
        assertThat(session.isActive()).isTrue();
    }
}
