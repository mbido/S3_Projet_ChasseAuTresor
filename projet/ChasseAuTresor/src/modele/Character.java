package modele;

import java.util.Random;

public abstract class Character extends Occupant implements Moveable {
    private int waitingTime;
    private int direction;
    private int tempDir;
    private final char name;

    public Character(char name, Position position, int direction) {
        super(position);
        this.direction = direction;
        this.tempDir = -1;
        this.name = name;
        this.waitingTime = 0;
    }

    public Character(char name, Position position) {
        this(name, position, new Random().nextInt(8));
    }

    @Override
    public int getDirection() {
        return direction;
    }

    @Override
    public void setDirection(int direction) {
        this.direction = direction;
    }

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

    @Override
    public void setNextPosition(Position p) {
        setPosition(p);
    }

    @Override
    public String toString() {
        return String.valueOf(name);

    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int time) {
        waitingTime = time;
    }
}
