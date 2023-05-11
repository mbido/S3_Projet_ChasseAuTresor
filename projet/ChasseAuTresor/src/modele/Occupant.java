package modele;

import java.util.Objects;

public abstract class Occupant implements Questionnable{
    private Position position;

    public Occupant(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }
    public int getRow() {
        return position.getRow();
    }
    public int getCol() {
        return position.getCol();
    }
    protected void setPosition(Position position) {
        this.position = position;
    }
    public abstract void process(Character m);

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Occupant)) {
            return false;
        }
        Occupant o = (Occupant) other;
        return Objects.equals(this.position, o.position);
    }
}
