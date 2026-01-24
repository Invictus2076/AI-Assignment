#include <iostream>
using namespace std;

int board[8];  // board[row] = column of queen

bool isSafe(int row, int col) {
    for (int i = 0; i < row; i++) {
        if (board[i] == col || abs(board[i] - col) == row - i)
            return false;
    }
    return true;
}

bool solve(int row) {
    if (row == 8)
        return true;

    for (int col = 0; col < 8; col++) {
        if (isSafe(row, col)) {
            board[row] = col;
            if (solve(row + 1))
                return true;
        }
    }
    return false;
}

void printBoard() {
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
            if (board[i] == j)
                cout << "Q ";
            else
                cout << ". ";
        }
        cout << endl;
    }
}

int main() {
    if (solve(0)) {
        cout << "Solution Found:\n\n";
        printBoard();
    } else {
        cout << "No solution exists\n";
    }
    return 0;
}
