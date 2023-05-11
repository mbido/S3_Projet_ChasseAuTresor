package modele.occupant_no_moveable;

import modele.Character;
import modele.Occupant;
import modele.Position;

public class Glue extends Occupant {
    public char symbole = '~';

    public Glue(Position position) {
        super(position);
    }

    @Override
    public void process(Character m ) {
        int waitTime = m.getWaitingTime();
        m.setWaitingTime(waitTime + 1);
    }

}
