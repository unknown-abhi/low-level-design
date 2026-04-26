package com.lld.splitwise;

import com.lld.splitwise.enums.SplitType;
import com.lld.splitwise.model.User;
import com.lld.splitwise.repository.InMemoryGroupRepository;
import com.lld.splitwise.service.*;

import java.util.*;

public class Main {
    /**
     * Creates a new Main instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public static void main(String[] args) {

        // users
        User shubh = new User("u1", "Shubh");
        User bob = new User("u2", "Bob");
        User tom = new User("u3", "Tom");
        User jake = new User("u4", "Jake");

        InMemoryGroupRepository repo = new InMemoryGroupRepository();
        BalanceSheetService balanceSheetService = new BalanceSheetService();
        ExpenseService expenseService = new ExpenseService(balanceSheetService);
        DebtSimplificationService simplificationService = new DebtSimplificationService();

        GroupService groupService = new GroupService(repo, expenseService, simplificationService);

        /* ---------- create groups ---------- */
        String goaGroupId = groupService.createGroup("Goa Trip", List.of(shubh, bob, tom));
        String miscGroup = groupService.createGroup("Non-Group Expenses", List.of(shubh, bob, tom, jake));

        /* ---------- add expenses ---------- */
        groupService.addExpense(goaGroupId,
                "Lunch Day-1", 100, shubh,
                List.of(shubh, bob), SplitType.EQUAL, null);

        groupService.addExpense(goaGroupId,
                "Lunch Day-2", 100, bob,
                List.of(bob, tom), SplitType.EQUAL, null);

        /* ---------- simplify & print ---------- */
        groupService.simplifyDebts(goaGroupId);
        groupService.printBalances(goaGroupId);
    }
}
