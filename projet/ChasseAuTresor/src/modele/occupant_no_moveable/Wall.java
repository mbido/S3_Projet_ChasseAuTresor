package modele.occupant_no_moveable;

import java.util.LinkedList;

public class Wall {
    private LinkedList<Stone> stones;

    public Wall() {
        stones = new LinkedList<Stone>();
    }

    /**
     * Add a stone to the wall.
     * A wall has to be vertical or horizontal.
     * A wall has to be built from left to right or from top to bottom.
     * @param stone the stone to add at the end of the wall.
     */
    public void addStone(Stone stone) {
        stones.add(stone);
        stone.setWall(this);
    }

    public void removeStone(Stone stone) {
        stones.remove(stone);
    }

    public Stone getFirst() {
        return stones.getFirst();
    }

    public Stone getLast() {
        return stones.getLast();
    }

    public boolean isVertical(){
        return stones.getFirst().getPosition().getCol() == stones.getLast().getPosition().getCol();
    }
}
