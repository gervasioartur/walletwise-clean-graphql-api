package com.br.walletwise.core.domain.entity;

import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.core.validation.ValidationBuilder;
import com.br.walletwise.core.validation.validator.contract.Validator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Session extends AbstractEntity{
    private UUID id;
    private UUID userId;
    private String token;
    private LocalDateTime  creationDate;
    private boolean active;

    public Session(UUID id, UUID userId, String token, LocalDateTime creationDate, boolean active) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.creationDate = creationDate;
        this.active = active;

        String error =  this.validate();
        if(error != null) throw new DomainException(error);
    }

    public Session(UUID userId, String token) {
        this.userId = userId;
        this.token = token;
        this.creationDate = LocalDateTime.now();

        String error =  this.validate();
        if(error != null) throw new DomainException(error);
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;

        String error =  this.validate();
        if(error != null) throw new DomainException(error);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        String error =  this.validate();
        if(error != null) throw new DomainException(error);
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    protected List<Validator> buildValidators() {
        List<Validator> validators = new ArrayList<Validator>();
        validators.addAll(ValidationBuilder.of("User", this.userId).required().build());
        validators.addAll(ValidationBuilder.of("Token", this.token).required().build());
        return validators;
    }
}