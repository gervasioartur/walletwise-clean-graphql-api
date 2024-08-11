package com.br.walletwise.infra.entrypoint;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.exception.ConflictException;
import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.infra.entrypoint.dto.CreateUserRequest;
import com.br.walletwise.infra.mappers.UserMapper;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.usecase.CreateUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTests {
    private final String URL = "/users";

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private MockMvc mvc;

    @MockBean
    private CreateUser createUser;
    @MockBean
    private UserMapper mapper;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @DisplayName("Should return 500 if  unexpected exception is thrown")
    void shouldTReturn500IfUnexpectedExceptionIsThrown() throws Exception {
        CreateUserRequest requestParams = MocksFactory.createUserRequestFactory();
        User user = MocksFactory.userFactory(requestParams);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        when(this.mapper.map(requestParams)).thenReturn(user);
        doThrow(HttpServerErrorException.InternalServerError.class).when(this.createUser).create(user);

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

        verify(createUser, times(1)).create(user);
    }

    @Test
    @DisplayName("Should return 409 if conflict exception is thrown")
    void shouldTReturn409IfConflictExceptionIsThrown() throws Exception {
        CreateUserRequest requestParams = MocksFactory.createUserRequestFactory();
        User user = MocksFactory.userFactory(requestParams);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        when(this.mapper.map(requestParams)).thenReturn(user);
        doThrow(new ConflictException("any exception")).when(this.createUser).create(user);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(this.URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isConflict())
                .andExpect(jsonPath("body", Matchers.is("any exception")));

        verify(createUser, times(1)).create(user);
    }

    @Test
    @DisplayName("Should return 400 if domain  exception is thrown")
    void shouldTReturn400IfDomainExceptionIsThrown() throws Exception {
        CreateUserRequest requestParams = MocksFactory.createUserRequestFactory();
        User user = MocksFactory.userFactory(requestParams);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        when(this.mapper.map(requestParams)).thenReturn(user);
        doThrow(new DomainException("any exception")).when(this.createUser).create(user);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(this.URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("body", Matchers.is("any exception")));

        verify(createUser, times(1)).create(user);
    }

    @Test
    @DisplayName("Should return 200 on create user success")
    void shouldTReturn200OnCreatUserSuccess() throws Exception {
        CreateUserRequest requestParams = MocksFactory.createUserRequestFactory();
        User user = MocksFactory.userFactory(requestParams);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        when(this.mapper.map(requestParams)).thenReturn(user);
        doNothing().when(this.createUser).create(user);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(this.URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("body", Matchers.is("User Created.")));

        verify(createUser, times(1)).create(user);
    }
}
