package modele;

// import java.util.Objects;

/**
 * An occupant is an object that can be placed on the grid
 */
public abstract class Occupant implements Questionable{
    private Position position;
    private final boolean isStepEnabled;

    /* Create an occupant with a position and a step ability
     *
     * @param position the position of the occupant
     * @param isStepEnabled true if the occupant can be stepped on
     */
    public Occupant(Position position, boolean isStepEnabled) {

        this.position = position;
        this.isStepEnabled = isStepEnabled;
    }

    /* Give the position of the occupant
     *
     * @return the current position of the occupant
     */
    public Position getPosition() {
        return position;
    }

    /* Give the row of the occupant 0 being the top
     *
     * @return the current row of the occupant
     */
    public int getRow() {
        return position.getRow();
    }

    /* Give the column of the occupant 0 being the left
     *
     * @return the current column of the occupant
     */
    public int getCol() {
        return position.getCol();
    }

    /* Set the position of the occupant
     *
     * @param position the new position of the occupant
     */
    protected void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Interact with the given character mostly used to change its direction
     *
     * @param m the character to interact with
     */
    public abstract void process(Character m);

    /**
     * Check if the occupant can be stepped on
     *
     * @return true if the occupant can be stepped on
     */
    public boolean isStepEnabled() {
        return isStepEnabled;
    }

    /**
     * Check if the occupant is equal to another object
     *
     * @param other the object to compare to
     * @return true if the occupant is equal to the other object
     */
    @Override
    public boolean equals(Object other) {
        return this == other;
    }

    /**
     * Get the string representation of the occupant
     *
     * @return the string representation of the occupant to be displayed
     */
    @Override
    abstract public String toString();
}
