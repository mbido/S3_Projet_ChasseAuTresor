import modele.*;
import modele.occupant_no_moveable.*;
import modele.Character;

import java.io.IOException;
import java.util.*;




public class Main {
    static Grid grid;
    static List<Character> listCharacter;

    private static void move(int i) {
        Character p = (Character) listCharacter.get(i);
        if(p.getWaitingTime() != 0) {
            p.setWaitingTime(p.getWaitingTime() - 1);
        }
        Position pos = p.getNextPosition();
        List<Occupant> occupants = grid.get(pos);
        if (occupants != null){
            // 3 cas :
            // 1 -> un seul element
            if (occupants.size() == 1) {
                occupants.get(0).process(listCharacter.get(i));
            }else if(occupants.size() > 1) {
                // si c'est une stone on process avec
                // sinon on process le dernier element
                if (occupants.get(occupants.size() - 1) instanceof Stone) {
                    occupants.get(occupants.size() - 1).process(listCharacter.get(i));
                } else {
                    occupants.get(0).process(listCharacter.get(i));
                }
            }else{ // la liste est vide
                grid.remove(((Occupant) listCharacter.get(i)).getPosition(), (Occupant) listCharacter.get(i));
                grid.add(pos, (Occupant) listCharacter.get(i));
                listCharacter.get(i).setNextPosition(pos);
            }
        }else {
            grid.remove(((Occupant) listCharacter.get(i)).getPosition(), (Occupant) listCharacter.get(i));
            grid.add(pos, (Occupant) listCharacter.get(i));
            listCharacter.get(i).setNextPosition(pos);
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        grid = new Grid(10, 30);
        listCharacter = new ArrayList<>();
        // occupants
        Hunter hunter = new Hunter('H', new Position(10, 30, 5, 2), 1);
        //Hunter hunter2 = new Hunter('D', new Position(10, 30, 5, 28), 4);

        // adding them to the list
        listCharacter.add(hunter);
        //listMoveables.add(hunter2);

        // adding them to the grid
        grid.add(hunter.getPosition(), hunter);
        //grid.add(hunter2.getPosition(), hunter2);

        System.out.println(grid);
        for(int i = 0; i < 50; i++) {
            move(0);
            //move(1);
            System.out.println(grid);
        }
    }
}
