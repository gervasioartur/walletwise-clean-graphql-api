package com.br.walletwise.infra.mappers;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.infra.mocks.MocksFactory;
import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FixedExpenseMapperTests {
    @Autowired
    private FixedExpenseMapper  mapper;

    @Test
    @DisplayName("Should return Fixed Expense JPA entity")
    void shouldReturnFixedExpense() {
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory();

        FixedExpenseJpaEntity entity = this.mapper.map(fixedExpense);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(fixedExpense.getId());
        assertThat(entity.getUser().getId()).isEqualTo(fixedExpense.getUserId());
        assertThat(entity.getDescription()).isEqualTo(fixedExpense.getDescription());
        assertThat(entity.getDueDay()).isEqualTo(fixedExpense.getDueDay());
        assertThat(entity.getAmount()).isEqualTo(fixedExpense.getAmount());
        assertThat(entity.getCategory()).isEqualTo(fixedExpense.getCategory());
        assertThat(entity.getStarDate()).isEqualTo(fixedExpense.getStartDate());
        assertThat(entity.getEndDate()).isEqualTo(fixedExpense.getEndDate());
    }

}
