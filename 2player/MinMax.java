import java.util.Scanner;
public class MinMax {
    private static final int COMPUTER = 1;
    private static final int Player = 2;
    private static final int SIDE = 3; 
    private static final char AI = 'O';
    private static final char P1 = 'X';

    private static void showBoard(char[][] board) {
        System.out.printf("\t\t\t %c | %c | %c \n",board[0][0],board[0][1], board[0][2]);
        System.out.println("\t\t\t_____________");
        System.out.printf("\t\t\t %c | %c | %c \n", board[1][0], board[1][1], board[1][2]);
        System.out.println("\t\t\t_____________");
        System.out.printf("\t\t\t %c | %c | %c \n\n", board[2][0], board[2][1], board[2][2]);
    }
    
private static void showInstructions() {
        System.out.println("\nChoose a cell numbered from 1 to 9 as below and play\n");
        System.out.println("\t\t\t 1 | 2 | 3 ");
        System.out.println("\t\t\t____________\n");
        System.out.println("\t\t\t 4 | 5 | 6 ");
        System.out.println("\t\t\t____________\n");
        System.out.println("\t\t\t 7 | 8 | 9 \n");
    }

    private static void initialise(char[][] board) {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                board[i][j] = ' ';
            }
        }
    }
    
private static void declareWinner(int whoseTurn) {
        if (whoseTurn == COMPUTER)
            System.out.println("COMPUTER has won");
        else
            System.out.println("Player has won");
    }

    private static boolean rowCrossed(char[][] board) {
        for (int i = 0; i < SIDE; i++) {
           if(board[i][0]==board[i][1] && board[i][1]==board[i][2] && board[i][0]!=' ')
                return true;
        }
        return false;
    }
    private static boolean columnCrossed(char[][] board) {
        for(int i = 0; i < SIDE; i++) {
           if(board[0][i]==board[1][i] && board[1][i]==board[2][i] && board[0][i]!=' ')
                return true;
        }
        return false;
    }

    private static boolean diagonalCrossed(char[][] board) {
        return (board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0] != ' ')||(board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ');
    }

    private static boolean gameOver(char[][] board) {
        return rowCrossed(board) || columnCrossed(board) || diagonalCrossed(board);
    }

    private static int minimax(char[][] board, int depth, boolean isAI) {
        int score = 0;
        int bestScore;

        if (gameOver(board)) {
            return isAI ? -1 : 1;
        } else {
            if (depth < 9) {
                if (isAI) {
                    bestScore = -999;
                    for (int i = 0; i < SIDE; i++) {
                        for (int j = 0; j < SIDE; j++) {
                            if (board[i][j] == ' ') {
                                board[i][j] = AI;
                                score = minimax(board, depth + 1, false);
                                board[i][j] = ' ';
                                if (score > bestScore) {
                                    bestScore = score;
                                }
                            }
                        }
                    }
                    return bestScore;
                } else {
                    bestScore = 999;
                    for (int i = 0; i < SIDE; i++) {
                        for (int j = 0; j < SIDE; j++) {
                            if (board[i][j] == ' ') {
                                board[i][j] = P1;
                                score = minimax(board, depth + 1, true);
                                board[i][j] = ' ';
                                if (score < bestScore) {
                                    bestScore = score;
                                }
                            }
                        }
                    }
                    return bestScore;
                }
            } else {
                return 0;
            }
        }
    }

    private static int bestMove(char[][] board, int moveIndex) {
        int x = -1, y = -1;
        int score;
        int bestScore = -999;

        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = AI;
                    score = minimax(board, moveIndex + 1, false);
                    board[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        x = i;
                        y = j;
                    }
                }
            }
        }
        return x * 3 + y;
    }

    private static void playTicTacToe() {
        char[][] board = new char[SIDE][SIDE];
        int moveIndex = 0, x, y;
        initialise(board);
        showInstructions();
        while (!gameOver(board) && moveIndex != SIDE * SIDE) {
            int n;
            if (moveIndex % 2 == 0) {
                System.out.print("You can insert in the following positions : ");
                for (int i = 0; i < SIDE; i++)
                    for (int j = 0; j < SIDE; j++)
                        if (board[i][j] == ' ')
                            System.out.print((i * 3 + j) + 1 + " ");
                System.out.print("\nEnter the position = ");
                Scanner scanner = new Scanner(System.in);
                n = scanner.nextInt();
                n--;
                x = n / SIDE;
                y = n % SIDE;
                if (board[x][y] == ' ' && n < 9 && n >= 0) {
                    board[x][y] = P1;
                    System.out.printf("Player has put a %c in cell %d\n\n", P1, n + 1);
                    showBoard(board);
                    moveIndex++;
                } else if (board[x][y] != ' ' && n < 9 && n >= 0) {
                    System.out.println("\nPosition is occupied! (n < 0 || n > 8) {
                    System.out.println("Invalid position\n");
                }
                scanner.close();
            } else {
                n = bestMove(board, moveIndex);
                x = n / SIDE;
                y = n % SIDE;
                board[x][y] = AI;
                System.out.printf("COMPUTER has put a %c in cell %d\n\n", AI, n + 1);
                showBoard(board);
                moveIndex++;
            }
        }
        if (!gameOver(board) && moveIndex == SIDE * SIDE)
            System.out.println("It's a draw\n");
        else {
            declareWinner(moveIndex % 2 == 0 ? Player : COMPUTER);
        }
    }

    public static void main(String[] args) {
        System.out.println("\t\t\t Tic-Tac-Toe");
        playTicTacToe();
    }
}

