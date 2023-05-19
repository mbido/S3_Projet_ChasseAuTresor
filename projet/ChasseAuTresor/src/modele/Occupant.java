package modele;

import java.util.Objects;

public abstract class Occupant implements Questionnable{
    private Position position;
    private final boolean isStepEnabled;

    public Occupant(Position position, boolean isStepEnabled) {

        this.position = position;
        this.isStepEnabled = isStepEnabled;
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

    public boolean isStepEnabled() {
        return isStepEnabled;
    }

    @Override
    public boolean equals(Object other) {
        return this == other;
    }

    @Override
    abstract public String toString();
}
