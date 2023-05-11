package modele.occupant_no_moveable;

import modele.Occupant;
import modele.Position;
import modele.Character;
import modele.Hunter;

public class Treasure extends Occupant {
    public char symbole = '$';
    private boolean win = false;

    public Treasure(Position position) {
        super(position);
    }

    public char getSymbole() {
        return symbole;
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
            if (h.getLadder() == true && h.getPickaxe() == true) {
                setWin(true);
            }
        }
    }

}
