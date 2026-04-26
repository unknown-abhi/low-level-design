package com.lld.car_rental.repository;

import com.lld.car_rental.model.Branch;

import java.util.HashMap;
import java.util.Map;

public class BranchRepository {
    private final Map<String, Branch> branchMap = new HashMap<>();

    /**
     * Handles add branch for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void addBranch(Branch branch) {
        branchMap.put(branch.getId(), branch);
    }

    /**
     * Handles get branch for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public Branch getBranch(String id) {
        return branchMap.get(id);
    }
}
