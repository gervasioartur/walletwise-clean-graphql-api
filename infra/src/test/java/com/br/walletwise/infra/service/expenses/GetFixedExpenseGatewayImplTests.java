package com.br.walletwise.infra.service.expenses;

import com.br.walletwise.application.gateway.expense.GetFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.infra.mappers.FixedExpenseMapper;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import com.br.walletwise.infra.persistence.repository.FixedExpenseJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GetFixedExpenseGatewayImplTests {
    @Autowired
    private GetFixedExpenseGateway getFixedExpenseGateway;
    @MockBean
    private FixedExpenseMapper mapper;
    @MockBean
    private FixedExpenseJpaRepository repository;

    @Test
    @DisplayName("Should return optional of fixed expense")
    public void shouldReturnOptionalOfFixedExpense() {
        User user  = MocksFactory.userFactory();
        FixedExpenseJpaEntity entity = MocksFactory.fixedExpenseJpaEntityFactory(user);
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory(entity);

        when(this.repository.findByIdAndUserId(entity.getId(),entity.getUser().getId())).thenReturn(Optional.of(entity));
        when(this.mapper.mapToDomainObj(entity)).thenReturn(fixedExpense);

        Optional<FixedExpense> result = this.getFixedExpenseGateway.get(entity.getId(), entity.getUser().getId());

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(fixedExpense.getId());
        verify(this.mapper, times(1)).mapToDomainObj(entity);
        verify(this.repository, times(1)).findByIdAndUserId(entity.getId(),entity.getUser().getId());
    }
}