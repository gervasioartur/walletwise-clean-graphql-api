package com.br.walletwise.infra.service.expenses;

import com.br.walletwise.application.gateway.expense.UpdateFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.infra.mapper.FixedExpenseMapper;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import com.br.walletwise.infra.persistence.repository.FixedExpenseJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest
class UpdateFixedExpenseGatewayImplTests {
    @Autowired
    private UpdateFixedExpenseGateway updateFixedExpenseGateway;
    @MockBean
    private FixedExpenseMapper mapper;
    @MockBean
    private FixedExpenseJpaRepository resRepository;

    @Test
    @DisplayName("Should update user fixed expense")
    void shouldUpdateUserFixedExpense() {
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory();
        FixedExpenseJpaEntity entity = MocksFactory.fixedExpenseJpaEntityFactory(fixedExpense);

        when(this.mapper.map(fixedExpense)).thenReturn(entity);
        when(this.resRepository.save(entity)).thenReturn(entity);

        this.updateFixedExpenseGateway.updated(fixedExpense);

        verify(this.mapper, times(1)).map(fixedExpense);
        verify(this.resRepository, times(1)).save(entity);
    }
}

