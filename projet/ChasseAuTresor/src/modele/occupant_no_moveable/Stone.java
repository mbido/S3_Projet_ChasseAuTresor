package modele.occupant_no_moveable;

import modele.Occupant;
import modele.Character;
import modele.Position;

public class Stone extends Occupant {
    private Wall wall;

    public Stone(Position position) {
        super(position);
    }

    @Override
    public void process(Character m) {
        Position firstWallPos = wall.getFirst().getPosition();
        Position lastWallPos = wall.getLast().getPosition();
        Position mPosition = m.getPosition();

        boolean isFirstClosest = firstWallPos.distance(mPosition) < lastWallPos.distance(mPosition);

        boolean mGoingUp = m.getDirection() == 1 || m.getDirection() == 2 || m.getDirection() == 3;
        boolean mGoingDown = m.getDirection() == 5 || m.getDirection() == 6 || m.getDirection() == 7;
        boolean mGoingLeft = m.getDirection() == 3 || m.getDirection() == 4 || m.getDirection() == 5;
        boolean mGoingRight = m.getDirection() == 1 || m.getDirection() == 0 || m.getDirection() == 7;

        if (wall.isVertical()) {
            if (isFirstClosest) {
                if (firstWallPos.getRow() == mPosition.getRow()) {
                    if (mGoingLeft) {
                        m.setTempDir(3);
                    } else {
                        m.setTempDir(1);
                    }
                }else if (firstWallPos.getRow() < mPosition.getRow()) {
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
                }else if (lastWallPos.getRow() > mPosition.getRow()) {
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

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    @Override
    public String toString() {
        return "O";
    }

}
