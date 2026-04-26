package com.lld.snakes_and_ladder.model;

import java.util.Random;

public class Dice {
    private final int noOfDices;
    private final Random random = new Random();

    /**
     * Creates a new Dice instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Dice(int noOfDices) {
        this.noOfDices = noOfDices;
    }

    /**
     * Handles roll for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public int roll() {
        int sum = 0;
        for (int i = 0; i < noOfDices; i++) {
            sum += random.nextInt(6) + 1;
        }
        return sum;
    }
}
