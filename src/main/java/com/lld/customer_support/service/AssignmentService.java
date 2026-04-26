package com.lld.customer_support.service;

import lombok.AllArgsConstructor;
import com.lld.customer_support.enums.IssueStatus;
import com.lld.customer_support.model.Agent;
import com.lld.customer_support.model.Issue;
import com.lld.customer_support.repository.AgentRepository;
import com.lld.customer_support.repository.IssueRepository;
import com.lld.customer_support.strategy.assignment.AssignmentStrategy;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AssignmentService {
    private final AgentRepository agentRepository;
    private final IssueRepository issueRepository;
    private final AssignmentStrategy strategy;

    /**
     * Handles assign issue for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void assignIssue(String issueId) {
        Issue issue = issueRepository.getById(issueId);
        if (issue == null)
            throw new IllegalArgumentException("Issue not found");

        List<Agent> agents = new ArrayList<>(agentRepository.getAll());
        Agent assigned = strategy.assign(agents, issue);

        if (assigned != null) {
            assigned.setAssignedIssueId(issue.getId());
            issue.setAssignedAgentId(assigned.getId());
            System.out.println(">>> Issue " + issueId + " assigned to agent " + assigned.getId());
        } else {
            for (Agent agent : agents) {
                if (agent.getExpertise().contains(issue.getIssueType())) {
                    agent.getWaitList().add(issue.getId());
                    issue.setStatus(IssueStatus.WAITING);
                    System.out.println(">>> Issue " + issueId + " added to waitlist of Agent " + agent.getId());
                    return;
                }
            }
            System.out.println(">>> No agent found with expertise for issue " + issueId);
        }
    }
}
