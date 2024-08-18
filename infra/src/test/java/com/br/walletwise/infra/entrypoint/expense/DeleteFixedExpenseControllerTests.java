package com.br.walletwise.infra.entrypoint.expense;

import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.infra.entrypoint.dto.UpdateFixedExpenseRequest;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.usecase.expense.DeleteFixedExpense;
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
public class DeleteFixedExpenseControllerTests {

    private final String URL = "/fixed-expenses";

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private MockMvc mvc;

    @MockBean
    private DeleteFixedExpense usecase;

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

        String json = new ObjectMapper().writeValueAsString(requestParams);

        doThrow(HttpServerErrorException.InternalServerError.class).when(this.usecase).delete(expenseCode);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(this.URL + "/" + expenseCode)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("body",
                        Matchers.is("An unexpected error occurred. Please try again later.")));

        verify(this.usecase, times(1)).delete(expenseCode);
    }

    @Test
    @DisplayName("Should return not found if use case throws NotFoundException")
    void shouldTReturnNotFoundIfUseCaseThrowsDomainException() throws Exception {
        long expenseCode = 2;
        UpdateFixedExpenseRequest requestParams = MocksFactory.updateFixedExpenseRequestFactory();

        String json = new ObjectMapper().writeValueAsString(requestParams);

        doThrow(NotFoundException.class).when(this.usecase).delete(expenseCode);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(this.URL + "/" + expenseCode)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isNotFound());

        verify(this.usecase, times(1)).delete(expenseCode);
    }

    @Test
    @DisplayName("Should return ok on delete fixed expense success")
    void shouldTReturnOKOnDeleteFixedExpenseSuccess() throws Exception {
        long expenseCode = 2;
        UpdateFixedExpenseRequest requestParams = MocksFactory.updateFixedExpenseRequestFactory();

        String json = new ObjectMapper().writeValueAsString(requestParams);

        doNothing().when(this.usecase).delete(expenseCode);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(this.URL + "/" + expenseCode)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("body", Matchers.is("OK")));

        verify(this.usecase, times(1)).delete(expenseCode);
    }
}