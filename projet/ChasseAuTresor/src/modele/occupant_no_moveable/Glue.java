package modele.occupant_no_moveable;

import modele.Moveable;
import modele.Player;
import modele.Occupant;
import modele.Position;

public class Glue extends Occupant {
    public char symbole = '~';

    public Glue(Position position) {
        super(position);
    }

    @Override
    public void process(Moveable m ) {
        Player p = (Player) m;
        int waitTime = p.getWaitingTime();
        p.setWaitingTime(waitTime + 1);
    }

}
