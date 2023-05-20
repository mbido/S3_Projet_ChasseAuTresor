package modele.occupant_no_moveable;


import modele.Occupant;
import modele.Character;
import modele.Hunter;
import modele.Position;

/**
 * RoadMap is a class that represents the road map to the treasure
 * it guides the hunter to the treasure by giving him the best direction to the treasure
 */
public class RoadMap extends Occupant{
    public Position treasure;

    /** Creates a new instance of RoadMap
     * A road map can be walked on
     * A road map always knows the position of the treasure
     *
     * @param position the position of the road map
     * @param treasure the position of the treasure
     */
    public RoadMap(Position position, Position treasure) {
        super(position, true);
        this.treasure = treasure;

    }


    /** Get the best direction to the treasure. It is used
     * in the process method for hunters to guide them to the treasure
     *
     * @param treasure the position of the treasure row and col
     * @return the best direction to the treasure
     */
    private int getBestDirectionToTreasure(Position treasure, Position hunter) {
        int rowVector = treasure.getRow() - hunter.getRow();
        int colVector = treasure.getCol() - hunter.getCol();
        double angle = Math.atan2(rowVector, colVector);
        int res;
        if (angle >= -Math.PI / 8 && angle < Math.PI / 8) {
            res = 0;
        } else if (angle >= Math.PI / 8 && angle < 3 * Math.PI / 8) {
            res = 7;
        } else if (angle >= 3 * Math.PI / 8 && angle < 5 * Math.PI / 8) {
            res = 6;
        } else if (angle >= 5 * Math.PI / 8 && angle < 7 * Math.PI / 8) {
            res = 5;
        } else if (angle <= -Math.PI / 8 && angle > -3 * Math.PI / 8) {
            res = 1;
        } else if (angle <= -3 * Math.PI / 8 && angle > -5 * Math.PI / 8) {
            res = 2;
        } else if (angle <= -5 * Math.PI / 8 && angle > -7 * Math.PI / 8) {
            res = 3;
        } else {
            res = 4;
        }
        return res;
    }

        
    /** Process the hunter
     * It changes the hunter's direction to the best direction to the treasure
     *
     * @param m the hunter
     */
    @Override
    public void process(Character m) {
        if (m instanceof Hunter) {
            int direction = this.getBestDirectionToTreasure(this.treasure, m.getPosition());
            m.setDirection(direction);
        }
    }

    /** Get the string representation of the road map
     *
     * @return the string representation of the road map : '?'
     */
    @Override
    public String toString() {
        return String.valueOf('?');
    }
}
