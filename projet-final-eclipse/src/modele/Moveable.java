package modele;

/**
 * Interface for all the moveable objects
 */
public interface Moveable {
    /**
     * Get the next position of the object
     *
     * @return the next position of the object
     */
    public Position getNextPosition();

    /**
     * Set the next position of the object
     *
     * @param p the new next position of the object
     */
    public void setNextPosition(Position p);

    /**
     * Set the direction of the object
     */
    public void setDirection(int direction);

    /**
     * Get the direction of the object
     *
     * @return the direction of the object
     */
    public int getDirection();
}
