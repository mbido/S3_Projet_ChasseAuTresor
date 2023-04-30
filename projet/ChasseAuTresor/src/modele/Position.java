package modele;

import java.util.Random;
import java.util.Objects;


public class Position {
    private static final int MAX_ROW = 10, MAX_COL = 30;
    private int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Position() {
        this(new Random().nextInt(MAX_ROW - 2) + 1, new Random().nextInt(MAX_COL - 2) + 1);
    }
    public int getMaxRow() {
        return MAX_ROW;
    }
    public int getMaxCol() {
        return MAX_COL;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public boolean isOutside() {
        return row < 0 || row >= MAX_ROW || col < 0 || col >= MAX_COL;
    }

    @Override
    public String toString() {
        return "[" + row + "][" + col + "]";
    }
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Position position = (Position) obj;
        return row == position.row && col == position.col;
    }
}
