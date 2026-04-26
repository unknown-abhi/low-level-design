package com.lld.splitwise.model;

import lombok.Getter;

import java.util.*;

@Getter
public class Group {
    private final String id;
    private final String name;
    private final List<User> members = new ArrayList<>();
    private final List<Expense> expenses = new ArrayList<>();
    private final Map<User, BalanceSheet> balanceSheets = new HashMap<>();

    /**
     * Creates a new Group instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Group(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Handles add member for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void addMember(User user) {
        members.add(user);
        balanceSheets.putIfAbsent(user, new BalanceSheet());
    }

    /**
     * Handles add expense for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    /**
     * Handles get balance sheet for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public BalanceSheet getBalanceSheet(User user) {
        return balanceSheets.get(user);
    }
}
