package com.br.walletwise.application.usecaseimpl.expense;

import com.br.walletwise.application.gateway.expense.AddFixedExpenseGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.expense.AddFixedExpenseImpl;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.cache.InvalidateCache;
import com.br.walletwise.usecase.expense.AddFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.Mockito.*;

class AddFixedExpenseImplTests {
    private AddFixedExpense addFixedExpense;

    private GetLoggedUser getLoggedUser;
    private AddFixedExpenseGateway addFixedExpenseGateway;
    private InvalidateCache invalidateCache;

    @BeforeEach
    void setUp() {
        this.getLoggedUser = mock(GetLoggedUser.class);
        this.addFixedExpenseGateway = mock(AddFixedExpenseGateway.class);
        this.invalidateCache = mock(InvalidateCache.class);
        this.addFixedExpense = new AddFixedExpenseImpl(addFixedExpenseGateway, getLoggedUser, invalidateCache);
    }

    @Test
    @DisplayName("Should add FixedExpense")
    void shouldAddFixedExpense() {
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory();
        fixedExpense.setUserId(UUID.randomUUID());
        fixedExpense.setActive(true);
        User user = MocksFactory.userFactory(fixedExpense.getUserId());

        when(this.getLoggedUser.get()).thenReturn(user);
        doNothing().when(this.addFixedExpenseGateway).add(fixedExpense);
        doNothing().when(this.invalidateCache).delete(user.getId().toString());

        this.addFixedExpense.add(fixedExpense);

        verify(this.getLoggedUser, times(1)).get();
        verify(this.addFixedExpenseGateway, times(1)).add(fixedExpense);
        verify(this.invalidateCache, times(1)).delete("fixedExpenses:"+user.getId());
    }
}