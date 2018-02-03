# RushHour Game Solver

## Overview

This is a simple RushHour solver. 
The first implementation uses **Bread-First-Search bruteforce** exploration. 
We provide a smarter solver using **A\* algorithm** with some **heuristics**.

## Usage

### Quick tests
The package tests runs a bunch of tests of all the heuristics.
It also display a fancy graphic animation during the tests.


### RushHourGame

The `RushHourGame` class provides a structure to represent a RushHour Game. This class comes with several methods:
 - `loadFromFile(String filename)` initialize a game from the given file
 - `initFromHash(String hash)` change an existing game board layout according to the given hash
 - Other private methods are implemented and commented

### RushHourGraphics
This class implements a graphic representation of a RushHourGame.
Its constructor needs a `RushHourGame` object.
It can be animated according to a `Movement` list with the method `animeMovements`.


### RushHourSolver
This is the core of the solver. A `RushHourGame` instance must be given to the constructor. Two methods:

- `bruteforce()`: Compute a solution using DFS
- `astar(Heuristic h)`: Compute a solution using A* algorithm with the given heuristic. It uses a `PriorityQueue` manipulating `AStarState` objects.


> You can find the different heuristics in the `Report.pdf`

