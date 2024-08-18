package com.br.walletwise.infra.service.expenses;

import com.br.walletwise.application.gateway.expense.GetFixedExpensesGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.infra.mappers.FixedExpenseMapper;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import com.br.walletwise.infra.persistence.repository.FixedExpenseJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class GetFixedExpensesGatewayImplTests {
    @Autowired
    private GetFixedExpensesGateway getFixedExpensesGateway;
    @MockBean
    private FixedExpenseJpaRepository fixedExpenseJpaRepository;
    @MockBean
    private FixedExpenseMapper mapper;

    @Test
    @DisplayName("Should return list of fixed expenses model")
    void shouldListOfFixedExpensesModel() {
        User user = MocksFactory.userFactory();

        List<FixedExpenseJpaEntity> expenseJpaEntities =
                List.of(MocksFactory.fixedExpenseJpaEntityFactory(user)
                        , MocksFactory.fixedExpenseJpaEntityFactory(user));

        List<FixedExpenseModel> fixedExpenseModelList =
                List.of(MocksFactory.fixedExpenseModelFactory(expenseJpaEntities.getFirst())
                        , MocksFactory.fixedExpenseModelFactory(expenseJpaEntities.getLast()));

        when(this.fixedExpenseJpaRepository.findByUserId(user.getId())).thenReturn(expenseJpaEntities);
        when(this.mapper.map(expenseJpaEntities.getFirst())).thenReturn(fixedExpenseModelList.getFirst());
        when(this.mapper.map(expenseJpaEntities.getLast())).thenReturn(fixedExpenseModelList.getLast());

        List<FixedExpenseModel> result = this.getFixedExpensesGateway.get(user.getId());


        assertThat(result.getFirst().getOwnerFullName()).isEqualTo(fixedExpenseModelList.getFirst().getOwnerFullName());
        assertThat(result.getLast().getOwnerFullName()).isEqualTo(fixedExpenseModelList.getLast().getOwnerFullName());


        verify(this.fixedExpenseJpaRepository, times(1)).findByUserId(user.getId());
        verify(this.mapper, times(1)).map(expenseJpaEntities.getFirst());
        verify(this.mapper, times(1)).map(expenseJpaEntities.getFirst());
    }
}
