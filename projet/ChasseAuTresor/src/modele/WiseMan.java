package modele;

public class WiseMan extends Character {
    private Position position; // position du sage
    private int direction; // direction du sage
    private final char symbole = '^'; // symbole du sage
    private Position treasure; // position du trésor



    public WiseMan(char name, Position position, Position treasure) {
        super(name, position);
        this.position = position;
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
    public int getBestDirectionToTreasure(Position treasure) {
        int row = this.getRow();
        int col = this.getCol();
        int rowTreasure = treasure.getRow();
        int colTreasure = treasure.getCol();
        int direction = 0;
        if (rowTreasure < row) {
            if (colTreasure < col) {
                direction = 4;
            } else if (colTreasure > col) {
                direction = 2;
            } else {
                direction = 3;
            }
        } else if (rowTreasure > row) {
            if (colTreasure < col) {
                direction = 6;
            } else if (colTreasure > col) {
                direction = 8;
            } else {
                direction = 7;
            }
        } else {
            if (colTreasure < col) {
                direction = 5;
            } else if (colTreasure > col) {
                direction = 1;
            } else {
                direction = 0;
            }
        }
        return direction;
    }






    @Override
    public void process(Character m) {
        if (m instanceof Hunter) {
            int direction = getBestDirectionToTreasure(this.treasure);
            m.setDirection(direction);
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public char getSymbole() {
        return symbole;
    }
}