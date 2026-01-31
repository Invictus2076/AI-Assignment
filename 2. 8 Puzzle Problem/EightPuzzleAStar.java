import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class EightPuzzleGUI extends JFrame {

    private JButton[][] buttons = new JButton[3][3];

    private int[][] state = {
        {1, 2, 3},
        {4, 0, 6},
        {7, 5, 8}
    };

    private final int[][] goal = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    public EightPuzzleGUI() {
        setTitle("8-Puzzle Problem");
        setSize(350, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // -------- TOP BANNER --------
        JLabel topBanner = new JLabel("8-Puzzle Problem", SwingConstants.CENTER);
        topBanner.setFont(new Font("Arial", Font.BOLD, 18));
        topBanner.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(topBanner, BorderLayout.NORTH);

        // -------- GRID --------
        JPanel grid = new JPanel(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 24);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton();
                btn.setFont(font);
                buttons[i][j] = btn;
                int r = i, c = j;
                btn.addActionListener(e -> moveTile(r, c));
                grid.add(btn);
            }
        }

        add(grid, BorderLayout.CENTER);

        // -------- BOTTOM BANNER --------
        JLabel bottomBanner = new JLabel(
            "By Siddharth Singh Khati 24BCE7783",
            SwingConstants.CENTER
        );
        bottomBanner.setFont(new Font("Arial", Font.PLAIN, 12));
        bottomBanner.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(bottomBanner, BorderLayout.SOUTH);

        updateGrid();
        setVisible(true);
    }

    // -------- MOVE TILE --------
    private void moveTile(int r, int c) {
        int[] blank = findBlank();
        int br = blank[0], bc = blank[1];

        if (Math.abs(br - r) + Math.abs(bc - c) == 1) {
            state[br][bc] = state[r][c];
            state[r][c] = 0;
            updateGrid();
        }
    }

    // -------- UPDATE GRID --------
    private void updateGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    buttons[i][j].setText("");
                    buttons[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    buttons[i][j].setText(String.valueOf(state[i][j]));
                    buttons[i][j].setBackground(Color.WHITE);
                }
            }
        }

        if (Arrays.deepEquals(state, goal)) {
            JOptionPane.showMessageDialog(this, "🎉 Puzzle Solved!");
        }
    }

    // -------- FIND BLANK --------
    private int[] findBlank() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (state[i][j] == 0)
                    return new int[]{i, j};
        return null;
    }

    public static void main(String[] args) {
        new EightPuzzleGUI();
    }
}
