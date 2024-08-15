package com.br.walletwise.infra.service.expenses;

import com.br.walletwise.application.gateway.expense.AddFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.infra.mappers.FixedExpenseMapper;
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
class AddFixedExpenseGatewayImplTests {
    @Autowired
    private AddFixedExpenseGateway addFixedExpenseGateway;

    @MockBean
    private FixedExpenseMapper mapper;
    @MockBean
    private FixedExpenseJpaRepository fixedExpenseJpaRepository;

    @Test
    @DisplayName("Should add Fixed expense")
    void shouldAddFixedExpense() {
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory();
        FixedExpenseJpaEntity fixedExpenseEntity = MocksFactory.fixedExpenseJpaEntityFactory(fixedExpense);

        when(this.mapper.map(fixedExpense)).thenReturn(fixedExpenseEntity);

        this.addFixedExpenseGateway.add(fixedExpense);

        verify(this.mapper, times(1)).map(fixedExpense);
        verify(this.fixedExpenseJpaRepository, times(1)).save(fixedExpenseEntity);
    }
}