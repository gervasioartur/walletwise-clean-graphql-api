package com.br.walletwise.application.usecaseimpl.expense;

import com.br.walletwise.application.gateway.expense.DeleteFixedExpenseGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.expense.DeleteFixedExpenseImpl;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.usecase.cache.InvalidateCache;
import com.br.walletwise.usecase.expense.DeleteFixedExpense;
import com.br.walletwise.usecase.expense.GetFixedExpense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

class DeleteFixedExpenseImplTests {
    private DeleteFixedExpense deleteFixedExpense;

    private GetFixedExpense getFixedExpense;
    private DeleteFixedExpenseGateway deleteFixedExpenseGateway;
    private InvalidateCache invalidateCache;

    @BeforeEach
    void setUp() {
        this.getFixedExpense = mock(GetFixedExpense.class);
        this.invalidateCache = mock(InvalidateCache.class);
        this.deleteFixedExpenseGateway = mock(DeleteFixedExpenseGateway.class);
        this.deleteFixedExpense = new DeleteFixedExpenseImpl(getFixedExpense,
                deleteFixedExpenseGateway,
                invalidateCache);
    }

    @Test
    @DisplayName("Should throw NotFoundException if Fixed expense does not exist")
    void shouldThrowNotFoundExceptionIfFixedExpenseDoesNotExist() {
        long expenseCode = 1;
        User user = MocksFactory.userFactory();

        when(this.getFixedExpense.get(expenseCode)).thenReturn(Optional.empty());

        Throwable exception = catchThrowable(() -> this.deleteFixedExpense.delete(expenseCode));

        assertThat(exception).isInstanceOf(NotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo("Resource not found.");
        verify(this.getFixedExpense, times(1)).get(expenseCode);
    }

    @Test
    @DisplayName("Should delete Fixed expense")
    void shouldDeleteFixedExpense() {
        long expenseCode = 1;
        User user = MocksFactory.userFactory();
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory(user);

        when(this.getFixedExpense.get(expenseCode)).thenReturn(Optional.of(fixedExpense));
        fixedExpense.setActive(false);
        doNothing().when(this.deleteFixedExpenseGateway).delete(fixedExpense);
        doNothing().when(this.invalidateCache).delete("fixedExpenses:" + user.getId());

        this.deleteFixedExpense.delete(expenseCode);

        verify(this.getFixedExpense, times(1)).get(expenseCode);
        verify(this.deleteFixedExpenseGateway, times(1)).delete(fixedExpense);
        verify(this.invalidateCache, times(1)).delete("fixedExpenses:" + user.getId());
    }
}
