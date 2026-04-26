package com.lld.snakes_and_ladder.model;

import lombok.Getter;
import com.lld.snakes_and_ladder.enums.ObstacleType;

import java.util.Queue;

@Getter
public class Board {
    private final int size;
    private final int sideLength;
    private final Cell[][] grid;

    /**
     * Creates a new Board instance.
     * This constructor wires the initial dependencies and starting state for the object.
     */
    public Board(int size) {
        this.size = size;
        this.sideLength = (int) Math.sqrt(size);
        this.grid = new Cell[sideLength][sideLength];

        int position = 1;
        boolean leftToRight = true;

        for (int i = sideLength - 1; i >= 0; i--) {
            if (leftToRight) {
                for (int j = 0; j < sideLength; j++) {
                    grid[i][j] = new Cell(position++);
                }
            } else {
                for (int j = sideLength - 1; j >= 0; j--) {
                    grid[i][j] = new Cell(position++);
                }
            }
            leftToRight = !leftToRight;
        }
    }

    /**
     * Handles get row for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    private int getRow(int position) {
        int row = (position - 1) / sideLength;
        return sideLength - 1 - row;
    }

    /**
     * Handles get col for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    private int getCol(int position) {
        int row = getRow(position);
        int col = (position - 1) % sideLength;
        return (row % 2 == 0) ? sideLength - 1 - col : col;
    }

    /**
     * Handles get cell for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    private Cell getCell(int position) {
        return grid[getRow(position)][getCol(position)];
    }

    /**
     * Handles add obstacle for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public boolean addObstacle(Obstacle obstacle) {
        Cell srcCell = getCell(obstacle.getSrc());
        Cell destCell = getCell(obstacle.getDest());

        if (srcCell.hasObstacle() || destCell.hasObstacle()) {
            return false; // Prevents overlapping obstacles
        }

        srcCell.setObstacle(obstacle);
        return true;
    }

    /**
     * Handles get new position for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public int getNewPosition(Player player, int offset) {
        int newPosition = player.getPosition() + offset;

        if (newPosition > size) {
            System.out.println("You are going out of the board! Better luck next time!");
            return player.getPosition();
        }

        Cell cell = grid[getRow(newPosition)][getCol(newPosition)];
        int finalPosition = cell.getFinalPosition();

        if (finalPosition < newPosition) {
            System.out.println("Oops! Snake has bitten " + player.getName());
        } else if (finalPosition > newPosition) {
            System.out.println("Congratulations! " + player.getName() + " moved up through a ladder");
        } else {
            System.out.println(player.getName() + " moved from " + player.getPosition() + " to " + newPosition);
        }

        return finalPosition;
    }

    /**
     * Handles print board for this class.
     * It applies the class-specific rules and updates any related state or result.
     */
    public void printBoard(Queue<Player> players) {
        System.out.println("\nCurrent Board State:");

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                int position = grid[i][j].getPosition();
                String cellContent = String.valueOf(position);

                if (grid[i][j].hasObstacle()) {
                    Obstacle obstacle = grid[i][j].getObstacle();
                    cellContent = (obstacle.getObstacleType() == ObstacleType.SNAKE) ? "🐍" + obstacle.getDest()
                            : "🪜" + obstacle.getDest();
                }

                for (Player player : players) {
                    if (player.getPosition() == position) {
                        // cellContent = player.getName().substring(0, 1);
                        cellContent = player.getName();
                    }
                }

                System.out.printf("%-8s", cellContent);
            }
            System.out.println();
        }
        System.out.println();
    }
}
