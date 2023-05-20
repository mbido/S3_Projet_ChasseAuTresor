package modele.occupant_no_moveable;

import modele.Hunter;
import modele.Occupant;
import modele.Character;
import modele.Position;

/**
 * Stone class that makes up the walls
 */
public class Stone extends Occupant {
    private Wall wall;

    private int brokenTime;

    /**
     * Constructor of the class Stone
     * A stone is cannot be walked on
     *
     * @param position the position of the stone
     */
    public Stone(Position position) {
        super(position, false);
        this.brokenTime = 0;
    }

    /**
     * Process the interaction between a character and the stone
     * It breaks the stone for a short period of time if the character
     * has a pickaxe (only hunters can have a pickaxe)
     * Otherwise, it will make the character go to the closest side of the wall
     * using the tempDir attribute of the character
     *
     * @param m the character to interact with
     */
    @Override
    public void process(Character m) {
        if (m instanceof Hunter && ((Hunter) m).getPickaxe()) {
            this.brokenTime = 2;
            ((Hunter)m).setPickaxe(false);
            return;
        }

        Position firstWallPos = wall.getFirst().getPosition();
        Position lastWallPos = wall.getLast().getPosition();

        // on donne la position de la premiere et de la derniere pierre + la taille du mur
        Position mPosition = m.getPosition();

        boolean isFirstClosest = firstWallPos.distance(mPosition) < lastWallPos.distance(mPosition);

        boolean mGoingUp = m.getDirection() == 1 || m.getDirection() == 2 || m.getDirection() == 3;
        boolean mGoingLeft = m.getDirection() == 3 || m.getDirection() == 4 || m.getDirection() == 5;

        if (wall.isVertical()) {
            if (isFirstClosest) {
                if (firstWallPos.getRow() == mPosition.getRow()) {
                    if (mGoingLeft) {
                        m.setTempDir(3);
                    } else {
                        m.setTempDir(1);
                    }
                }else if (firstWallPos.getRow() > mPosition.getRow()) {
                    if (mGoingLeft) {
                        m.setTempDir(4);
                    } else {
                        m.setTempDir(0);
                    }
                }else{
                    m.setTempDir(2);
                }
            } else {
                if (lastWallPos.getRow() == mPosition.getRow()) {
                    if (mGoingLeft) {
                        m.setTempDir(5);
                    } else {
                        m.setTempDir(7);
                    }
                }else if (lastWallPos.getRow() < mPosition.getRow()) {
                    if (mGoingLeft) {
                        m.setTempDir(4);
                    } else {
                        m.setTempDir(0);
                    }
                }else{
                    m.setTempDir(6);
                }
            }
        } else {
            if (isFirstClosest) {
                if (firstWallPos.getCol() == mPosition.getCol()) {
                    if (mGoingUp) {
                        m.setTempDir(3);
                    } else {
                        m.setTempDir(5);
                    }
                }else if (firstWallPos.getCol() > mPosition.getCol()) {
                    if (mGoingUp) {
                        m.setTempDir(2);
                    } else {
                        m.setTempDir(6);
                    }
                }else{
                    m.setTempDir(4);
                }
            } else {
                if (lastWallPos.getCol() == mPosition.getCol()) {
                    if (mGoingUp) {
                        m.setTempDir(1);
                    } else {
                        m.setTempDir(7);
                    }
                }else if (lastWallPos.getCol() < mPosition.getCol()) {
                    if (mGoingUp) {
                        m.setTempDir(2);
                    } else {
                        m.setTempDir(6);
                    }
                }else{
                    m.setTempDir(0);
                }
            }
        }

    }

    /**
     * Check if the stone is broken
     *
     * @return true if the stone is broken, false otherwise
     */
    public boolean isStoneBroken(){return this.brokenTime > 0;}

    /**
     * Set the wall of the stone this function is called by the wall and only by the wall
     *
     * @param wall the wall to set
     */
    public void setWall(Wall wall) {
        this.wall = wall;
    }

    /**
     * Return the string representation of the stone
     * If the stone is broken, it will return " " for a short period of time and decrease the brokenTime attribute
     *
     * @return the string representation of the stone : "#" if the stone is not broken, " " otherwise
     */
    @Override
    public String toString() {
        if(this.brokenTime > 0){
            --brokenTime;
            return " ";
        }
        return "#";
    }

}
