package modele;

import java.util.Random;

/**
 * A character is an occupant that can move.
 * It has a direction and a temporary direction.
 * The possible directions are: 0, 1, 2, 3, 4, 5, 6, 7
 * 0 beeing right and the other directions going counter-clockwise.
 */
public abstract class Character extends Occupant implements Moveable {
    private int waitingTime;
    private int direction;
    private int tempDir;
    private final char name;

    /**
     * Create a character with a position and a direction
     *
     * @param name the name of the character
     * @param position the position of the character
     * @param direction the direction of the character
     */
    public Character(char name, Position position, int direction) {
        super(position, false);
        this.direction = direction;
        this.tempDir = -1;
        this.name = name;
        this.waitingTime = 0;
    }

    /**
     * Create a character with a position and a random direction
     *
     * @param name the name of the character
     * @param position the position of the character
     */
    public Character(char name, Position position) {
        this(name, position, new Random().nextInt(8));
    }

    /**
     * Give the direction of the character
     *
     * @return the current direction of the character
     */
    @Override
    public int getDirection() {
        return direction;
    }

    /**
     * Set the direction of the character
     *
     * @param direction the new direction of the character
     */
    @Override
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Set the temporary direction of the character
     *
     * @param tempDir the new temporary direction of the character
     */
    public void setTempDir(int tempDir) {
        this.tempDir = tempDir;
    }

    /**
     * Give the next position of the character based on its direction
     * Be very careful with this method as it does reset the temporary direction
     *
     * @return the next position of the character
     */
    @Override
    public Position getNextPosition() {
        int row = getRow();
        int col = getCol();
        int MAX_ROW = getPosition().getMaxRow();
        int MAX_COL = getPosition().getMaxCol();
        int[] rows = { 0, -1, -1, -1, 0, 1, 1, 1 };
        int[] cols = { 1, 1, 0, -1, -1, -1, 0, 1 };

        Position res;
        if (tempDir != -1) {
            res = new Position(MAX_ROW, MAX_COL, row + rows[tempDir], col + cols[tempDir]);
            tempDir = -1;
        }else{
            res = new Position(MAX_ROW, MAX_COL, row + rows[direction], col + cols[direction]);
        }
        return res;
    }

    /**
     * Set the new position of the character
     *
     * @param p the new position of the character
     */
    @Override
    public void setNextPosition(Position p) {
        setPosition(p);
    }

    /**
     * Give the string representation of the character being its name
     *
     * @return the name of the character
     */
    @Override
    public String toString() {
        return String.valueOf(name);
    }

    /**
     * Give the waiting time of the character before moving
     *
     * @return the waiting time of the character
     */
    public int getWaitingTime() {
        return waitingTime;
    }

    /**
     * Set the waiting time of the character before moving
     *
     * @param time the new waiting time of the character
     */
    public void setWaitingTime(int time) {
        waitingTime = time;
    }
}
