package com.br.walletwise.infra.entrypoint.expense;

import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.core.exception.DomainException;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.infra.entrypoint.dto.UpdateFixedExpenseRequest;
import com.br.walletwise.infra.mappers.FixedExpenseMapper;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.usecase.expense.UpdateFixedExpense;
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
public class UpdateFixedExpenseControllerTests {

    private final String URL = "/fixed-expenses";

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private MockMvc mvc;

    @MockBean
    private UpdateFixedExpense usecase;
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
        long expenseCode = 2;

        UpdateFixedExpenseRequest requestParams = MocksFactory.updateFixedExpenseRequestFactory();
        FixedExpenseModel fixedExpense = MocksFactory.fixedExpenseFactory(requestParams);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        when(this.mapper.map(requestParams)).thenReturn(fixedExpense);
        doThrow(HttpServerErrorException.InternalServerError.class).when(this.usecase).update(fixedExpense);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(this.URL + "/" + expenseCode)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("body",
                        Matchers.is("An unexpected error occurred. Please try again later.")));

        verify(this.usecase, times(1)).update(fixedExpense);
    }

    @Test
    @DisplayName("Should return not found if use case throws NotFoundException")
    void shouldTReturnNotFoundIfUseCaseThrowsDomainException() throws Exception {
        long expenseCode = 2;
        UpdateFixedExpenseRequest requestParams = MocksFactory.updateFixedExpenseRequestFactory();

        FixedExpenseModel fixedExpense = MocksFactory.fixedExpenseFactory(requestParams);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        when(this.mapper.map(requestParams)).thenReturn(fixedExpense);
        doThrow(NotFoundException.class).when(this.usecase).update(fixedExpense);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(this.URL + "/" + expenseCode)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isNotFound());

        verify(this.usecase, times(1)).update(fixedExpense);
    }

    @Test
    @DisplayName("Should return not found if use case throws DomainException")
    void shouldTReturnBadRequestIfUseCaseThrowsDomainException() throws Exception {
        long expenseCode = 2;
        UpdateFixedExpenseRequest requestParams = MocksFactory.updateFixedExpenseRequestFactory();

        FixedExpenseModel fixedExpense = MocksFactory.fixedExpenseFactory(requestParams);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        when(this.mapper.map(requestParams)).thenReturn(fixedExpense);
        doThrow(DomainException.class).when(this.usecase).update(fixedExpense);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(this.URL + "/" + expenseCode)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest());

        verify(this.usecase, times(1)).update(fixedExpense);
    }

    @Test
    @DisplayName("Should return ok on updated fixed expense success")
    void shouldTReturnOKOnAddFixedExpenseSuccess() throws Exception {
        long expenseCode = 2;
        UpdateFixedExpenseRequest requestParams = MocksFactory.updateFixedExpenseRequestFactory();

        FixedExpenseModel fixedExpense = MocksFactory.fixedExpenseFactory(requestParams);

        String json = new ObjectMapper().writeValueAsString(requestParams);

        when(this.mapper.map(requestParams)).thenReturn(fixedExpense);
        doNothing().when(this.usecase).update(fixedExpense);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(this.URL + "/" + expenseCode)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("body", Matchers.is("Expense successfully  updated.")));

        verify(this.usecase, times(1)).update(fixedExpense);
    }
}