package com.br.walletwise.core.domain.entity;

import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.core.validation.ValidationBuilder;
import com.br.walletwise.core.validation.validator.contracts.IValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Session extends AbstractEntity{
    private UUID id;
    private UUID userId;
    private String token;
    private LocalDateTime expirationDate;
    private LocalDateTime  creationDate;

    public Session(UUID id, UUID userId, String token, LocalDateTime expirationDate, LocalDateTime creationDate) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;

        String error = this.validate();
        if(error != null) throw new  DomainException(error);
    }

    public Session(UUID userId, String token, LocalDateTime expirationDate, LocalDateTime creationDate) {
        this.userId = userId;
        this.token = token;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    protected List<IValidator> buildValidators() {
        List<IValidator> validators = new ArrayList<IValidator>();
        validators.addAll(ValidationBuilder.of("User", this.userId).required().build());
        return validators;
    }
}