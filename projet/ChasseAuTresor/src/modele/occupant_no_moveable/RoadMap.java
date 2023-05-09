package modele.occupant_no_moveable;

import modele.Moveable;
import modele.Occupant;
import modele.Player;
import modele.Position;


public class RoadMap extends Occupant{
    public char symbole = '?';
    public Position treasure;

    public RoadMap(Position position, Position treasure) {
        super(position);
        this.treasure = treasure;

    }

    // les direction possible
    // 4 3 2
    // 5 0 1
    // 6 7 8
    /**
     * @param treasure
     * @return la direction vers le trésor
     */
    public int getDirectionToTreasure(Position treasure) {
        int row = treasure.getRow();
        int col = treasure.getCol();
        int rowPlayer = this.getRow();
        int colPlayer = this.getCol();
        if (rowPlayer == row && colPlayer == col) {
            return 0;
        }
        if (rowPlayer == row && colPlayer < col) {
            return 1;
        }
        if (rowPlayer == row && colPlayer > col) {
            return 5;
        }
        if (rowPlayer < row && colPlayer == col) {
            return 3;
        }
        if (rowPlayer > row && colPlayer == col) {
            return 7;
        }
        if (rowPlayer < row && colPlayer < col) {
            return 2;
        }
        if (rowPlayer < row && colPlayer > col) {
            return 4;
        }
        if (rowPlayer > row && colPlayer < col) {
            return 8;
        }
        if (rowPlayer > row && colPlayer > col) {
            return 6;
        }
        return 0;
    }

    @Override
    // redirige le joueur vers le trésor
    public void process(Moveable m) {
        Player p = (Player) m;
        int direction = this.getDirectionToTreasure(this.treasure);
        p.setDirection(direction);
    }
}
