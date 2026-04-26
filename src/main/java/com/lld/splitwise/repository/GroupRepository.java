package com.lld.splitwise.repository;

import com.lld.splitwise.model.Group;

import java.util.Optional;

public interface GroupRepository {
    Optional<Group> findById(String id);

    void save(Group group);
}
