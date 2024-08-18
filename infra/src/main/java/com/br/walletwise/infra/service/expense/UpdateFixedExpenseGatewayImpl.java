package com.br.walletwise.infra.service.expense;

import com.br.walletwise.application.gateway.expense.UpdateFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.infra.mappers.FixedExpenseMapper;
import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import com.br.walletwise.infra.persistence.repository.FixedExpenseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateFixedExpenseGatewayImpl implements UpdateFixedExpenseGateway {
    private final FixedExpenseMapper mapper;
    private final FixedExpenseJpaRepository repository;

    @Override
    public void updated(FixedExpense fixedExpense) {
        FixedExpenseJpaEntity entity = this.mapper.map(fixedExpense);
        this.repository.save(entity);
    }
}
