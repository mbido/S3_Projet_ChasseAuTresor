package modele.occupant_no_moveable;

import modele.Moveable;
import modele.Position;
import modele.Hunter;
import modele.Player;

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
        Player p = (Player) m;
        Hunter h = (Hunter) p;
        h.setPickaxe(true);
        setUsability(false);
        throw new UnsupportedOperationException("Unimplemented method 'process'");
    }
}
