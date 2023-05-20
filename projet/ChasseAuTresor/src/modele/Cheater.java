package modele;


import java.util.Random;

public class Cheater extends Character {
    private final Position treasure; // position du trésor



    public Cheater(Position position, Position treasure) {
        super('!', position);
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
    public int getWorstDirectionToTreasure(Position treasure, Position hunter) {
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

        return (res + 3) % 8;
    }


    @Override
    public void process(Character m) {
        if (m instanceof Hunter) {
            int direction = getWorstDirectionToTreasure(this.treasure, m.getPosition());
            m.setDirection(direction);
        }else{
            m.setDirection(new Random().nextInt(8));
        }
    }
}