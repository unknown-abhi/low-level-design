package com.lld.customer_support.repository;

import com.lld.customer_support.model.Issue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IssueRepository {
    private final Map<String, Issue> issues = new HashMap<>();

    /**
     * Saves the save into the backing store.
     * It updates repository state so later operations can retrieve the same data.
     */
    public void save(Issue issue) {
        issues.put(issue.getId(), issue);
    }

    /**
     * Handles get by id for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Issue getById(String id) {
        return issues.get(id);
    }

    /**
     * Handles get all for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Collection<Issue> getAll() {
        return issues.values();
    }
}
