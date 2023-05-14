package modele.occupant_no_moveable;

import modele.Occupant;
import modele.Character;
import modele.Position;

public class Stone extends Occupant {
    private Wall wall;

    public Stone(Position position) {
        super(position);
    }

    @Override
    public void process(Character m) {
        
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    @Override
    public String toString() {
        return "O";
    }

}
