package com.lld.customer_support.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import com.lld.customer_support.enums.IssueStatus;
import com.lld.customer_support.enums.IssueType;

import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
public class Agent {
    private final String id;
    private final String email;
    private final String name;
    private final Set<IssueType> expertise;

    private String assignedIssueId;
    private final Queue<String> waitList = new LinkedList<>();
    private final List<String> history = new ArrayList<>();

    /**
     * Handles is available for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public boolean isAvailable() {
        return assignedIssueId == null;
    }
}
