package modele;

import java.util.Random;
import java.util.Objects;


public class Position {
    private  int MAX_ROW = 10, MAX_COL = 30;
    private int row, col;

    public Position(int MAX_ROW, int MAX_COL, int row, int col) {
        this.row = row;
        this.col = col;
        this.MAX_COL = MAX_COL;
        this.MAX_ROW = MAX_ROW;
    }
    public Position(int MAX_ROW, int MAX_COL) {
        this(new Random().nextInt(MAX_ROW - 2) + 1, new Random().nextInt(MAX_COL - 2) + 1, MAX_ROW, MAX_COL);
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

    public double distance(Position p) {
        return Math.sqrt(Math.pow(row - p.row, 2) + Math.pow(col - p.col, 2));
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
