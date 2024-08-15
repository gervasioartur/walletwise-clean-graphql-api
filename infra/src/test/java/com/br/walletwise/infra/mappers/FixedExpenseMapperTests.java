package com.br.walletwise.infra.mappers;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.infra.entrypoint.dto.AddFixedExpenseRequest;
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
    private FixedExpenseMapper mapper;

    @Test
    @DisplayName("Should return Fixed Expense JPA entity")
    void shouldReturnFixedExpense() {
        FixedExpense fixedExpense = MocksFactory.fixedExpenseFactory();

        FixedExpenseJpaEntity result = this.mapper.map(fixedExpense);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(fixedExpense.getId());
        assertThat(result.getUser().getId()).isEqualTo(fixedExpense.getUserId());
        assertThat(result.getDescription()).isEqualTo(fixedExpense.getDescription());
        assertThat(result.getDueDay()).isEqualTo(fixedExpense.getDueDay());
        assertThat(result.getAmount()).isEqualTo(fixedExpense.getAmount());
        assertThat(result.getCategory()).isEqualTo(fixedExpense.getCategory());
        assertThat(result.getStarDate()).isEqualTo(fixedExpense.getStartDate());
        assertThat(result.getEndDate()).isEqualTo(fixedExpense.getEndDate());
    }

    @Test
    @DisplayName("Should return Fixed Expense")
    void shouldReturnFixedExpenseFromJpaEntity() {
        AddFixedExpenseRequest request = MocksFactory.addFixedExpenseRequestFactory();

        FixedExpense result = this.mapper.map(request);

        assertThat(result).isNotNull();
        assertThat(result.getDescription()).isEqualTo(request.description());
        assertThat(result.getDueDay()).isEqualTo(request.dueDay());
        assertThat(result.getAmount()).isEqualTo(request.amount());
        assertThat(result.getCategory()).isEqualTo(request.category());
        assertThat(result.getStartDate()).isEqualTo(request.starDate());
        assertThat(result.getEndDate()).isEqualTo(request.endDate());
    }

    @Test
    @DisplayName("Should return Fixed eExpense Model")
    void shouldReturnFixedExpenseModel() {
        User user = MocksFactory.userFactory();
        FixedExpenseJpaEntity entity =  MocksFactory.fixedExpenseJpaEntityFactory(user);

        FixedExpenseModel result = this.mapper.map(entity);

        assertThat(result).isNotNull();
        assertThat(result.getOwnerFullName())
                .isEqualTo(entity.getUser().getFirstname() + " " + entity.getUser().getLastname());
        assertThat(result.getDescription()).isEqualTo(entity.getDescription());
        assertThat(result.getDueDay()).isEqualTo(entity.getDueDay());
        assertThat(result.getAmount()).isEqualTo(entity.getAmount());
        assertThat(result.getCategory()).isEqualTo(entity.getCategory());
        assertThat(result.getStartDate()).isEqualTo(entity.getStarDate());
        assertThat(result.getEndDate()).isEqualTo(entity.getEndDate());
    }
}