package com.br.walletwise.infra.entrypoint.expense;

import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.usecase.expense.GetFixedExpenses;
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

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class GetFixedExpensesControllerTests {
    private final String URL = "/fixed-expenses";

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private MockMvc mvc;

    @MockBean
    private GetFixedExpenses usecase;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @DisplayName("Should return 500 if  unexpected exception is thrown")
    void shouldTReturn500IfUnexpectedExceptionIsThrown() throws Exception {
        doThrow(HttpServerErrorException.InternalServerError.class).when(this.usecase).get();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(this.URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("body",
                        Matchers.is("An unexpected error occurred. Please try again later.")));

        verify(this.usecase, times(1)).get();
    }

    @Test
    @DisplayName("Should return 200 on success")
    void shouldTReturn200IfOnSuccess() throws Exception {
        User user = MocksFactory.userFactory();
        List<FixedExpenseModel> fixedExpenseModelList =
                List.of(MocksFactory.fixedExpenseModelFactory(user), MocksFactory.fixedExpenseModelFactory(user));

        when(this.usecase.get()).thenReturn(fixedExpenseModelList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(this.URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk());

        verify(this.usecase, times(1)).get();
    }
}
