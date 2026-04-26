package com.lld.splitwise.repository;

import com.lld.splitwise.model.Group;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryGroupRepository implements GroupRepository {

    private final Map<String, Group> store = new HashMap<>();

    @Override
    /**
     * Handles find by id for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Optional<Group> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    /**
     * Saves the save into the backing store.
     * It updates repository state so later operations can retrieve the same data.
     */
    public void save(Group g) {
        store.put(g.getId(), g);
    }
}
