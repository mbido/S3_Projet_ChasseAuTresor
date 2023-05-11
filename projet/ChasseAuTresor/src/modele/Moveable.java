package modele;

public interface Moveable {
    // the direction of the moveable
    // 3 2 1
    // 4 X 0
    // 5 6 7
    //
    public Position getNextPosition();
    public void setNextPosition(Position p);
    public void setDirection(int direction);
    public int getDirection();
}
