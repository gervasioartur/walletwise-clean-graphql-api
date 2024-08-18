package com.br.walletwise.infra.mappers;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.infra.entrypoint.dto.AddFixedExpenseRequest;
import com.br.walletwise.infra.entrypoint.dto.UpdateFixedExpenseRequest;
import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class FixedExpenseMapper {

    public FixedExpenseJpaEntity map(FixedExpense fixedExpense) {
        return FixedExpenseJpaEntity
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
                .createdAt(fixedExpense.getCreatedAt())
                .updatedAt(fixedExpense.getUpdatedAt())
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

    public FixedExpenseModel map(UpdateFixedExpenseRequest request) {
        return new FixedExpenseModel(
                request.description(),
                request.dueDay(),
                request.category(),
                request.amount(),
                request.starDate(),
                request.endDate());
    }

    public FixedExpenseModel map(FixedExpenseJpaEntity entity) {
        return new FixedExpenseModel(
                entity.getId(),
                entity.getUser().getFirstname() + " " + entity.getUser().getLastname(),
                entity.getDescription(),
                entity.getDueDay(),
                entity.getCategory(),
                entity.getAmount(),
                entity.getStarDate(),
                entity.getEndDate()
        );
    }

    public FixedExpense mapToDomainObj(FixedExpenseJpaEntity entity) {
        FixedExpense expense = new FixedExpense(
                entity.getId(),
                entity.getUser().getId(),
                entity.getDescription(),
                entity.getDueDay(),
                entity.getCategory(),
                entity.getAmount(),
                entity.getStarDate(),
                entity.getEndDate(),
                entity.isActive()
        );

        expense.setCreatedAt(entity.getCreatedAt());
        expense.setUpdatedAt(entity.getUpdatedAt());
        return expense;
    }
}