package modele;
import java.util.Random;
public abstract class Player extends Occupant implements Moveable {
    private int waitingTime;
    private int direction;
    private final char name;
    public Player(char name, Position position, int direction) {
        super(position);
        this.direction = direction;
        this.name = name;
        this.waitingTime = 0;
    }
    public Player(char name, Position position) {
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
        int[] rows = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] cols = {1, 1, 0, -1, -1, -1, 0, 1};
        return new Position(MAX_ROW, MAX_COL, row + rows[direction], col + cols[direction]);
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
