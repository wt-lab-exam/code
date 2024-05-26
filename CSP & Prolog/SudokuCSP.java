import java.util.Arrays;

public class SudokuCSP {

    private static final int SIZE = 9;

    public static void main(String[] args) {
        int[][] sudokuGrid = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        SudokuCSP sudoku = new SudokuCSP();
        if (sudoku.solveSudoku(sudokuGrid)) {
            System.out.println("Sudoku solved successfully:");
            sudoku.printSolution(sudokuGrid);
        } else {
            System.out.println("No solution exists.");
        }
    }

    public boolean solveSudoku(int[][] grid) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Find an empty cell in the grid
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // If no empty cell is found, the puzzle is solved
        if (isEmpty) {
            return true;
        }

        // Try filling the empty cell with values 1 to 9
        for (int num = 1; num <= SIZE; num++) {
            if (isValidMove(grid, row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku(grid)) {
                    return true; // Solution found
                }
                grid[row][col] = 0; // Backtrack if solution not found
            }
        }

        return false; // No solution found
    }

    // Check if a move is valid
    private boolean isValidMove(int[][] grid, int row, int col, int num) {
        // Check if num is already present in the row or column
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }

        // Check if num is already present in the 3x3 subgrid
        int subgridRow = row - row % 3;
        int subgridCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[subgridRow + i][subgridCol + j] == num) {
                    return false;
                }
            }
        }

        return true; // Move is valid
    }

    // Method to print the solved Sudoku grid
    private void printSolution(int[][] grid) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
