package com.lld.snakes_and_ladder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.lld.snakes_and_ladder.enums.ObstacleType;

@Getter
public abstract class Obstacle {
    protected int src;
    protected int dest;

    /**
     * Creates a new Obstacle instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Obstacle(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }

    /**
     * Handles move player for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public int movePlayer() {
        return dest;
    }

    /**
     * Handles get obstacle type for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public abstract ObstacleType getObstacleType();
}
