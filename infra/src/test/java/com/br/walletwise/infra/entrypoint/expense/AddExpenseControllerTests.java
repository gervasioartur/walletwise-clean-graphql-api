package com.br.walletwise.infra.entrypoint.expense;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.infra.entrypoint.dto.AddFixedExpenseRequest;
import com.br.walletwise.infra.mappers.FixedExpenseMapper;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.usecase.expense.AddFixedExpense;
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
public class AddExpenseControllerTests {

    private final String URL = "/fixed-expenses";

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private MockMvc mvc;

    @MockBean
    private AddFixedExpense usecase;
    @MockBean
    private FixedExpenseMapper mapper;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @DisplayName("Should return 500 if  unexpected exception is thrown")
    void shouldTReturn500IfUnexpectedExceptionIsThrown() throws Exception {
        AddFixedExpenseRequest requestParams = MocksFactory.addFixedExpenseRequestFactory();

        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory(requestParams);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        when(this.mapper.map(requestParams)).thenReturn(fixedExpense);
        doThrow(HttpServerErrorException.InternalServerError.class).when(this.usecase).add(fixedExpense);

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

        verify(this.usecase, times(1)).add(fixedExpense);
    }

    @Test
    @DisplayName("Should return bad request if user case throws DomainException")
    void shouldTReturnBadRequestIfUserCaseThrowsDomainException() throws Exception {
        AddFixedExpenseRequest requestParams = MocksFactory.addFixedExpenseRequestFactory();

        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory(requestParams);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        when(this.mapper.map(requestParams)).thenReturn(fixedExpense);
        doThrow(DomainException.class).when(this.usecase).add(fixedExpense);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(this.URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest());

        verify(this.usecase, times(1)).add(fixedExpense);
    }

    @Test
    @DisplayName("Should return created on add expense success")
    void shouldTReturnCreatedOnAddFixedExpenseSuccess() throws Exception {
        AddFixedExpenseRequest requestParams = MocksFactory.addFixedExpenseRequestFactory();

        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory(requestParams);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        when(this.mapper.map(requestParams)).thenReturn(fixedExpense);
        doNothing().when(this.usecase).add(fixedExpense);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(this.URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("body", Matchers.is("Expense Added.")));

        verify(this.usecase, times(1)).add(fixedExpense);
    }
}
