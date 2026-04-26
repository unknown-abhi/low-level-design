package com.lld.snakes_and_ladder.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Cell {
    private final int position;
    private Obstacle obstacle;

    /**
     * Handles has obstacle for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public boolean hasObstacle() {
        return obstacle != null;
    }

    /**
     * Handles get final position for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public int getFinalPosition() {
        return hasObstacle() ? obstacle.movePlayer() : position;
    }
}
