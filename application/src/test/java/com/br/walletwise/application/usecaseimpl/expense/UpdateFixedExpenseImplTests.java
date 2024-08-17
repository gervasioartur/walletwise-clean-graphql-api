package com.br.walletwise.application.usecaseimpl.expense;

import com.br.walletwise.application.gateway.expense.UpdateFixedExpenseGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.expense.UpdateFixedExpenseImpl;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.enums.CategoryEnum;
import com.br.walletwise.core.exception.NotFoundException;
import com.br.walletwise.usecase.cache.InvalidateCache;
import com.br.walletwise.usecase.expense.GetFixedExpense;
import com.br.walletwise.usecase.expense.UpdateFixedExpense;
import com.br.walletwise.usecase.user.GetLoggedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

class UpdateFixedExpenseImplTests {
    private UpdateFixedExpense updateFixedExpense;
    private GetLoggedUser getLoggedUser;
    private GetFixedExpense getFixedExpense;
    private UpdateFixedExpenseGateway updateFixedExpenseGateway;
    private InvalidateCache invalidateCache;

    @BeforeEach
    void setUp() {
        this.getLoggedUser = mock(GetLoggedUser.class);
        this.getFixedExpense = mock(GetFixedExpense.class);
        this.updateFixedExpenseGateway = mock(UpdateFixedExpenseGateway.class);
        this.invalidateCache = mock(InvalidateCache.class);
        this.updateFixedExpense = new UpdateFixedExpenseImpl(getLoggedUser,
                getFixedExpense,
                updateFixedExpenseGateway, invalidateCache);
    }

    @Test
    @DisplayName("Should throw NotFoundException if user fixed expense does not exist")
    void shouldThrowNotFoundExceptionIfUserFixedExpenseDoesNotExist() {
        User user = MocksFactory.userFactory();
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory(user);

        when(this.getLoggedUser.get()).thenReturn(user);
        when(this.getFixedExpense.get(user.getId(), fixedExpense.getId())).thenReturn(Optional.empty());

        Throwable exception = catchThrowable(() -> this.updateFixedExpense.update(fixedExpense));

        assertThat(exception).isInstanceOf(NotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo("Fixed expense with code "
                + fixedExpense.getId() + " not found");
        verify(this.getLoggedUser, times(1)).get();
        verify(this.getFixedExpense, times(1)).get(user.getId(), fixedExpense.getId());
    }

    @Test
    @DisplayName("Should update fixed expense")
    void shouldUpdateFixedExpense() {
        User user = MocksFactory.userFactory();
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory(user);

        when(this.getLoggedUser.get()).thenReturn(user);
        when(this.getFixedExpense.get(user.getId(), fixedExpense.getId())).thenReturn(Optional.of(fixedExpense));

        fixedExpense.setDescription(MocksFactory.faker.lorem().paragraph());
        fixedExpense.setDueDay(15);
        fixedExpense.setCategory(CategoryEnum.RENT.getValue());
        fixedExpense.setAmount(new BigDecimal(400));
        fixedExpense.setStartDate(new Date());
        fixedExpense.setEndDate(Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()));

        doNothing().when(this.updateFixedExpenseGateway).updated(fixedExpense);
        doNothing().when(this.invalidateCache).delete("fixedExpenses:"+fixedExpense.getId());

        this.updateFixedExpense.update(fixedExpense);

        verify(this.getLoggedUser, times(1)).get();
        verify(this.getFixedExpense, times(1)).get(user.getId(), fixedExpense.getId());
        verify(this.updateFixedExpenseGateway, times(1)).updated(fixedExpense);
        verify(this.invalidateCache, times(1)).delete("fixedExpenses:"+user.getId());
    }
}
