package com.lld.car_rental.model;

import lombok.Getter;
import lombok.Setter;
import com.lld.car_rental.enums.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Branch {
    private String id;
    private String city;

    private Map<VehicleType, List<Vehicle>> vehicles = new HashMap<>();

    /**
     * Creates a new Branch instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Branch(String id, String city) {
        this.id = id;
        this.city = city;
    }

    /**
     * Handles get vehicles by type for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public List<Vehicle> getVehiclesByType(VehicleType type) {
        return vehicles.getOrDefault(type, new ArrayList<>());
    }

    /**
     * Handles add vehicle for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void addVehicle(Vehicle vehicle) {
        vehicles.computeIfAbsent(vehicle.getType(), k -> new ArrayList<>()).add(vehicle);
    }

    /**
     * Handles remove vehicle for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void removeVehicle(Vehicle vehicle) {
        List<Vehicle> list = vehicles.get(vehicle.getType());
        if (list != null)
            list.remove(vehicle);
    }
}
