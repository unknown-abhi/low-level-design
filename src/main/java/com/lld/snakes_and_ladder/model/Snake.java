package com.lld.snakes_and_ladder.model;

import com.lld.snakes_and_ladder.enums.ObstacleType;

public class Snake extends Obstacle {
    /**
     * Creates a new Snake instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Snake(int head, int tail) {
        super(head, tail);
    }

    @Override
    /**
     * Handles get obstacle type for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public ObstacleType getObstacleType() {
        return ObstacleType.SNAKE;
    }
}
