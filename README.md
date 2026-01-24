# Water-Jug-Problem
The traditional water jug problem implemented in Python with State-Space Search using BFS/DFS
Consider two water jugs:
 Jug A with capacity 4 liters
 Jug B with capacity 3 liters
The goal is to obtain 2 liters of water in Jug A.

1. Formulation as a State-Space Search Problem

The Water Jug Problem can be modeled as a state-space search, where:

Each state represents the amount of water in both jugs.

Actions (operators) move the system from one state to another.

The objective is to reach a goal state satisfying the requirement.

2. Problem Definition
State Representation

A state is represented as a tuple:

(A, B)


where:

A = amount of water in Jug A (0–4 liters)

B = amount of water in Jug B (0–3 liters)

Initial State
(0, 0)


Both jugs are empty.

Goal State
(2, B)  where B ∈ {0,1,2,3}


Jug A must contain exactly 2 liters (Jug B can have any amount).

Operators (Actions)

Fill Jug A → (4, B)

Fill Jug B → (A, 3)

Empty Jug A → (0, B)

Empty Jug B → (A, 0)

Pour A → B

Pour until Jug A is empty or Jug B is full

Pour B → A

Pour until Jug B is empty or Jug A is full

3. State-Space Graph (Conceptual)

Nodes: All possible states (A, B) where 0 ≤ A ≤ 4 and 0 ≤ B ≤ 3

Edges: Transitions produced by applying the operators

Root node: (0,0)

Goal node(s): (2,0), (2,1), (2,2), (2,3)

This graph is finite and unweighted, which makes BFS ideal.
