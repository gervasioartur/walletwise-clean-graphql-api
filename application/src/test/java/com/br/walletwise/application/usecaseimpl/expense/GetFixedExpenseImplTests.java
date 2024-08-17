package com.br.walletwise.application.usecaseimpl.expense;

import com.br.walletwise.application.gateway.expense.GetFixedExpenseGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.expense.GetFixedExpenseImpl;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.usecase.expense.GetFixedExpense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetFixedExpenseImplTests {
    private GetFixedExpense getFixedExpense;
    private GetFixedExpenseGateway getFixedExpenseGateway;

    @BeforeEach
    void setUp() {
        this.getFixedExpenseGateway = mock(GetFixedExpenseGateway.class);
        this.getFixedExpense = new GetFixedExpenseImpl(getFixedExpenseGateway);
    }

    @Test
    @DisplayName("Should return optional of fixed expense ")
    void shouldReturnEmptyOptionalOfFixedEpense() {
        User user = MocksFactory.userFactory();
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory(user);

        when(this.getFixedExpenseGateway.get(fixedExpense.getId(), user.getId())).thenReturn(Optional.empty());

        Optional<FixedExpense> result = this.getFixedExpense.get(user.getId(), fixedExpense.getId());

        assertThat(result).isEmpty();
        verify(this.getFixedExpenseGateway, times(1)).get(fixedExpense.getId(), user.getId());
    }
}
