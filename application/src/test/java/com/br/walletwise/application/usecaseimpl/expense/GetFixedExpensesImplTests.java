package com.br.walletwise.application.usecaseimpl.expense;

import com.br.walletwise.application.gateway.expense.GetFixedExpensesGateway;
import com.br.walletwise.application.mocks.MocksFactory;
import com.br.walletwise.application.usecasesimpl.expense.GetFixedExpensesImpl;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.usecase.cache.AddToCache;
import com.br.walletwise.usecase.cache.GetCache;
import com.br.walletwise.usecase.expense.GetFixedExpenses;
import com.br.walletwise.usecase.user.GetLoggedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetFixedExpensesImplTests {
    private GetFixedExpenses GetFixedExpenses;
    private GetFixedExpensesGateway getFixedExpensesGateway;
    private GetLoggedUser getLoggedUser;
    private GetCache<FixedExpenseModel> getCache;
    private AddToCache addToCache;

    @BeforeEach
    void setUp() {
        this.getLoggedUser = mock(GetLoggedUser.class);
        this.getFixedExpensesGateway = mock(GetFixedExpensesGateway.class);
        this.getCache = mock(GetCache.class);
        this.addToCache = mock(AddToCache.class);
        this.GetFixedExpenses = new GetFixedExpensesImpl(getFixedExpensesGateway, getLoggedUser, getCache, addToCache);
    }

    @Test
    @DisplayName("Should return cached Fixed expense model")
    void shouldReturnCachedFixedExpenseModel() {
        User user = MocksFactory.userFactory();
        List<FixedExpenseModel> fixedExpenseModelList =
                List.of(MocksFactory.fixedExpenseModelFactory(user), MocksFactory.fixedExpenseModelFactory(user));

        when(this.getLoggedUser.get()).thenReturn(user);
        when(this.getCache.get("fixedExpenses:" + user.getId())).thenReturn(fixedExpenseModelList);
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

        verify(this.getFixedExpensesGateway, times(0)).get(user.getId());
        verify(this.addToCache, times(0)).add("fixedExpenses:" + user.getId(), fixedExpenseModelList);
    }

    @Test
    @DisplayName("Should return Fixed expense model form repository")
    void shouldReturnFixedExpenseModelFromRepository() {
        User user = MocksFactory.userFactory();
        List<FixedExpenseModel> fixedExpenseModelList =
                List.of(MocksFactory.fixedExpenseModelFactory(user), MocksFactory.fixedExpenseModelFactory(user));

        when(this.getLoggedUser.get()).thenReturn(user);
        when(this.getFixedExpensesGateway.get(user.getId())).thenReturn(fixedExpenseModelList);
        when(this.getCache.get(user.getId().toString())).thenReturn(List.of());

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

        verify(this.getCache, times(1)).get("fixedExpenses:" + user.getId());
        verify(this.getFixedExpensesGateway, times(1)).get(user.getId());
        verify(this.addToCache, times(1)).add("fixedExpenses:" + user.getId(), fixedExpenseModelList);
    }
}
