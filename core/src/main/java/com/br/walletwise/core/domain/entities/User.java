package com.br.walletwise.core.domain.entities;

import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.core.validation.validators.contracts.IValidator;
import com.br.walletwise.core.validation.ValidationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User extends  AbstractEntity{
    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private boolean active;

    public User(UUID id, String firstname, String lastname, String username, String email, String password, boolean active) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;

        String error = this.validate();
        if (error != null) throw new DomainException(error);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
        String error = this.validate();
        if (error != null) throw new DomainException(error);
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
        String error = this.validate();
        if (error != null) throw new DomainException(error);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        String error = this.validate();
        if (error != null) throw new DomainException(error);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    protected List<IValidator> buildValidators() {
        List<IValidator> validators = new ArrayList<>();
        validators.addAll(ValidationBuilder.of("Firstname",this.firstname).required().build());
        validators.addAll(ValidationBuilder.of("Lastname",this.lastname).required().build());
        validators.addAll(ValidationBuilder.of("Username",this.username).required().username().build());
        validators.addAll(ValidationBuilder.of("E-mail",this.email).required().username().build());
        return validators;
    }
}