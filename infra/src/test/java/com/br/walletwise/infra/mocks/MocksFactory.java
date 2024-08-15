package com.br.walletwise.infra.mocks;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.Session;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.CategoryEnum;
import com.br.walletwise.infra.entrypoint.dto.CreateUserRequest;
import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import com.br.walletwise.infra.persistence.entity.SessionJpaEntity;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import com.github.javafaker.Faker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MocksFactory {
    public static Faker faker = new Faker();

    public static User userWithNoIdFactory() {
        return new User(
                null,
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                "Password!1234H",
                true);
    }

    public static User userFactory() {
        return new User(
                UUID.randomUUID(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                "Password!1234H",
                true);
    }

    public static User userFactory(UserJpaEntity entity) {
        return new User(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.isActive()
        );
    }

    public static User userFactory(CreateUserRequest request) {
        return new User(
                request.firstname(),
                request.lastname(),
                request.username(),
                request.email(),
                request.password()
        );
    }

    public static UserJpaEntity userJpaEntityFactory() {
        return new UserJpaEntity(
                UUID.randomUUID(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                "Password!1234H",
                true,
                LocalDateTime.now(),
                LocalDateTime.now(),
                List.of()
        );
    }

    public static UserJpaEntity userJpaEntityFactory(User user){
        return UserJpaEntity
                .builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.isActive())
                .build();
    }

    public static UserJpaEntity userJpaEntityFactory(UserJpaEntity user){
        return UserJpaEntity
                .builder()
                .id(UUID.randomUUID())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public  static CreateUserRequest createUserRequestFactory(){
        return new CreateUserRequest(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().emailAddress(),
                "Password!1234H");
    }

    public static Authentication authenticationFactory (){
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "";
            }
        };
    }

    public static Session sessionFactoryWithNoId(){
        return new Session(UUID.randomUUID(),UUID.randomUUID().toString());
    }

    public static Session sessionFactoryId(){
        return new Session(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID().toString(),LocalDateTime.now(),true);
    }

    public static Session sessionFactory(SessionJpaEntity entity){
        return new Session(entity.getId(), entity.getUser().getId(),entity.getToken(), entity.getCreationDate(),true);
    }

    public static SessionJpaEntity sessionJpaEntityFactory(Session session){
        return  SessionJpaEntity
                .builder()
                .id(session.getId())
                .user(UserJpaEntity.builder().id(session.getUserId()).build())
                .token(session.getToken())
                .creationDate(session.getCreationDate())
                .build();
    }

    public static SessionJpaEntity sessionJpaEntityFactory(){
        return  SessionJpaEntity
                .builder()
                .id(UUID.randomUUID())
                .user(UserJpaEntity.builder().id(UUID.randomUUID()).build())
                .token(UUID.randomUUID().toString())
                .creationDate(LocalDateTime.now())
                .build();
    }

    public static SessionJpaEntity sessionJpaEntityFactory(SessionJpaEntity entity){
        return  SessionJpaEntity
                .builder()
                .id(UUID.randomUUID())
                .id(entity.getId())
                .user(UserJpaEntity.builder().id(entity.getUser().getId()).build())
                .token(entity.getToken())
                .creationDate(entity.getCreationDate())
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static FixedExpense fixedExpenseFactory(){
        return new FixedExpense(
                UUID.randomUUID(),
                faker.lorem().word(),
                20,
                CategoryEnum.SCHOOL.getValue(),
                new BigDecimal(200),
                new Date(),
                Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()),
                true
        );
    }

    public static FixedExpenseJpaEntity fixedExpenseJpaEntityFactory(FixedExpense fixedExpense){
        return  FixedExpenseJpaEntity
                .builder()
                .id(fixedExpense.getId())
                .user(UserJpaEntity.builder().id(fixedExpense.getUserId()).build())
                .description(fixedExpense.getDescription())
                .dueDay(fixedExpense.getDueDay())
                .amount(fixedExpense.getAmount())
                .category(fixedExpense.getCategory())
                .starDate(fixedExpense.getStartDate())
                .endDate(fixedExpense.getEndDate())
                .active(fixedExpense.isActive())
                .build();
    }
}