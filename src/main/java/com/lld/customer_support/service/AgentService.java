package com.lld.customer_support.service;

import lombok.AllArgsConstructor;
import com.lld.customer_support.enums.IssueType;
import com.lld.customer_support.model.Agent;
import com.lld.customer_support.model.Issue;
import com.lld.customer_support.repository.AgentRepository;

import java.util.*;

@AllArgsConstructor
public class AgentService {
    private final AgentRepository agentRepository;

    /**
     * Handles add agent for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void addAgent(String email, String name, List<IssueType> issueTypes) {
        String id = "A" + UUID.randomUUID().toString().substring(0, 6);
        Agent agent = new Agent(id, email, name, new HashSet<>(issueTypes));
        agentRepository.save(agent);
        System.out.println(">>> Agent " + id + " created");
    }

    /**
     * Handles view agents work history for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void viewAgentsWorkHistory() {
        for (Agent agent : agentRepository.getAll()) {
            System.out.println(agent.getId() + " -> " + agent.getHistory());
        }
    }
}
