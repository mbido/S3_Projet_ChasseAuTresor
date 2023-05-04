package modele;

public class Stone extends Occupant{

    public Stone(Position position) {
        super(position);
    }

    @Override
    public void process(Moveable m) {
        // TODO
    }

    @Override
    public String toString() {
        return "O";
    }
}
