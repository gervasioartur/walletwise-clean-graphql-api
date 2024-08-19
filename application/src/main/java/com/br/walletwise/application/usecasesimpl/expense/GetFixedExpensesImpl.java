package com.br.walletwise.application.usecasesimpl.expense;

import com.br.walletwise.application.gateway.expense.GetFixedExpensesGateway;
import com.br.walletwise.core.domain.entity.User;
import com.br.walletwise.core.domain.model.FixedExpenseModel;
import com.br.walletwise.usecase.cache.AddToCache;
import com.br.walletwise.usecase.cache.GetCache;
import com.br.walletwise.usecase.expense.GetFixedExpenses;
import com.br.walletwise.usecase.user.GetLoggedUser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class GetFixedExpensesImpl implements GetFixedExpenses {
    private final GetFixedExpensesGateway getFixedExpensesGateway;
    private final GetLoggedUser getLoggedUser;
    private final GetCache getCache;
    private final AddToCache<List<FixedExpenseModel>> addToCache;

    public GetFixedExpensesImpl(GetFixedExpensesGateway getFixedExpensesGateway,
                                GetLoggedUser getLoggedUser,
                                GetCache getCache,
                                AddToCache<List<FixedExpenseModel>> addToCache) {

        this.getFixedExpensesGateway = getFixedExpensesGateway;
        this.getLoggedUser = getLoggedUser;
        this.getCache = getCache;
        this.addToCache = addToCache;
    }

    @Override
    public List<FixedExpenseModel> get() {
        User user = this.getLoggedUser.get();
        List<FixedExpenseModel> fixedExpenseModels;
        List<LinkedHashMap<String, Object>> listOfMaps = this.getCache.get("fixedExpenses:" + user.getId());

        if (listOfMaps.size() > 0) {
            fixedExpenseModels = listOfMaps
                    .stream()
                    .map(this::toFixedExpenseModel)
                    .toList();
        } else {
            fixedExpenseModels = this.getFixedExpensesGateway.get(user.getId());
            this.addToCache.add("fixedExpenses:" + user.getId(), fixedExpenseModels);
        }

        return fixedExpenseModels;
    }

    private FixedExpenseModel toFixedExpenseModel(LinkedHashMap<String, Object> map) {
        int expenseCode = (Integer) map.get("expenseCode");
        String ownerFullName = (String) map.get("ownerFullName");
        String description = (String) map.get("description");
        int dueDay = (Integer) map.get("dueDay");
        String category = (String) map.get("category");
        BigDecimal amount = new BigDecimal(map.get("amount").toString());
        LocalDate startDate = parseDate((String) map.get("startDate"));
        LocalDate endDate = parseDate((String) map.get("endDate"));

        return new FixedExpenseModel(expenseCode,
                ownerFullName,
                description,
                dueDay,
                category,
                amount,
                this.toDate(startDate),
                this.toDate(endDate));
    }

    private Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateStr, formatter);
        return offsetDateTime.toLocalDate();
    }
}
