package modele.occupant_no_moveable;

import modele.Character;
import modele.Occupant;
import modele.Position;

public class Glue extends Occupant {

    public Glue(Position position) {
        super(position, true);
    }

    @Override
    public void process(Character m ) {
        int waitTime = m.getWaitingTime();
        m.setWaitingTime(waitTime + 1);
    }
    @Override
    public String toString() {
        return String.valueOf('~');
    }
}
