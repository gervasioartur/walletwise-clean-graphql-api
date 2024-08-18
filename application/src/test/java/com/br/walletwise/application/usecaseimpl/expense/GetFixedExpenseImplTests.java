package com.br.walletwise.application.usecaseimpl.expense;

import com.br.walletwise.application.gateway.expense.GetFixedExpenseGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.expense.GetFixedExpenseImpl;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.usecase.expense.GetFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetFixedExpenseImplTests {
    private GetFixedExpense getFixedExpense;
    private GetFixedExpenseGateway getFixedExpenseGateway;
    private GetLoggedUser getLoggedUser;

    @BeforeEach
    void setUp() {
        this.getFixedExpenseGateway = mock(GetFixedExpenseGateway.class);
        this.getLoggedUser = mock(GetLoggedUser.class);
        this.getFixedExpense = new GetFixedExpenseImpl(getFixedExpenseGateway, getLoggedUser);
    }

    @Test
    @DisplayName("Should return optional of fixed expense ")
    void shouldReturnEmptyOptionalOfFixedExpense() {
        User user = MocksFactory.userFactory();
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory(user);

        when(this.getLoggedUser.get()).thenReturn(user);
        when(this.getFixedExpenseGateway.get(fixedExpense.getId(), user.getId())).thenReturn(Optional.of(fixedExpense));

        Optional<FixedExpense> result = this.getFixedExpense.get(fixedExpense.getId());

        assertThat(result.get().getUserId()).isEqualTo(fixedExpense.getUserId());
        verify(this.getFixedExpenseGateway, times(1)).get(fixedExpense.getId(), user.getId());
    }

    @Test
    @DisplayName("Should throw NotFoundException if fixed expense does not exist")
    void shouldReturnThrowNotFoundExceptionIfFixedExpenseDoesNotExist() {
        User user = MocksFactory.userFactory();
        FixedExpenseModel fixedExpense = MocksFactory.fixedExpenseModelFactory(user);

        when(this.getLoggedUser.get()).thenReturn(user);
        when(this.getFixedExpenseGateway.getModel(fixedExpense.getExpenseCode(), user.getId()))
                .thenReturn(Optional.empty());

        Throwable exception = Assertions.catchThrowable((() -> this.getFixedExpense.getModel(fixedExpense.getExpenseCode())));

        assertThat(exception).isInstanceOf(NotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo(exception.getMessage());
        verify(this.getLoggedUser, times(1)).get();
        verify(this.getFixedExpenseGateway, times(1))
                .getModel(fixedExpense.getExpenseCode(), user.getId());
    }

    @Test
    @DisplayName("Should return fixed expense model")
    void shouldReturnFixedExpenseModel() {
        User user = MocksFactory.userFactory();
        FixedExpenseModel fixedExpense = MocksFactory.fixedExpenseModelFactory(user);

        when(this.getLoggedUser.get()).thenReturn(user);
        when(this.getFixedExpenseGateway.getModel(fixedExpense.getExpenseCode(), user.getId()))
                .thenReturn(Optional.of(fixedExpense));

        FixedExpenseModel result = this.getFixedExpense.getModel(fixedExpense.getExpenseCode());

        assertThat(result.getDueDay()).isEqualTo(fixedExpense.getDueDay());
        verify(this.getLoggedUser, times(1)).get();
        verify(this.getFixedExpenseGateway, times(1))
                .getModel(fixedExpense.getExpenseCode(), user.getId());
    }
}