from collections import deque

# Capacities
CAP_A = 4
CAP_B = 3

# Goal test
def is_goal(state):
    return state[0] == 2

# Generate all valid next states
def get_neighbors(state):
    a, b = state
    neighbors = []

    # Fill operations
    neighbors.append((CAP_A, b))   # Fill A
    neighbors.append((a, CAP_B))   # Fill B

    # Empty operations
    neighbors.append((0, b))       # Empty A
    neighbors.append((a, 0))       # Empty B

    # Pour A -> B
    pour = min(a, CAP_B - b)
    neighbors.append((a - pour, b + pour))

    # Pour B -> A
    pour = min(b, CAP_A - a)
    neighbors.append((a + pour, b - pour))

    return neighbors

# BFS implementation
def bfs():
    start = (0, 0)
    queue = deque([start])
    visited = set([start])
    parent = {start: None}

    while queue:
        current = queue.popleft()

        if is_goal(current):
            return parent, current

        for neighbor in get_neighbors(current):
            if neighbor not in visited:
                visited.add(neighbor)
                parent[neighbor] = current
                queue.append(neighbor)

    return None, None

# Reconstruct path
def print_solution(parent, goal):
    path = []
    while goal is not None:
        path.append(goal)
        goal = parent[goal]
    path.reverse()

    print("Solution Path:")
    for step in path:
        print(step)

# Run the program
parent, goal = bfs()
print_solution(parent, goal)
