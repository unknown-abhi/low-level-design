package com.lld.customer_support.strategy.assignment;

import com.lld.customer_support.model.Agent;
import com.lld.customer_support.model.Issue;

import java.util.List;

public interface AssignmentStrategy {
    Agent assign(List<Agent> agents, Issue issue);
}
