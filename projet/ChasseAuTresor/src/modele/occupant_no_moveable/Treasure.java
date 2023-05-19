package modele.occupant_no_moveable;

import modele.Occupant;
import modele.Position;
import modele.Character;
import modele.Hunter;

public class Treasure extends Occupant {
    private boolean win = false;

    public Treasure(Position position) {
        super(position, true);
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    @Override
    public void process(Character m) {
        if (m instanceof Hunter) {
            Hunter h = (Hunter) m;
            if (h.getLadder() && h.getPickaxe()) {
                setWin(true);
            }
        }
    }

    @Override
    public String toString() {
        return String.valueOf('$');
    }
}
