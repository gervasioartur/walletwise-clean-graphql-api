package com.br.walletwise.application.usecaseimpl.expense;

import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.expense.UpdateFixedExpenseImpl;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.usecase.expense.GetFixedExpense;
import com.br.walletwise.usecase.expense.UpdateFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdateFixedExpenseImplTests {
    private UpdateFixedExpense updateFixedExpense;
    private GetLoggedUser getLoggedUser;
    private GetFixedExpense getFixedExpense;

    @BeforeEach
    void setUp() {
        this.getLoggedUser = mock(GetLoggedUser.class);
        this.getFixedExpense = mock(GetFixedExpense.class);
        this.updateFixedExpense = new UpdateFixedExpenseImpl(getLoggedUser, getFixedExpense);
    }

    @Test
    @DisplayName("Should throw NotFoundException if user fixed expense does not exist")
    void shouldThrowNotFoundExceptionIfUserFixedExpenseDoesNotExist() {
        User user = MocksFactory.userFactory();
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory(user);

        when(this.getLoggedUser.get()).thenReturn(user);
        when(this.getFixedExpense.get(user.getId(),fixedExpense.getId())).thenReturn(Optional.empty());

        Throwable exception = catchThrowable(() -> this.updateFixedExpense.update(fixedExpense));

        assertThat(exception).isInstanceOf(NotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo("Fixed expense with code "
                + fixedExpense.getId() + " not found");
    }
}
