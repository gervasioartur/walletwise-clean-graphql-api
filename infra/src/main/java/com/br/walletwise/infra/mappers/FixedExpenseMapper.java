package com.br.walletwise.infra.mappers;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.infra.entrypoint.dto.AddFixedExpenseRequest;
import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class FixedExpenseMapper {

    public FixedExpenseJpaEntity map(FixedExpense fixedExpense) {
        return  FixedExpenseJpaEntity
                .builder()
                .id(fixedExpense.getId())
                .user(UserJpaEntity.builder().id(fixedExpense.getUserId()).build())
                .description(fixedExpense.getDescription())
                .dueDay(fixedExpense.getDueDay())
                .amount(fixedExpense.getAmount())
                .category(fixedExpense.getCategory())
                .starDate(fixedExpense.getStartDate())
                .endDate(fixedExpense.getEndDate())
                .active(fixedExpense.isActive())
                .build();
    }

    public FixedExpense map(AddFixedExpenseRequest request) {
        return new FixedExpense(
                request.description(),
                request.dueDay(),
                request.category(),
                request.amount(),
                request.starDate(),
                request.endDate());
    }
}