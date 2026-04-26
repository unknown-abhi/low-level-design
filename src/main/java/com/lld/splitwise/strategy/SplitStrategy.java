package com.lld.splitwise.strategy;

import com.lld.splitwise.model.Split;
import com.lld.splitwise.model.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    List<Split> split(double totalAmount, List<User> participants, Map<User, Double> metadata);
}
