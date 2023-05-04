package modele.occupant_no_moveable;

import modele.Position;

public class Pickaxe extends Tool{
    private boolean usability = true;

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
