# Snakes and Ladders Game

A complete implementation of the classic Snakes and Ladders board game with multi-player support, game state management, and win condition handling.

## 📋 Overview

This module implements the Snakes and Ladders game with:
- Multi-player support (2-6 players)
- Configurable board size (default 100)
- Snakes and ladders on the board
- Turn-based gameplay
- Dice rolling with random outcomes
- Win condition checking
- Game history tracking

## 🏗️ Architecture

### Package Structure
```
snakes_and_ladder/
├── enums/          # GameStatus, PlayerStatus
├── factory/        # GameFactory, PlayerFactory
├── model/          # Game, Player, Board, Dice, Snake, Ladder
├── service/        # GameService, PlayerService
└── Main.java      # Entry point and game loop
```

## 📐 UML Class Diagram

```
┌────────────────────────────────────────────────────────────┐
│        Snakes and Ladders Game Architecture                │
└────────────────────────────────────────────────────────────┘

         ┌──────────────────┐
         │      Game        │
         ├──────────────────┤
         │- gameId          │
         │- board: Board    │
         │- players: List   │
         │- currentPlayer   │
         │- gameStatus      │
         │- winner: Player  │
         ├──────────────────┤
         │+ startGame()     │
         │+ playTurn()      │
         │+ endGame()       │
         │+ getWinner()     │
         │+ getGameState()  │
         └──────────────────┘
                 │
        ┌────────┼────────┬──────────┐
        │        │        │          │
        ▼        ▼        ▼          ▼
    ┌──────┐ ┌──────┐ ┌──────┐ ┌──────────┐
    │Board │ │Player│ │ Dice │ │ GameLog  │
    └──────┘ └──────┘ └──────┘ └──────────┘

         ┌──────────────────┐
         │      Board       │
         ├──────────────────┤
         │- size: int       │
         │- snakes: Map     │
         │- ladders: Map    │
         ├──────────────────┤
         │+ getNextPosition()
         │+ hasSnake(pos)   │
         │+ hasLadder(pos)  │
         │+ getFinalPos()   │
         └──────────────────┘
                 △
        ┌────────┼─────────┐
        │        │         │
        ▼        ▼         ▼
    ┌──────┐ ┌──────┐ ┌──────┐
    │Snake │ │ pos  │ │Ladder│
    └──────┘ └──────┘ └──────┘

         ┌──────────────────┐
         │      Player      │
         ├──────────────────┤
         │- playerId        │
         │- name            │
         │- currentPosition │
         │- isActive        │
         │- score: int      │
         ├──────────────────┤
         │+ moveForward()   │
         │+ getPosition()   │
         │+ setPosition()   │
         └──────────────────┘

         ┌──────────────────┐
         │       Dice       │
         ├──────────────────┤
         │- sides: int      │
         ├──────────────────┤
         │+ roll(): int     │
         │+ roll(n): int[]  │
         └──────────────────┘

    ┌──────────────────────────┐
    │      GameStatus          │
    ├──────────────────────────┤
    │- NOT_STARTED             │
    │- IN_PROGRESS             │
    │- COMPLETED              │
    │- PAUSED                 │
    └──────────────────────────┘
```

## 🔑 Key Features

### 1. **Game Board**
- Standard 10×10 board (100 squares)
- Configurable snakes (go down)
- Configurable ladders (go up)
- Position tracking for each player

### 2. **Snakes Configuration**
```
Default Snakes:
16 → 6,   62 → 19,   95 → 75
47 → 26,  87 → 24,   98 → 79
```

### 3. **Ladders Configuration**
```
Default Ladders:
1 → 38,   4 → 14,    9 → 31
21 → 42,  51 → 67,   72 → 91
```

### 4. **Turn-Based Gameplay**
1. Player rolls dice (1-6)
2. Move forward by dice value
3. Check for snake/ladder
4. Next player's turn
5. First to reach 100 wins

