package modele.occupant_no_moveable;

import modele.Position;
import modele.Hunter;
import modele.Character;

/**
 * Ladder is a tool that can be used by Hunter to climb up walls
 * It can be used only once and by only one Hunter.
 */
public class Ladder extends Tool {
    private boolean isUsable;

    /**
     * Create a Ladder on a given position
     *
     * @param position the position of the Ladder
     */
    public Ladder(Position position) {
        super(position);
        isUsable = true;
    }

    /**
     * Process the interaction between a Chharacter and the Ladder
     * If the Character is a Hunter and the Ladder is usable, the Hunter picks up the Ladder
     * and the Ladder is no longer usable
     *
     * @param m the Hunter that is on the Ladder
     */
    @Override
    public void process(Character m) {
        if (m instanceof Hunter) {
            Hunter h = (Hunter) m;
            if (isUsable && !h.getLadder()) {
                h.setLadder(true);
                isUsable = false;
            }
        }
    }

    /**
     * Return the String representation of the Ladder
     * if the Ladder is usable, a space otherwise
     *
     * @return the String representation of the Ladder '/' or ' ' if not usable
     */
    @Override
    public String toString() {
        return (isUsable)? String.valueOf('/') : " ";
    }
}
