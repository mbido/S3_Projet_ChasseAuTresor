package modele;

public class Border extends Occupant{
    public Border(Position position) {
        super(position);
    }
    public void process(Moveable m) {
        // TODO
    }
    @Override
    public String toString() {
        return "#";
    }
}
