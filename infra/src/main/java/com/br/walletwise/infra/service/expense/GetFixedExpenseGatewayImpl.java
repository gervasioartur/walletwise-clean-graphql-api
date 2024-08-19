package com.br.walletwise.infra.service.expense;

import com.br.walletwise.application.gateway.expense.GetFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.infra.mapper.FixedExpenseMapper;
import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import com.br.walletwise.infra.persistence.repository.FixedExpenseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetFixedExpenseGatewayImpl implements GetFixedExpenseGateway {
    private final FixedExpenseJpaRepository repository;
    private final FixedExpenseMapper mapper;

    @Override
    public Optional<FixedExpense> get(long expenseCode, UUID userId) {
        Optional<FixedExpenseJpaEntity> entity = this.repository.findByIdAndUserId(expenseCode, userId);
        return entity.map(this.mapper::mapToDomainObj);
    }

    @Override
    public Optional<FixedExpenseModel> getModel(long expenseCode, UUID userId) {
        Optional<FixedExpenseJpaEntity> entity = this.repository.findByIdAndUserId(expenseCode, userId);
        return entity.map(this.mapper::map);
    }
}
