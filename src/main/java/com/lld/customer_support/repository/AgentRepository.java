package com.lld.customer_support.repository;

import com.lld.customer_support.model.Agent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AgentRepository {
    private final Map<String, Agent> agents = new HashMap<>();

    /**
     * Saves the save into the backing store.
     * It updates repository state so later operations can retrieve the same data.
     */
    public void save(Agent agent) {
        agents.put(agent.getId(), agent);
    }

    /**
     * Handles get by id for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Agent getById(String id) {
        return agents.get(id);
    }

    /**
     * Handles get all for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Collection<Agent> getAll() {
        return agents.values();
    }
}
