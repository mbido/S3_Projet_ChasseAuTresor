package modele.occupant_no_moveable;

import modele.Occupant;
import modele.Position;
import modele.Character;
import modele.Hunter;

/**
 * Treasure is a class that represents the treasure in the game
 * and the goal of the game is to find that treasure.
 */
public class Treasure extends Occupant {

    /**
     * Creates a new treasure at the given position.
     */
    public Treasure(Position position) {
        super(position, true);
    }

    /**
     * This method is used to process the treasure.
     * It does nothing because the end of the game is checked
     * in the controller.
     *
     * @param m the character that is on the treasure.
     */
    @Override
    public void process(Character m) {
    }

    /**
     * This method is used to display the treasure.
     *
     * @return the string representation of the treasure : '$'.
     */
    @Override
    public String toString() {
        return String.valueOf('$');
    }
}
