package com.lld.splitwise.service;

import com.lld.splitwise.model.Group;
import com.lld.splitwise.model.Split;
import com.lld.splitwise.model.User;

import java.util.List;

public class BalanceSheetService {

    /**
     * Handles update balances for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void updateBalances(Group group, User paidBy, List<Split> splits) {
        double totalAmount = splits.stream().mapToDouble(Split::getAmount).sum();
        group.getBalanceSheet(paidBy).addTotalPaid(totalAmount);

        for (Split split : splits) {
            User user = split.getUser();
            double amt = split.getAmount();
            group.getBalanceSheet(user).addTotalExpense(amt);
            if (!user.equals(paidBy)) {
                group.getBalanceSheet(user).addBalance(paidBy, -amt);
                group.getBalanceSheet(paidBy).addBalance(user, amt);
            }
        }
    }
}
