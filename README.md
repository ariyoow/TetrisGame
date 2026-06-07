# Console Tetris Game in Java

## Overview

This project is a simple console-based implementation of the classic **Tetris** game written in Java.
The game allows the player to move and rotate randomly generated shapes inside a game board using keyboard commands.

The project demonstrates concepts such as:

* Object-Oriented Programming (OOP)
* Matrix manipulation
* Collision detection
* Random shape generation
* Game loop implementation
* Basic board rendering in console

---

# Project Structure

```text
├── Shape.java
├── Tetris.java
└── TetrisGame.java
```

## Files Description

### `Shape.java`

Handles everything related to the current falling shape:

* Random shape generation
* Shape movement
* Shape rotation
* Updating the shape position on the board

### `Tetris.java`

Main game logic:

* Board creation
* User input handling
* Collision checking
* Row and column clearing
* Score management
* Rendering the board in console

### `TetrisGame.java`

Contains the `main` method and starts the game.

---

# Features

* Randomly generated shapes
* Move left, right, and down
* Rotate shapes clockwise
* Score system
* Remove filled rows
* Remove filled columns
* Restart option
* Console-based interface

---

# Controls

| Key | Action       |
| --- | ------------ |
| A   | Move Left    |
| D   | Move Right   |
| S   | Move Down    |
| W   | Rotate Shape |
| R   | Restart Game |
| L   | Exit Game    |

---

# How to Run

## Requirements

* Java 17 or newer

## Compile

```bash
javac *.java
```

## Run

```bash
java TetrisGame
```

---

# Game Rules

* Shapes fall inside the board.
* If a shape touches another shape or the bottom border, it becomes fixed.
* Filled rows are removed and increase the score.
* Filled columns are removed and decrease the score.
* The game ends when the score becomes negative or the player exits.

---

# Scoring System

| Event          | Score |
| -------------- | ----- |
| Clear a row    | +100  |
| Clear a column | -10   |

---

# Technical Details

## Board Size

```java
ROW = 22
COLUMN = 10
```

## Shape Representation

Shapes are represented using 2D `char` arrays.

Example:

```text
* * *
  *
```

---

# Concepts Used

* 2D Arrays
* Encapsulation
* Random Number Generation
* Matrix Rotation
* Collision Detection
* Game State Management

---

# Possible Improvements

Some ideas for future development:

* Add real Tetris shapes (I, O, T, L, J, S, Z)
* Add automatic falling timer
* Improve rotation collision handling
* Add levels and speed increase
* Save high scores
* Add GUI using Java Swing or JavaFX
* Improve game over detection

---

# Author

Developed as a Java programming project for practicing object-oriented design and game logic.
