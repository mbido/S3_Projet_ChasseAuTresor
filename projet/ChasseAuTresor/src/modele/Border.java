package modele;

public class Border extends Occupant{
    public Border(Position position) {
        super(position);
    }
    public void process(Moveable m) {
        int dir = m.getDirection();
        if(dir % 2 == 0) {
            m.setDirection((dir + 4) % 8);
        }else{
            //TODO
        }
    }
    @Override
    public String toString() {
        return "#";
    }
}
