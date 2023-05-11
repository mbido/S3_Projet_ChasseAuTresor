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
        int row = this.getRow();
        int col = this.getCol();
        int rowTreasure = treasure.getRow();
        int colTreasure = treasure.getCol();
        int direction = -1;
        if (rowTreasure < row) {
            if (colTreasure < col) {
                direction = 3;
            } else if (colTreasure > col) {
                direction = 1;
            } else {
                direction = 2;
            }
        } else if (rowTreasure > row) {
            if (colTreasure < col) {
                direction = 5;
            } else if (colTreasure > col) {
                direction = 7;
            } else {
                direction = 6;
            }
        } else {
            if (colTreasure < col) {
                direction = 4;
            } else if (colTreasure > col) {
                direction = 0;
            }
        }
        return direction;
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
