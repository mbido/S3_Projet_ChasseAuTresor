package modele;

public class Border extends Occupant{
    public Border(Position position) {
        super(position);
    }
    public void process(Character m) {
        int dir = m.getDirection();
        if(dir % 2 == 0) {
            m.setDirection((dir + 4) % 8);
        }else{
            int f = (this.getRow() == 0 || this.getRow() == this.getPosition().getMaxRow() - 1) ? 1 : 0;
            int newDir = Math.floorMod(dir + (4 * (1 - Math.abs(f - ((Math.floorMod(dir, 4) - 1) / 2))) - 2), 8);
            m.setDirection(newDir);
        }
    }
    @Override
    public String toString() {
        return "#";
    }
}
