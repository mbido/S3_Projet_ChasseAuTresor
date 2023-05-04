package modele.occupant_no_moveable;

import modele.Position;

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
}
