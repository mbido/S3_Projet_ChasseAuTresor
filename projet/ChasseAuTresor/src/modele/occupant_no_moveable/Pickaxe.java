package modele.occupant_no_moveable;

import modele.Position;
import modele.Hunter;
import modele.Character;

/**
 * Pickaxe is a tool that can be used by the hunter to break a Stone of a Wall.
 * It can be used only once and by only one hunter.
 */
public class Pickaxe extends Tool {
    private boolean isUsable = true;

    /**
     * Constructor of Pickaxe.
     *
     * @param position the position of the pickaxe.
     */
    public Pickaxe(Position position) {
        super(position);
    }

    /**
     * Set the usability of the pickaxe.
     *
     * @param usability the usability of the pickaxe.
     */
    public void setUsability(boolean usability) {
        this.isUsable = usability;
    }

    /**
     * Process the interaction between the pickaxe and a character.
     * If the character is a hunter and the pickaxe is usable, the hunter
     * picks up the pickaxe.
     *
     * @param m the character that picks up the pickaxe.
     */
    @Override
    public void process(Character m) {
        if (m instanceof Hunter) {
            Hunter h = (Hunter) m;
            if (isUsable && !h.getPickaxe()) {
                h.setPickaxe(true);
                setUsability(false);
            }
        }
    }

    /**
     * Return the string representation of the pickaxe if
     * the pickaxe is usable, else return a space.
     *
     * @return the string representation of the pickaxe : '7'
     */
    @Override
    public String toString() {
        return (isUsable)? String.valueOf('7') : " ";
    }
}
