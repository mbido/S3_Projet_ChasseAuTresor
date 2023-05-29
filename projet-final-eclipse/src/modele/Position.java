package modele;

import java.util.Random;
import java.util.Objects;

/**
 * Represent a position in the grid
 * A position always knows the maximum row and column of the grid
 */
public class Position {
    private  int MAX_ROW = 10, MAX_COL = 30;
    private final int row;
    private final int col;

    /**
     * Create a position with a given row and column and a maximum row and column
     *
     * @param MAX_ROW the maximum row of the grid
     * @param MAX_COL the maximum column of the grid
     * @param row the row of the position
     * @param col the column of the position
     */
    public Position(int MAX_ROW, int MAX_COL, int row, int col) {
        this.row = row;
        this.col = col;
        this.MAX_COL = MAX_COL;
        this.MAX_ROW = MAX_ROW;
    }

    /**
     * Create a random position with a given maximum row and column
     *
     * @param MAX_ROW the maximum row of the grid
     * @param MAX_COL the maximum column of the grid
     */
    public Position(int MAX_ROW, int MAX_COL) {
        this(new Random().nextInt(MAX_ROW - 2) + 1, new Random().nextInt(MAX_COL - 2) + 1, MAX_ROW, MAX_COL);
    }

    /**
     * Get the maximum row of the grid
     *
     * @return the maximum row of the grid : MAX_ROW
     */
    public int getMaxRow() {
        return MAX_ROW;
    }

    /**
     * Get the maximum column of the grid
     *
     * @return the maximum column of the grid : MAX_COL
     */
    public int getMaxCol() {
        return MAX_COL;
    }

    /**
     * Get the row of the position
     *
     * @return the row of the position : row
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the column of the position
     *
     * @return the column of the position : col
     */
    public int getCol() {
        return col;
    }

    /**
     * Get the distance between two positions
     *
     * @param p the position to compare with
     *
     * @return the distance between the two positions (pythagore)
     */
    public double distance(Position p) {
        return Math.sqrt(Math.pow(row - p.row, 2) + Math.pow(col - p.col, 2));
    }

    /**
     * Get the string representation of the position
     *
     * @return the string representation of the position : [row][col]
     */
    @Override
    public String toString() {
        return "[" + row + "][" + col + "]";
    }

    /**
     * Get the hashcode of the position for mapping it
     *
     * @return the hashcode of the position
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    /**
     * Compare two positions
     *
     * @param obj the position to compare with
     *
     * @return true if the positions are equals, false otherwise
     */
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
