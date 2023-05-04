package modele.occupant_no_moveable;

import modele.Moveable;
import modele.Position;

public class Ladder extends Tool {
    private boolean usability = true;
    public char symbole = '/';

    public Ladder(Position position) {
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'process'");
    }
}
