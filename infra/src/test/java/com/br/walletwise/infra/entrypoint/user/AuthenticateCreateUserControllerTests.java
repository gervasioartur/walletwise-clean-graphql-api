package com.br.walletwise.infra.entrypoint.user;


import com.br.walletwise.core.exception.UnauthorizedException;
import com.br.walletwise.infra.entrypoint.dto.AuthenticateUserRequest;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.usecase.user.AuthenticateUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AuthenticateCreateUserControllerTests {
    private final String URL = "/users/authenticate";

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private MockMvc mvc;

    @MockBean
    private AuthenticateUser usecase;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_username", "anyuser@email.com"})
    @DisplayName("Should return InternalServerError if an unexpected exception occurs")
    public void shouldReturnInternalServerErrorIfAnUnexpectedExceptionOccurs(String usernameOrEmail) throws Exception {
        AuthenticateUserRequest requestParams = new AuthenticateUserRequest
                (usernameOrEmail, MocksFactory.faker.internet().password());

        String json = new ObjectMapper().writeValueAsString(requestParams);

        doThrow(HttpServerErrorException.InternalServerError.class).when(this.usecase)
                .authenticate(requestParams.usernameOrEmail(), requestParams.password());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(this.URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("body",
                        Matchers.is("An unexpected error occurred. Please try again later.")));

        verify(usecase, times(1))
                .authenticate(requestParams.usernameOrEmail(), requestParams.password());
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_username", "anyuser@email.com"})
    @DisplayName("Should throw Unauthorized if user credentials are wrong")
    void shouldThrowUnauthorizedIfUserCredentialsAreWrong(String password) throws Exception {
        AuthenticateUserRequest requestParams = new AuthenticateUserRequest
                (MocksFactory.faker.name().username(), password);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        doThrow(new UnauthorizedException("Bad credentials.")).when(this.usecase)
                .authenticate(requestParams.usernameOrEmail(), requestParams.password());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(this.URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("body",
                        Matchers.is("Bad credentials.")));

        verify(usecase, times(1))
                .authenticate(requestParams.usernameOrEmail(), requestParams.password());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw BadRequest if username Or email is empty or null")
    void shouldThrowBadRequestIfUsernameOrEmailIsEmptyOrNull(String usernameOrEmail) throws Exception {
        AuthenticateUserRequest requestParams = new AuthenticateUserRequest
                (usernameOrEmail, MocksFactory.faker.internet().password());

        String json = new ObjectMapper().writeValueAsString(requestParams);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(this.URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("body",
                        Matchers.is("Username or E-mail is required.")));

        verify(usecase, times(0))
                .authenticate(requestParams.usernameOrEmail(), requestParams.password());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Should throw BadRequest if password is empty or null")
    void shouldThrowBadRequestIfPasswordIsEmptyOrNull(String password) throws Exception {
        AuthenticateUserRequest requestParams = new AuthenticateUserRequest
                (MocksFactory.faker.name().username(), password);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(this.URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("body",
                        Matchers.is("Password is required.")));

        verify(usecase, times(0))
                .authenticate(requestParams.usernameOrEmail(), requestParams.password());
    }

    @ParameterizedTest
    @ValueSource(strings = {"any_username", "anyuser@email.com"})
    @DisplayName("Should return token on authenticate success")
    public void shouldReturnTokenOnAuthenticateSuccess(String usernameOrEmail) throws Exception {
        String token = UUID.randomUUID().toString();

        AuthenticateUserRequest requestParams = new AuthenticateUserRequest
                (usernameOrEmail, MocksFactory.faker.internet().password());

        String json = new ObjectMapper().writeValueAsString(requestParams);

        when(this.usecase.authenticate(requestParams.usernameOrEmail(), requestParams.password())).thenReturn(token);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(this.URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("body",
                        Matchers.is(token)));

        verify(usecase, times(1))
                .authenticate(requestParams.usernameOrEmail(), requestParams.password());
    }
}
