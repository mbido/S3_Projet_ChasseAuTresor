import modele.*;

import java.io.IOException;
import java.util.*;




public class Main {
    static Grid grid;
    static List<Moveable> listMoveables;

    private static void move(int i) {
        Position pos = listMoveables.get(i).getNextPosition();
        //TODO : verification validite
        grid.remove(((Occupant) listMoveables.get(i)).getPosition(), (Occupant) listMoveables.get(i));
        grid.add(pos, (Occupant) listMoveables.get(i));
        listMoveables.get(i).setNextPosition(pos);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        grid = new Grid();
        listMoveables = new ArrayList<>();
        // occupants
        Hunter hunter = new Hunter('H', new Position(10, 30, 5, 2), 0);
        Hunter hunter2 = new Hunter('D', new Position(10, 30, 5, 10), 4);

        // adding them to the list
        listMoveables.add(hunter);
        listMoveables.add(hunter2);

        // adding them to the grid
        grid.add(hunter.getPosition(), hunter);
        grid.add(hunter2.getPosition(), hunter2);

        System.out.println(grid);
        for(int i = 0; i < 10; i++) {
            move(0);
            move(1);
            System.out.println(((Occupant) listMoveables.get(0)).getPosition());
            System.out.println(((Occupant) listMoveables.get(1)).getPosition());
            System.out.println(grid);
        }
    }
}
