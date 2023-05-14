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
        Position thisPosition = getPosition();

        boolean isFirstClosest = firstWallPos.distance(thisPosition) < lastWallPos.distance(thisPosition);
        if (wall.isVertical()) {
            if (isFirstClosest) {
                // we go up
                m.setTempDir(2);
            } else {
                // we go down
                m.setTempDir(6);
            }
        } else {
            if (isFirstClosest) {
                // we go left
                m.setTempDir(4);
            } else {
                // we go right
                m.setTempDir(0);
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
