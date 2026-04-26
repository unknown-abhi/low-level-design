package com.lld.snakes_and_ladder.model;

import com.lld.snakes_and_ladder.enums.ObstacleType;

public class Ladder extends Obstacle {
    /**
     * Creates a new Ladder instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Ladder(int top, int bottom) {
        super(bottom, top);
    }

    @Override
    /**
     * Handles get obstacle type for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public ObstacleType getObstacleType() {
        return ObstacleType.LADDER;
    }
}
