package modele.occupant_no_moveable;

import modele.Occupant;
import modele.Character;
import modele.Position;

public class Stone extends Occupant {

    public Stone(Position position) {
        super(position);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void process(Character m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return "O";
    }

}
