package modele;

/**
 * A Border is an Occupant that can be found all around the grid.
 * It makes the Character bounce when it hits it.
 */
public class Border extends Occupant{
    /**
     * Creates a new Border at the given Position.
     *
     * @param position The Position of the new Border.
     */
    public Border(Position position) {
        super(position, false);
    }

    /**
     * Makes the given Character bounce when it hits the Border.
     * if the Character is going in a diagonal direction, it will bounce in the opposite diagonal direction.
     * that direction is determined by a logical formula to avoid the repetition of code.
     *
     * @param m The Character that hits the Border.
     */
    public void process(Character m) {
        int dir = m.getDirection();
        if(dir % 2 == 0) {
            m.setDirection((dir + 4) % 8);
        }else{
            int f = (this.getRow() == 0 || this.getRow() == this.getPosition().getMaxRow() - 1) ? 1 : 0;
            int newDir = Math.floorMod(dir + (4 * (1 - Math.abs(f - ((Math.floorMod(dir, 4) - 1) / 2))) - 2), 8);
            m.setDirection(newDir);
        }
    }

    /**
     * Get the String representation of a Border.
     *
     * @return The String representation of a Border : '#'.
     */
    @Override
    public String toString() {
        return "#";
    }
}
