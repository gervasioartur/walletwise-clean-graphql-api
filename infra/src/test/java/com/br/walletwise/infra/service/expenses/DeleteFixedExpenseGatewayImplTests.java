package com.br.walletwise.infra.service.expenses;

import com.br.walletwise.application.gateway.expense.DeleteFixedExpenseGateway;
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
class DeleteFixedExpenseGatewayImplTests {
    @Autowired
    private DeleteFixedExpenseGateway deleteFixedExpenseGateway;
    @MockBean
    private FixedExpenseJpaRepository repository;
    @MockBean
    private FixedExpenseMapper mapper;


    @Test
    @DisplayName("Should delete fixed expense")
    void shouldDeleteFixedExpense() {
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory();
        FixedExpenseJpaEntity entity = MocksFactory.fixedExpenseJpaEntityFactory(fixedExpense);
        when(this.mapper.map(fixedExpense)).thenReturn(entity);
        this.deleteFixedExpenseGateway.delete(fixedExpense);
        verify(this.mapper, times(1)).map(fixedExpense);
        verify(this.repository, times(1)).save(entity);
    }
}