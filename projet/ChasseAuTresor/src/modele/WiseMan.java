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
    public void process(Moveable m) {
        Character p = (Character) m;
        Hunter h = (Hunter) p;
        int direction = getDirectionToTreasure(this.treasure);
        h.setDirection(direction);
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