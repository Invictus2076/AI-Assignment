from collections import deque

CAP_A, CAP_B = 4, 3

def neighbors(a, b):
    return {
        "Fill A": (CAP_A, b),
        "Fill B": (a, CAP_B),
        "Empty A": (0, b),
        "Empty B": (a, 0),
        "Pour A → B": (a - min(a, CAP_B - b), b + min(a, CAP_B - b)),
        "Pour B → A": (a + min(b, CAP_A - a), b - min(b, CAP_A - a))
    }

def bfs():
    start = (0, 0)
    queue = deque([(start, [])])
    visited = set([start])

    while queue:
        (a, b), path = queue.popleft()
        input(f"\nCurrent State: A={a}, B={b}  (press Enter)")

        if a == 2:
            print("\n✅ Goal Reached!")
            for step in path:
                print(step)
            print(f"Final State: A={a}, B={b}")
            return

        for action, (na, nb) in neighbors(a, b).items():
            if (na, nb) not in visited:
                visited.add((na, nb))
                queue.append(((na, nb), path + [f"{action} → ({na},{nb})"]))

bfs()
