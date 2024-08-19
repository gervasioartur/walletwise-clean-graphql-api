package com.br.walletwise.infra.service.expense;

import com.br.walletwise.application.gateway.expense.AddFixedExpenseGateway;
import com.br.walletwise.core.domain.entity.FixedExpense;
import com.br.walletwise.infra.mapper.FixedExpenseMapper;
import com.br.walletwise.infra.persistence.entity.FixedExpenseJpaEntity;
import com.br.walletwise.infra.persistence.repository.FixedExpenseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddFixedExpenseGatewayImpl implements AddFixedExpenseGateway {
    private final FixedExpenseJpaRepository repository;
    private final FixedExpenseMapper mapper;

    @Override
    public void add(FixedExpense fixedExpense) {
        FixedExpenseJpaEntity entity = this.mapper.map(fixedExpense);
        this.repository.save(entity);
    }
}