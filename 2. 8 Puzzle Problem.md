Representation of the 8-Puzzle problem as a state-space problem in java with A*Algorithm implementation.

1. State-Space Representation
State

A state is a 3×3 matrix representing the puzzle configuration.

Example state:

1 2 3
4 0 6
7 5 8


0 represents the blank tile.

Initial State
1 2 3
4 0 6
7 5 8

Goal State
1 2 3
4 5 6
7 8 0

State Space

-All possible legal configurations reachable by sliding the blank tile
-Maximum of 4 operators per state (Up, Down, Left, Right)

Operators:

Move the blank (0) tile:
Up
Down
Left
Right

(Only if the move is valid within grid boundaries)

2. Manhattan Distance Heuristic

h(n) = ∑ (∣xcurrent​−xgoal​∣ + ∣ycurrent​−ygoal​∣)



3. A* Algorithm Definition

f(n)=g(n)+h(n)
f(n)=g(n)+h(n)

Where

g(n) = path cost from start to node n

h(n) = heuristic estimate to goal

4. Sample Simulation Output (Condensed)
Open / Closed Behavior
OPEN → f=2 g=0 h=2
OPEN → f=2 g=1 h=1
OPEN → f=2 g=2 h=0

Solution Path
1 2 3
4 0 6
7 5 8
g=0 h=2 f=2

1 2 3
4 5 6
7 0 8
g=1 h=1 f=2

1 2 3
4 5 6
7 8 0
g=2 h=0 f=2


- Goal reached in 2 moves
