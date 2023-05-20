package modele;

import java.util.Random;

/**
 * The WiseMan class is a character that guides the hunter to the treasure
 * by giving him the best direction to the treasure.
 * A WiseMan always knows where the treasure is.
 */
public class WiseMan extends Character {
    private final Position treasure; // position du trésor


    /**
     * Create a WiseMan with a position and a treasure position
     *
     * @param position the position of the WiseMan
     * @param treasure the position of the treasure
     */
    public WiseMan(Position position, Position treasure) {
        super('^', position);
        this.treasure = treasure;
    }

    /** Get the best direction to the treasure. It is used
     * in the process method for hunters to guide them to the treasure.
     *
     * @param treasure the position of the treasure
     * @return the best direction to the treasure
     */
    public int getBestDirectionToTreasure(Position treasure, Position hunter) {
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

    /**
     * Process the interaction between the WiseMan and a character.
     * If the character is a hunter, the WiseMan will guide him to the treasure.
     * Otherwise, the WiseMan will give a random direction to the character.
     *
     * @param m the character to interact with
     */
    @Override
    public void process(Character m) {
        if (m instanceof Hunter) {
            int direction = getBestDirectionToTreasure(this.treasure, m.getPosition());
            m.setDirection(direction);
        }else{
            m.setDirection(new Random().nextInt(8));
        }
    }
}