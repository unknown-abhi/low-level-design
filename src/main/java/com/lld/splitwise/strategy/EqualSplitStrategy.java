package com.lld.splitwise.strategy;

import com.lld.splitwise.model.Split;
import com.lld.splitwise.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy {
    public List<Split> split(double totalAmount, List<User> participants, Map<User, Double> metadata) {
        double share = totalAmount / participants.size();
        List<Split> splits = new ArrayList<>();
        for (User user : participants) {
            splits.add(new Split(user, share));
        }
        return splits;
    }
}