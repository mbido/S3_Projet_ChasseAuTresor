package modele.occupant_no_moveable;

import modele.Position;
import modele.Hunter;
import modele.Character;

public class Ladder extends Tool {
    private boolean usability = true;
    public char symbole = '/';

    public Ladder(Position position) {
        super(position);
    }

    public boolean isUsable() {
        return usability;
    }

    public void setUsability(boolean usability) {
        this.usability = usability;
    }

    @Override
    public void process(Character m) {
        if (m instanceof Hunter) {
            Hunter h = (Hunter) m;
            if (this.isUsable() == true && h.getLadder() == false) {
                h.setLadder(true);
                setUsability(false);
            }
        }
    }
}
