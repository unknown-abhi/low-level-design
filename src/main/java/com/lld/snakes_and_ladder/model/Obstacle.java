package com.lld.snakes_and_ladder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.lld.snakes_and_ladder.enums.ObstacleType;

@Getter
public abstract class Obstacle {
    protected int src;
    protected int dest;

    public Obstacle(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }

    public int movePlayer() {
        return dest;
    }

    public abstract ObstacleType getObstacleType();
}