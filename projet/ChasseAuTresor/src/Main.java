import modele.*;

import java.io.IOException;
import java.util.*;




public class Main {
    static Grid grid;
    static List<Moveable> listMoveables;

    private static void move(int i) {
        Position pos = listMoveables.get(i).getNextPosition();
        List<Occupant> occupants = grid.get(pos);
        if (occupants != null){
            // 3 cas :
            // 1 -> un seul element
            if (occupants.size() == 1) {
                occupants.get(0).process(listMoveables.get(i));
            }else if(occupants.size() > 1) {
                // si c'est une stone on process avec
                // sinon on process le dernier element
                if (occupants.get(occupants.size() - 1) instanceof Stone) {
                    occupants.get(occupants.size() - 1).process(listMoveables.get(i));
                } else {
                    occupants.get(0).process(listMoveables.get(i));
                }
            }else{ // la liste est vide
                grid.remove(((Occupant) listMoveables.get(i)).getPosition(), (Occupant) listMoveables.get(i));
                grid.add(pos, (Occupant) listMoveables.get(i));
                listMoveables.get(i).setNextPosition(pos);
            }
        }else {
            grid.remove(((Occupant) listMoveables.get(i)).getPosition(), (Occupant) listMoveables.get(i));
            grid.add(pos, (Occupant) listMoveables.get(i));
            listMoveables.get(i).setNextPosition(pos);
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        grid = new Grid();
        listMoveables = new ArrayList<>();
        // occupants
        Hunter hunter = new Hunter('H', new Position(10, 30, 5, 2), 0);
        Hunter hunter2 = new Hunter('D', new Position(10, 30, 5, 28), 4);

        // adding them to the list
        listMoveables.add(hunter);
        listMoveables.add(hunter2);

        // adding them to the grid
        grid.add(hunter.getPosition(), hunter);
        grid.add(hunter2.getPosition(), hunter2);

        System.out.println(grid);
        for(int i = 0; i < 20; i++) {
            move(0);
            move(1);
            System.out.println(grid);
        }
    }
}
