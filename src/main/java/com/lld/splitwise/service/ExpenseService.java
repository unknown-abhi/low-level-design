package com.lld.splitwise.service;

import lombok.AllArgsConstructor;
import com.lld.splitwise.enums.SplitType;
import com.lld.splitwise.factory.SplitStrategyFactory;
import com.lld.splitwise.model.*;
import com.lld.splitwise.strategy.SplitStrategy;

import java.util.*;

@AllArgsConstructor
public class ExpenseService {
    private final BalanceSheetService balanceSheetService;

    public void addExpense(Group group, String description, double amount, User paidBy,
            List<User> participants, SplitType splitType, Map<User, Double> metadata) {

        SplitStrategy strategy = SplitStrategyFactory.getStrategy(splitType);
        List<Split> splits = strategy.split(amount, participants, metadata);
        Expense expense = new Expense(description, amount, paidBy, splits, splitType);
        group.addExpense(expense);

        balanceSheetService.updateBalances(group, paidBy, splits);
    }
}