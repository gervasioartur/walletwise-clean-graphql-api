package com.br.walletwise.application.usecaseimpl.expense;

import com.br.walletwise.application.gateway.expense.AddFixedExpenseGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.expense.AddFixedExpenseImpl;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.expense.AddFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AddFixedExpenseImplTests {
    private AddFixedExpense addFixedExpense;

    private GetLoggedUser getLoggedUser;
    private AddFixedExpenseGateway addFixedExpenseGateway;

    @BeforeEach
    void setUp() {
        this.getLoggedUser = mock(GetLoggedUser.class);
        this.addFixedExpenseGateway = mock(AddFixedExpenseGateway.class);
        this.addFixedExpense = new AddFixedExpenseImpl(getLoggedUser, addFixedExpenseGateway);
    }

    @Test
    @DisplayName("Should add FixedExpense")
    void shouldAddFixedExpense() {
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory();
        User user = MocksFactory.userFactory(fixedExpense.getUserId());

        when(this.getLoggedUser.get()).thenReturn(user);
        doNothing().when(this.addFixedExpenseGateway).add(fixedExpense);

        this.addFixedExpense.add(fixedExpense);

        verify(this.getLoggedUser, times(1)).get();
        verify(this.addFixedExpenseGateway, times(1)).add(fixedExpense);
    }
}