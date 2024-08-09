package com.br.walletwise.infra.entrypoint;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.entrypoint.dto.CreateUserRequest;
import com.br.walletwise.infra.mappers.UserMapper;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.usecase.CreateUser;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @DisplayName("Should throw unexpected exception if the the service throws")
    void shouldThrowUnexpectedException() throws Exception {
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
                .andExpect(status().isInternalServerError());

        verify(createUser, times(1)).create(user);
    }

}
