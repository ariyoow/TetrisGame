import java.util.Random;

public class Shape {
    Random ran;
    public static final int ROW = 22;
    public static final int COLUMN = 10;
    private int size;
    private char[][] randomShape;
    private char[][] finalShape;
    private int targetRow;
    private int targetColumn;

    public Shape() {
        this.ran = new Random();
//        you can set size = ran.nextInt(1,7)
        this.size = 7;
        this.randomShape = new char[size][size];
        this.finalShape = new char[Shape.ROW][Shape.COLUMN];
        makeShape();
        this.targetRow = 0;
        this.targetColumn = 0;
    }

    public char[][] getFinalShape() {
        return finalShape;
    }

    public char[][] getRandomShape() { return randomShape; }

    private void makeShape() {

        boolean flag = true;
        boolean rowORcolumn;
        int plusORminus;

        int row = ran.nextInt(0, size);
        int column = ran.nextInt(0, size);

        while (flag) {
            randomShape[row][column] = '*';

            rowORcolumn = ran.nextBoolean();
            plusORminus = ran.nextBoolean() ? 1 : -1;

            if (rowORcolumn) {
                row += plusORminus;
            } else {
                column += plusORminus;
            }

            if (row < 0 || row >= size || column < 0 || column >= size || randomShape[row][column] != 0) {
                flag = false;
            }
        }
        makeFinalShape();
    }

    private void makeFinalShape() {
        int shRow = finalShape.length;

        for (int i = 0; i < shRow; i++) {
            int shColumn = finalShape[i].length;

            for (int j = 0; j < shColumn; j++)
                if (finalShape[i][j] == '*')
                    if (j + 1 < Shape.COLUMN)
                        finalShape[i + this.targetRow][j + 1 + this.targetColumn] = finalShape[i][j];
        }
    }

    public void moveShape(char ch) {
        switch (ch) {
            case 'd':
                moveRight();
                break;
            case 'a':
                moveLeft();
                break;
            case 'w':
                rotateClockwise();
                break;
            case 's':
                moveDown();
                break;
        }
        cleanShape(finalShape);
        makeFinalShape();
    }

    private void cleanShape(char[][] shape) {
        for (int i = 0; i < shape.length; i++)
            for (int j = 0; j < shape[i].length; j++)
                shape[i][j] = ' ';
    }

    public int moveLeft() {
        return --targetColumn;
    }

    public int moveRight() {
        return ++targetColumn;
    }

    public int moveDown() {
        return ++targetRow;
    }

    public void rotateClockwise() {
        char[][] rotatedShape = new char[randomShape.length][randomShape.length];
        for (int i = 0; i < randomShape.length; i++)
            for (int j = 0; j < randomShape.length; j++)
                rotatedShape[j][randomShape.length - 1 - i] = randomShape[i][j];

        for (int i = 0; i < randomShape.length; i++)
            for (int j = 0; j < randomShape.length; j++)
                randomShape[i][j] = rotatedShape[i][j];
    }
}
