import java.util.*;

class Node {
    int[][] state;
    int g, h;
    Node parent;

    Node(int[][] state, int g, int h, Node parent) {
        this.state = state;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }

    int f() {
        return g + h;
    }
}

public class EightPuzzleAStar {

    static int[][] goal = {
        {1,2,3},
        {4,5,6},
        {7,8,0}
    };

    static int manhattan(int[][] state) {
        int dist = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int val = state[i][j];
                if (val != 0) {
                    int goalX = (val - 1) / 3;
                    int goalY = (val - 1) % 3;
                    dist += Math.abs(i - goalX) + Math.abs(j - goalY);
                }
            }
        }
        return dist;
    }

    static List<int[][]> getNeighbors(int[][] state) {
        List<int[][]> neighbors = new ArrayList<>();
        int x = 0, y = 0;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (state[i][j] == 0) { x = i; y = j; }

        int[][] moves = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int[] m : moves) {
            int nx = x + m[0], ny = y + m[1];
            if (nx >= 0 && ny >= 0 && nx < 3 && ny < 3) {
                int[][] newState = copy(state);
                newState[x][y] = newState[nx][ny];
                newState[nx][ny] = 0;
                neighbors.add(newState);
            }
        }
        return neighbors;
    }

    static int[][] copy(int[][] state) {
        int[][] newState = new int[3][3];
        for (int i = 0; i < 3; i++)
            newState[i] = state[i].clone();
        return newState;
    }

    static boolean isGoal(int[][] state) {
        return Arrays.deepEquals(state, goal);
    }

    static void solve(int[][] start) {
        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingInt(Node::f));
        Set<String> closed = new HashSet<>();

        Node startNode = new Node(start, 0, manhattan(start), null);
        open.add(startNode);

        while (!open.isEmpty()) {
            Node current = open.poll();

            System.out.println("OPEN → f=" + current.f() +
                               " g=" + current.g +
                               " h=" + current.h);

            if (isGoal(current.state)) {
                printPath(current);
                return;
            }

            closed.add(Arrays.deepToString(current.state));

            for (int[][] neighbor : getNeighbors(current.state)) {
                if (closed.contains(Arrays.deepToString(neighbor)))
                    continue;

                Node child = new Node(
                    neighbor,
                    current.g + 1,
                    manhattan(neighbor),
                    current
                );
                open.add(child);
            }
        }
    }

    static void printPath(Node node) {
        Stack<Node> path = new Stack<>();
        while (node != null) {
            path.push(node);
            node = node.parent;
        }

        System.out.println("\nSolution Path:");
        while (!path.isEmpty()) {
            Node n = path.pop();
            printState(n.state);
            System.out.println("g=" + n.g + " h=" + n.h + " f=" + n.f());
            System.out.println();
        }
    }

    static void printState(int[][] state) {
        for (int[] row : state) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] start = {
            {1,2,3},
            {4,0,6},
            {7,5,8}
        };
        solve(start);
    }
}
