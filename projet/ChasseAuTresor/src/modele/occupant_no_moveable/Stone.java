package modele.occupant_no_moveable;

import modele.Hunter;
import modele.Occupant;
import modele.Character;
import modele.Position;

public class Stone extends Occupant {
    private Wall wall;

    private int brokenTime;

    public Stone(Position position) {
        super(position, false);
        this.brokenTime = 0;
    }

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

    public boolean isStoneBroken(){return this.brokenTime > 0;}

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    @Override
    public String toString() {
        if(this.brokenTime > 0){
            --brokenTime;
            return " ";
        }
        return "#";
    }

}
