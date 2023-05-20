package modele.occupant_no_moveable;

import modele.Character;
import modele.Occupant;
import modele.Position;

/**
 * Class Glue representing a puddle of glue
 * When a character is on a puddle of glue, it waits 2 turns before moving
 */
public class Glue extends Occupant {

    /**
     * Create a puddle of glue at the given position
     *
     * @param position the position of the glue
     */
    public Glue(Position position) {
        super(position, true);
    }

    /**
     * Process the interaction between the glue and a character
     *
     * @param m the character on the glue
     */
    @Override
    public void process(Character m ) {
        int waitTime = m.getWaitingTime();
        m.setWaitingTime(waitTime + 2);
    }

    /**
     * Gives the string representation of the glue
     *
     * @return the string representation of the glue : '~'
     */
    @Override
    public String toString() {
        return String.valueOf('~');
    }
}
