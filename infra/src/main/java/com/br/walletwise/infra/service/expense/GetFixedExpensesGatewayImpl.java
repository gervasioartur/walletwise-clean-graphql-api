package com.br.walletwise.infra.service.expense;

import com.br.walletwise.application.gateway.expense.GetFixedExpensesGateway;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.infra.mappers.FixedExpenseMapper;
import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import com.br.walletwise.infra.persistence.repository.FixedExpenseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
@Component
@RequiredArgsConstructor
public class GetFixedExpensesGatewayImpl implements GetFixedExpensesGateway {
    private final FixedExpenseJpaRepository repository;
    private final FixedExpenseMapper mapper;

    @Override
    public List<FixedExpenseModel> get(UUID userId) {
        List<FixedExpenseJpaEntity> entities = this.repository.findByUserId(userId);
        return entities.stream()
                .map(this.mapper::map)
                .toList();
    }
}