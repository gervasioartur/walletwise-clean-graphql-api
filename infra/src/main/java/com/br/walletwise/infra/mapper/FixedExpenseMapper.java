package com.br.walletwise.infra.mapper;

import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.infra.entrypoint.dto.AddFixedExpenseInput;
import com.br.walletwise.infra.entrypoint.dto.FixedExpenseOutput;
import com.br.walletwise.infra.entrypoint.dto.UpdateFixedExpenseInput;
import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import com.br.walletwise.infra.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

    public FixedExpense map(AddFixedExpenseInput request) {
        LocalDateTime startDateTime = request.starDate().atStartOfDay();
        Instant startInstant = startDateTime.atZone(ZoneId.systemDefault()).toInstant();

        LocalDateTime endDateTime = request.endDate().atStartOfDay();
        Instant endInstant = endDateTime.atZone(ZoneId.systemDefault()).toInstant();

        return new FixedExpense(
                request.description(),
                request.dueDay(),
                request.category(),
                request.amount(),
                Date.from(startInstant),
                Date.from(endInstant));
    }

    public FixedExpenseOutput map(FixedExpenseModel request) {
        LocalDate startDate = request.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = request.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new FixedExpenseOutput(
                (int) request.getExpenseCode(),
                request.getOwnerFullName(),
                request.getDescription(),
                request.getDueDay(),
                request.getCategory(),
                request.getAmount(),
                startDate,
                endDate);
    }

    public FixedExpenseModel map(UpdateFixedExpenseInput request) {
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