package modele;
import java.util.Random;
public abstract class Player extends Occupant implements Moveable {
    private int direction;
    private final char name;
    public Player(char name, Position position, int direction) {
        super(position);
        this.direction = direction;
        this.name = name;
    }
    public Player(char name, Position position) {
        this(name, position, new Random().nextInt(8));
    }
    public Player(char name) {
        this(name, new Position(), new Random().nextInt(8));
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
        int[] rows = {0, -1, -1, -1, 0, 1, 1, 1};
        int[] cols = {1, 1, 0, -1, -1, -1, 0, 1};
        return new Position(getRow() + rows[direction], getCol() + cols[direction]);
    }
    @Override
    public void setNextPosition(Position p) {
        setPosition(p);
    }
    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
