import java.util.Scanner;

public class Tetris {
    Scanner scan;
    private char[][] fixedBoard;
    private char[][] currentBoard;
    private Shape currentShape;
    private static int score;

    public Tetris() {
        scan = new Scanner(System.in);
        currentShape = new Shape();
        fixedBoard = new char[Shape.ROW][Shape.COLUMN];
        currentBoard = new char[Shape.ROW][Shape.COLUMN];
        score = 0;
        initializeBoard();
    }

    public void StartGame() {
        System.out.println("""
                *****************************************************************************
                *                                                                           *
                *                                                                           *
                *                                                                           *
                *                       Welcome to TeTrIs gAmE...                           *
                *                                                                           *
                *              Do you want to play?  if Yes type Y if not type N:           *
                *                                                                           *
                *                                                                           *
                *                                                                           *
                *****************************************************************************
                """);
        System.out.print("Type your request: ");

        char ch1 = scan.nextLine().charAt(0);
        if (Character.toUpperCase(ch1) == 'Y') {
            boolean flag = true;

            while (flag) {
                printBoard();

                char ch2;
                if (score < 0)
                    ch2 = 'L';
                else {
                    System.out.print("\nEnter a command (A/S/D/W/R/L): ");
                    ch2 = scan.nextLine().charAt(0);
                }


                switch (Character.toUpperCase(ch2)) {
                    case 'A':
                        moveLeft();
                        break;
                    case 'S':
                        moveDown();
                        break;
                    case 'D':
                        moveRight();
                        break;
                    case 'W':
                        rotation();
                        break;
                    case 'R':
                        new Tetris().StartGame();
                        return;
                    case 'L':
                        System.out.println("Game over!");
                        System.out.println("We look forward to seeing you again.");
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid command. Please enter A, S, D, W, R and L.");
                }
            }

        } else
            System.out.println("We look forward to seeing you again.");

    }

    private void initializeBoard() {
        for (int i = 0; i < Shape.ROW; i++)
            for (int j = 0; j < Shape.COLUMN; j++)
                fixedBoard[i][j] = ' ';

        for (int i = 7; i < Shape.ROW; i++) {
            fixedBoard[i][0] = '*';
            fixedBoard[i][Shape.COLUMN - 1] = '*';
        }
        for (int j = 0; j < Shape.COLUMN; j++)
            fixedBoard[Shape.ROW - 1][j] = '*';
    }

    private void printBoard() {
        makeBoard();

        for (int i = 0; i < Shape.ROW; i++) {
            for (int j = 0; j < Shape.COLUMN; j++)
                System.out.print(this.currentBoard[i][j]);

            System.out.println();
        }
        System.out.println("score :" + score);
    }

    private void makeBoard() {
        char[][] shape = currentShape.getFinalShape();

        for (int i = 0; i < Shape.ROW; i++)
            for (int j = 0; j < Shape.COLUMN; j++)
                currentBoard[i][j] = fixedBoard[i][j];

        for (int i = 0; i < Shape.ROW; i++) {
            for (int j = 0; j < Shape.COLUMN; j++) {
                if (shape[i][j] == '*')
                    currentBoard[i][j] = shape[i][j];
            }
        }
    }

    private void cleanStarsOutOfBoard() {
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 10; j++)
                fixedBoard[i][j] = ' ';
    }

    private void moveRight() {
        moveDown();
        if (canMoveRight())
            currentShape.moveShape('d');
    }

    private void moveDown() {
        if (canMoveDown())
            currentShape.moveShape('s');
        else
            newShape();
    }

    private void moveLeft() {
        moveDown();
        if (canMoveLeft())
            currentShape.moveShape('a');
    }

    private void rotation() {
        moveDown();
        if (canRotation90())
            currentShape.moveShape('w');
    }

    private boolean canMoveRight() {
        char[][] shape = currentShape.getFinalShape();
        for (int i = 0; i < Shape.ROW; i++) {
            for (int j = 0; j < Shape.COLUMN; j++) {
                if (shape[i][j] == '*' && (j == Shape.COLUMN - 1 || fixedBoard[i][j + 1] == '*')) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean canMoveDown() {
        char[][] shape = currentShape.getFinalShape();
        for (int i = 0; i < Shape.ROW; i++) {
            for (int j = 0; j < Shape.COLUMN; j++) {
                if (shape[i][j] == '*' && (i == Shape.ROW - 1 || fixedBoard[i + 1][j] == '*')) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean canMoveLeft() {
        char[][] shape = currentShape.getFinalShape();
        for (int i = 0; i < Shape.ROW; i++) {
            for (int j = 0; j < Shape.COLUMN; j++) {
                if (shape[i][j] == '*' && (j == 0 || fixedBoard[i][j - 1] == '*')) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean canRotation90() {
        char[][] shape = currentShape.getRandomShape();
        int rows = shape.length;

        if (rows == 0 || shape[0].length != rows) {
            throw new IllegalArgumentException("Shape must be a non-empty square matrix");
        }

        char[][] rotatedShape = new char[rows][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                rotatedShape[j][rows - 1 - i] = shape[i][j];
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (rotatedShape[i][j] == '*' && fixedBoard[i][j] == '*') {
                    return false;
                }
            }
        }

        return true;
    }


    private void newShape() {
        for (int i = 0; i < Shape.ROW; i++)
            for (int j = 0; j < Shape.COLUMN; j++)
                fixedBoard[i][j] = currentBoard[i][j];

        cleanStarsOutOfBoard();
        removeFilledRows();
        removeFilledColumns();
        currentShape = new Shape();
    }

    private void removeFilledRows() {
        for (int i = Shape.ROW - 2; i >= 8; i--) {
            boolean rowIsFull = true;
            for (int j = 1; j < Shape.COLUMN - 1; j++) {
                if (fixedBoard[i][j] != '*') {
                    rowIsFull = false;
                    break;
                }
            }
            if (rowIsFull) {
                for (int k = i; k >= 8; k--) {
                    for (int j = 1; j < Shape.COLUMN - 1; j++) {
                        fixedBoard[k][j] = fixedBoard[k - 1][j];
                    }
                }
                i++;
                this.score += 100;
            }
        }
    }

    private void removeFilledColumns() {

        for (int j = 1; j < Shape.COLUMN - 1; j++) {
            boolean isColumnFull = true;

            for (int i = 7; i < Shape.ROW - 1; i++) {
                if (fixedBoard[i][j] != '*') {
                    isColumnFull = false;
                    break;
                }
            }

            if (isColumnFull) {
                for (int i = 0; i < Shape.ROW - 1; i++) {
                    fixedBoard[i][j] = ' ';
                }
                this.score -= 10;
            }
        }
    }
}
