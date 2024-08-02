import java.util.Scanner;

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        boolean userConfirm;

        while (!done) {
            userConfirm = SafeInput.getYNConfirm(in, "Do you want to play TicTacToe?");
            System.out.println();

            if (!userConfirm) {
                System.out.println("Thanks anyways. Have a nice day. :)");
                break;
            }

            clearBoard();
            display();

            boolean gameReadySet = true;
            while (gameReadySet) {

                int playerRow = SafeInput.getRangedInt(in, "Enter your row move: ", 1, 3) - 1;
                int playerCol = SafeInput.getRangedInt(in, "Enter your column move: ", 1, 3) - 1;

                if (isValidMove(playerRow, playerCol)) {
                    board[playerRow][playerCol] = currentPlayer;
                    display();

                    if (isWin(currentPlayer)) {
                        System.out.println("Player " + currentPlayer + " wins!");
                        gameReadySet = false;
                    } else if (isTieOrBoardFull()) {
                        System.out.println("It's a tie!");
                        gameReadySet = false;
                    } else {
                        changePlayer();
                    }

                }
                else
                {
                    System.out.println("This move is not valid.");
                }
            }

            done = !SafeInput.getYNConfirm(in, "Do you want to keep playing? :)");

        }

        in.close();

    }

    //private/helper methods

    private static void clearBoard()
    {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void display()
    {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                System.out.print(board[row][col]);
                if (col < COL - 1) {
                    System.out.print("  |  ");
                }
            }
            System.out.println();
            if (row < ROW - 1) {
                System.out.println("--------------");
            }
        }
        System.out.println();
    }

    private static boolean isValidMove(int row, int col) {
        {
            return row >= 0 && row < ROW && col >= 0 && col < COL && board[row][col].equals(" ");
        }
    }

    private static boolean isWin(String player)
    {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player)
    {
        for (int col = 0; col < COL; col++)
        {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }


    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player) ||
                board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTieOrBoardFull() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void changePlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }
}

