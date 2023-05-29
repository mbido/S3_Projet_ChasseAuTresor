package modele;

import java.util.Random;

/**
 * Hunter class is a character that can move on the grid and
 * try to find the treasure.
 * It can pick up a pickaxe and a ladder (One of each at a time).
 */
public class Hunter extends Character {
    private boolean pickaxe = false;
    private boolean ladder = false;

    /**
     * Create a hunter with a position and a direction
     *
     * @param name the name of the hunter
     * @param position the position of the hunter
     * @param direction the direction of the hunter
     */
    public Hunter(char name, Position position, int direction) {
        super(name, position, direction);
    }

    /**
     * Create a hunter with a position and a random direction
     *
     * @param name the name of the hunter
     * @param position the position of the hunter
     */
    public Hunter(char name, Position position) {
        super(name, position);
    }

    /**
     * @return true if the hunter has a pickaxe
     */
    public boolean getPickaxe() {
        return pickaxe;
    }

    /**
     * Set the pickaxe of the hunter to true or false
     */
    public void setPickaxe(boolean pickaxe) {
        this.pickaxe = pickaxe;
    }
    /**
     * @return true if the hunter has a ladder
     */
    public boolean getLadder() {
        return ladder;
    }

    /**
     * Set the ladder of the hunter to true or false
     */
    public void setLadder(boolean ladder) {
        this.ladder = ladder;
    }

    /**
     * Process the interaction between the hunter and an occupant
     * it set the direction of the character to a random direction
     *
     * @param m the character to interact with
     */
    @Override
    public void process(Character m) {
        m.setDirection(new Random().nextInt(8));
    }
}
