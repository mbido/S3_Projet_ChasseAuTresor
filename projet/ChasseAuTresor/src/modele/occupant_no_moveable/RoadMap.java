package modele.occupant_no_moveable;

import modele.Moveable;
import modele.Occupant;
import modele.Position;


public class RoadMap extends Occupant{
    public char symbole = '?';

    public RoadMap(Position position) {
        super(position);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void process(Moveable m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'process'");
    }
    
}
