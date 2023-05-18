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