### 5. **Win Conditions**
- Reach exactly position 100
- OR pass position 100 (optional rule)
- Multiple players cannot win simultaneously

## 💻 Usage Example

```java
// Create game with 4 players
List<Player> players = new ArrayList<>();
players.add(new Player("Alice"));
players.add(new Player("Bob"));
players.add(new Player("Charlie"));
players.add(new Player("Diana"));

Game game = new Game(players, 100);

// Start game
game.startGame();

// Play turns
while (game.getGameStatus() != GameStatus.COMPLETED) {
    Player currentPlayer = game.getCurrentPlayer();
    
    // Roll dice
    Dice dice = new Dice(6);
    int diceValue = dice.roll();
    
    System.out.println(currentPlayer.getName() + " rolled: " + diceValue);
    
    // Play turn
    game.playTurn(diceValue);
    
    // Display board state
    displayBoard(game);
}

// Get winner
System.out.println("Winner: " + game.getWinner().getName());
```

## 🎯 Game Mechanics

### Movement Rules
1. **Normal Move**: position += dice_value
2. **Snake**: If land on snake head, go down to tail
3. **Ladder**: If land on ladder bottom, go up to top
4. **Exact 100**: Must reach exactly 100 to win
5. **Overshoot**: Can't go beyond 100 (some rule variants)

### Turn Order
- Players take turns in sequence
- After each player's turn, next player's turn begins
- Continue until someone wins

### Special Rules (Optional)
- Double dice rolls: Extra turn if doubles rolled
- Exact landing: Must roll exact to reach 100
- Multiple dice: Can roll multiple dice per turn

## 📊 Game Statistics

```
Board Size: 100
Total Snakes: 6
Total Ladders: 6
Snake Lengths: 5-28 squares
Ladder Heights: 8-40 squares
```

## ✅ Core Methods

### Game
- `startGame()` - Initialize and start
- `playTurn(diceValue)` - Process player turn
- `endGame()` - Finish game and declare winner
- `getWinner()` - Get winning player
- `getCurrentPlayer()` - Get active player
- `getGameStatus()` - Get current status

### Player
- `moveForward(steps)` - Update position
- `getPosition()` - Get current position
- `setPosition(pos)` - Set position directly
- `getName()` - Get player name
- `isWinner()` - Check if player won

### Board
- `getNextPosition(current, steps)` - Calculate final position
- `hasSnake(position)` - Check for snake
- `hasLadder(position)` - Check for ladder
- `getFinalPosition(pos)` - Get position after snake/ladder
- `displayBoard()` - Print board state

## 🧪 Testing Scenarios

Test cases should cover:
- Basic movement without snake/ladder
- Landing on snake (move down)
- Landing on ladder (move up)
- Winning condition (reach 100)
- Multiple players taking turns
- Dice rolling randomness
- Invalid moves (move beyond board)
- Game state transitions
- Win detection

## 📈 Example Game Flow

```
Initial State:
Alice: pos=0, Bob: pos=0, Charlie: pos=0

Turn 1 - Alice rolls 4:
  Alice moves: 0 → 4
  State: Alice=4, Bob=0, Charlie=0

Turn 2 - Bob rolls 3:
  Bob moves: 0 → 3
  State: Alice=4, Bob=3, Charlie=0

Turn 3 - Charlie rolls 9:
  Charlie moves: 0 → 9 (LADDER: 9 → 31)
  State: Alice=4, Bob=3, Charlie=31

...

Final Turn - Alice rolls 6:
  Alice moves: 94 → 100 (WINS!)
  Game Status: COMPLETED
  Winner: Alice
```

## 🎲 Dice Implementation

```java
// Standard 6-sided die
Dice dice = new Dice(6);
int roll = dice.roll();  // Returns 1-6

// Rolling n times
int[] rolls = dice.roll(3);  // Three rolls
```

---

**Back to [Parent README](../README.md)**
