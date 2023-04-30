package modele;

public interface Moveable {
    public Position getNextPosition();
    public void setNextPosition(Position p);
    public void setDirection(int direction);
    public int getDirection();
}
