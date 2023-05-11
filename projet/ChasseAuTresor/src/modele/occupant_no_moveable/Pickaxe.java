package modele.occupant_no_moveable;

import modele.Moveable;
import modele.Position;
import modele.Hunter;
import modele.Character;

public class Pickaxe extends Tool{
    private boolean usability = true;
    // public char symbole = '⛏';
    public char symbole = '7';

    public Pickaxe(Position position) {
        super(position);
    }
    

    public boolean isUsable() {
        return usability;
    }

    public void setUsability(boolean usability) {
        this.usability = usability;
    }


    @Override
    public void process(Moveable m) {
        if (m instanceof Hunter) {
            Character p = (Character) m;
            Hunter h = (Hunter) p;
            if (this.usability == true) {
                h.setPickaxe(true);
                setUsability(false);
            }
        }
    }
}
