package modele.occupant_no_moveable;


import modele.Occupant;
import modele.Character;
import modele.Hunter;
import modele.Position;


public class RoadMap extends Occupant{
    public char symbole = '?';
    public Position treasure;

    public RoadMap(Position position, Position treasure) {
        super(position);
        this.treasure = treasure;

    }

    // les direction possible
    // 3 2 1
    // 4 X 0
    // 5 6 7
    /**
     * @param treasure the position of the trasure row and col
     * @return the best direction to the treasure
     */
    public int getBestDirectionToTreasure(Position treasure) {
        Position vector = this.getPosition().getVector(treasure);
        int rowVector = vector.getRow();
        int colVector = vector.getCol();
        double angle = Math.atan2(rowVector, colVector);

        if (angle < 0) {
            angle += 2 * Math.PI;
        }


        if (angle >= 0 && angle < Math.PI / 8) {
            return 0;
        } else if (angle >= Math.PI / 8 && angle < 3 * Math.PI / 8) {
            return 1;
        } else if (angle >= 3 * Math.PI / 8 && angle < 5 * Math.PI / 8) {
            return 2;
        } else if (angle >= 5 * Math.PI / 8 && angle < 7 * Math.PI / 8) {
            return 3;
        } else if (angle >= 7 * Math.PI / 8 && angle < 9 * Math.PI / 8) {
            return 4;
        } else if (angle >= 9 * Math.PI / 8 && angle < 11 * Math.PI / 8) {
            return 5;
        } else if (angle >= 11 * Math.PI / 8 && angle < 13 * Math.PI / 8) {
            return 6;
        } else if (angle >= 13 * Math.PI / 8 && angle < 15 * Math.PI / 8) {
            return 7;
        } else {
            return 0;
        }


        

    @Override
    // redirige le joueur vers le trésor
    public void process(Character m) {
        if (m instanceof Character) {
            Character p = (Character) m;
            Hunter h = (Hunter) p;
            int direction = this.getBestDirectionToTreasure(this.treasure);
           h.setDirection(direction);
        }
    }
}
