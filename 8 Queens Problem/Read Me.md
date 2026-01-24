8 Queens Problem simulated in C++ using backtracking Algorithm.

1. Constraints in the 8-Queens Problem

To place 8 queens on an 8×8 chessboard, the following constraints must be satisfied:

Row constraint
→ Only one queen per row

Column constraint
→ Only one queen per column

Diagonal constraints

No two queens on the same main diagonal
No two queens on the same anti-diagonal

Mathematically, for queens at positions (r1, c1) and (r2, c2):

r1 ≠ r2
c1 ≠ c2
|r1 − r2| ≠ |c1 − c2|

2. Formulation as a Constraint Satisfaction Problem (CSP)
Variables
Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8

Each variable represents the column position of a queen in a specific row.

Domains
Qi ∈ {0, 1, 2, 3, 4, 5, 6, 7}

Constraints

For all i ≠ j:
Qi ≠ Qj (column constraint)
|Qi − Qj| ≠ |i − j| (diagonal constraint)

3. Use of Backtracking

Backtracking is a systematic search technique that:
-Places queens row by row
-Checks constraints at each step
-Backtracks immediately when a conflict is detected

It:

-Reduces unnecessary exploration
-Ensures correctness
-Efficient for CSPs with strong constraints

4. Simulation: Row-by-Row Placement
Row	Column Chosen
0	0
1	4
2	7
3	5
4	2
5	6
6	1
7	3

-Each placement satisfies all constraints
-Conflicts cause backtracking automatically

5. Final Chessboard Configuration
Q . . . . . . .
. . . . Q . . .
. . . . . . . Q
. . . . . Q . .
. . Q . . . . .
. . . . . . Q .
. Q . . . . . .
. . . Q . . . .

Where:
Q → Queen
. → Empty square
