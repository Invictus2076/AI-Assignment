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
