package modele.occupant_no_moveable;

import modele.Position;
import modele.Hunter;
import modele.Character;

public class Ladder extends Tool {
    private boolean isUsable = true;

    public Ladder(Position position) {
        super(position);
    }

    public void setUsability(boolean usability) {
        this.isUsable = usability;
    }

    @Override
    public void process(Character m) {
        if (m instanceof Hunter) {
            Hunter h = (Hunter) m;
            if (isUsable && !h.getLadder()) {
                h.setLadder(true);
                setUsability(false);
            }
        }
    }

    @Override
    public String toString() {
        return (isUsable)? String.valueOf('/') : " ";
    }
}
