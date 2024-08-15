package com.br.walletwise.application.usecaseimpl.expense;

import com.br.walletwise.application.gateway.expense.GetFixedExpensesGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.expense.GetFixedExpensesImpl;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.usecase.expense.GetFixedExpenses;
import com.br.walletwise.usecase.user.GetLoggedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetFixedExpensesImplTests {
    private GetFixedExpenses GetFixedExpenses;
    private GetLoggedUser getLoggedUser;
    private GetFixedExpensesGateway getFixedExpensesGateway;

    @BeforeEach
    void setUp() {
       this.getLoggedUser = mock(GetLoggedUser.class);
       this.getFixedExpensesGateway = mock(GetFixedExpensesGateway.class);
       this.GetFixedExpenses = new GetFixedExpensesImpl(getLoggedUser, getFixedExpensesGateway);
    }

    @Test
    @DisplayName("Should return user fixed expenses")
    void shouldReturnUserFixedExpenses() {
        User user = MocksFactory.userFactory();
        List<FixedExpenseModel> fixedExpenseModelList =
                List.of(MocksFactory.fixedExpenseModelFactory(user), MocksFactory.fixedExpenseModelFactory(user));

        when(this.getLoggedUser.get()).thenReturn(user);
        when(this.getFixedExpensesGateway.get(user.getId())).thenReturn(fixedExpenseModelList);

        List<FixedExpenseModel> result = this.GetFixedExpenses.get();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.getFirst().getOwnerFullName()).isEqualTo(fixedExpenseModelList.getFirst().getOwnerFullName());
        assertThat(result.getFirst().getDescription()).isEqualTo(fixedExpenseModelList.getFirst().getDescription());
        assertThat(result.getFirst().getDueDay()).isEqualTo(fixedExpenseModelList.getFirst().getDueDay());
        assertThat(result.getFirst().getCategory()).isEqualTo(fixedExpenseModelList.getFirst().getCategory());
        assertThat(result.getFirst().getAmount()).isEqualTo(fixedExpenseModelList.getFirst().getAmount());
        assertThat(result.getFirst().getStartDate()).isEqualTo(fixedExpenseModelList.getFirst().getStartDate());
        assertThat(result.getFirst().getEndDate()).isEqualTo(fixedExpenseModelList.getFirst().getEndDate());

        assertThat(result.getLast().getOwnerFullName()).isEqualTo(fixedExpenseModelList.getLast().getOwnerFullName());
        assertThat(result.getLast().getDescription()).isEqualTo(fixedExpenseModelList.getLast().getDescription());
        assertThat(result.getLast().getDueDay()).isEqualTo(fixedExpenseModelList.getLast().getDueDay());
        assertThat(result.getLast().getCategory()).isEqualTo(fixedExpenseModelList.getLast().getCategory());
        assertThat(result.getLast().getAmount()).isEqualTo(fixedExpenseModelList.getLast().getAmount());
        assertThat(result.getLast().getStartDate()).isEqualTo(fixedExpenseModelList.getLast().getStartDate());
        assertThat(result.getLast().getEndDate()).isEqualTo(fixedExpenseModelList.getLast().getEndDate());
    }
}
