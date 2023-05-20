package modele.occupant_no_moveable;

import java.util.LinkedList;

/**
 * A wall is a linked list of stones.
 * Liked list is used to make easier getting the first and the last stone of the wall.
 * A wall has to be vertical or horizontal.
 * A wall has to be built from left to right or from top to bottom.
 */
public class Wall {
    private LinkedList<Stone> stones;

    /**
     * Create a wall.
     */
    public Wall() {
        stones = new LinkedList<Stone>();
    }

    /**
     * Add a stone to the wall.
     * A wall has to be vertical or horizontal.
     * A wall has to be built from left to right or from top to bottom.
     *
     * @param stone the stone to add at the end of the wall.
     */
    public void addStone(Stone stone) {
        stones.add(stone);
        stone.setWall(this);
    }

    /**
     * Get the first stone of the wall.
     *
     * @return the first stone of the wall.
     */
    public Stone getFirst() {
        return stones.getFirst();
    }

    /**
     * Get the last stone of the wall.
     *
     * @return the last stone of the wall.
     */
    public Stone getLast() {
        return stones.getLast();
    }

    /**
     * Check if the wall is vertical or horizontal.
     *
     * @return true if the wall is vertical, false otherwise.
     */
    public boolean isVertical(){
        return stones.getFirst().getPosition().getCol() == stones.getLast().getPosition().getCol();
    }

    /**
     * Get the size of the wall.
     *
     * @return the size of the wall.
     */
    public int size() {
        return stones.size();
    }
}
