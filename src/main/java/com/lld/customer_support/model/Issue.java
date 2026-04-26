package com.lld.customer_support.model;

import lombok.Getter;
import lombok.Setter;
import com.lld.customer_support.enums.IssueStatus;
import com.lld.customer_support.enums.IssueType;

import java.util.UUID;

@Getter
@Setter
public class Issue {
    private final String id;
    private final String transactionId;
    private final IssueType issueType;
    private final String subject;
    private final String description;
    private final String email;
    private IssueStatus status;
    private String resolution;
    private String assignedAgentId;

    /**
     * Creates a new Issue instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Issue(String transactionId, IssueType issueType, String subject, String description, String email) {
        this.id = "I" + UUID.randomUUID().toString().substring(0, 6);
        this.transactionId = transactionId;
        this.issueType = issueType;
        this.subject = subject;
        this.description = description;
        this.email = email;
        this.status = IssueStatus.OPEN;
    }

    @Override
    /**
     * Handles to string for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public String toString() {
        return id + " {\"" + transactionId + "\", \"" + issueType + "\", \"" + subject + "\", \"" + description
                + "\", \"" + email + "\", \"" + status + "\"}";
    }
}
