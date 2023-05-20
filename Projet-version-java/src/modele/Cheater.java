package modele;


import java.util.Random;

/**
 * A character that cheat the hunters by giving them the worst direction to the treasure
 * witch is the opposite of the best direction minus 1 mod 8 to make sure that the hunter
 * does not go back to the treasure after hitting a border
 */
public class Cheater extends Character {
    private final Position treasure; // position du trésor


    /**
     * Create a cheater with a position and a treasure position
     *
     * @param position the position of the cheater
     * @param treasure the position of the treasure
     */
    public Cheater(Position position, Position treasure) {
        super('!', position);
        this.treasure = treasure;
    }

    /**Get the worst direction to the treasure. It is used
     * in the process method for hunters to guide them at the opposite of the best direction
     *
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

    /**
     * Process the interaction between the cheater and a character
     * If the character is a hunter, it will give him the worst direction to the treasure
     * Otherwise, it will give him a random direction
     *
     * @param m the character to process
     */
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