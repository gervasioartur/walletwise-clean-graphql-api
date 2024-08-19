package com.br.walletwise.infra.entrypoint;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.exception.ConflictException;
import com.br.walletwise.core.exception.UnexpectedException;
import com.br.walletwise.infra.entrypoint.dto.CreateUserInput;
import com.br.walletwise.infra.mappers.UserMapper;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.usecase.user.CreateUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@GraphQlTest
class CreateUserControllerTests {
    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private CreateUser createUser;
    @MockBean
    private UserMapper mapper;

    private CreateUserInput createUserInput;

    @BeforeEach
    public void setUp() {
        createUserInput = new CreateUserInput("John", "Doe", "johndoe", "john@example.com", "password123");
    }


    @Test
    @DisplayName("Should return general exception message if useCase trows unexpected exception")
    void shouldReturnGeneralExceptionMessageIfUseCaseTrowsUnexpectedException() {
        User user =  MocksFactory.userFactory(this.createUserInput);

        when(this.mapper.map(this.createUserInput)).thenReturn(user);
        doThrow(new UnexpectedException("Any exception")).when(this.createUser).create(user);

        String mutation = "mutation { createUser(input: {firstname: \"John\", lastname: \"Doe\", username: \"johndoe\", email: \"john@example.com\", password: \"password123\"}) { body } }";

        graphQlTester.document(mutation)
                .execute()
                .path("createUser.body")
                .entity(String.class)
                .isEqualTo("An unexpected error occurred. Please try again later.");
    }

    @Test
    @DisplayName("Should return conflict exception message if useCase throws conflict exception")
    void shouldReturnConflictExceptionMessageIfUseCaseThrowsConflictException() {
        User user =  MocksFactory.userFactory(this.createUserInput);

        when(this.mapper.map(this.createUserInput)).thenReturn(user);
        doThrow(new ConflictException("Any exception.")).when(this.createUser).create(user);

        String mutation = "mutation { createUser(input: {firstname: \"John\", lastname: \"Doe\", username: \"johndoe\", email: \"john@example.com\", password: \"password123\"}) { body } }";

        graphQlTester.document(mutation)
                .execute()
                .path("createUser.body")
                .entity(String.class)
                .isEqualTo("Any exception.");
    }
}
