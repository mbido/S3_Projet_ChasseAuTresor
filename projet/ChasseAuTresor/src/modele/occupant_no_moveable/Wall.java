package modele.occupant_no_moveable;

import java.util.LinkedList;

public class Wall {
    private LinkedList<Stone> stones;

    public Wall() {
        stones = new LinkedList<Stone>();
    }

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
}
