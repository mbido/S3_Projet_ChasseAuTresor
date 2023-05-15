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
                if(occupants.get(0) instanceof Stone && ((Stone) occupants.get(0)).isStoneBroken()){
                    grid.remove(((Occupant) listCharacter.get(i)).getPosition(), (Occupant) listCharacter.get(i));
                    grid.add(pos, (Occupant) listCharacter.get(i));
                    listCharacter.get(i).setNextPosition(pos);
                    return;
                }
                occupants.get(0).process(listCharacter.get(i));
            }else if(occupants.size() > 1) {
                // si c'est une stone on process avec
                // sinon on process le dernier element
                if (occupants.get(occupants.size() - 1) instanceof Stone) {
                    if(((Stone) occupants.get(0)).isStoneBroken()){
                        grid.remove(((Occupant) listCharacter.get(i)).getPosition(), (Occupant) listCharacter.get(i));
                        grid.add(pos, (Occupant) listCharacter.get(i));
                        listCharacter.get(i).setNextPosition(pos);
                        return;
                    }
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
        final int MAX_ROW = 10;
        final int MAX_COL = 30;
        grid = new Grid(MAX_ROW, MAX_COL);
        listCharacter = new ArrayList<>();
        // occupants
        // - Characters
        Hunter hunter = new Hunter('H', new Position(MAX_ROW, MAX_COL, 5, 2), 1);

        // - Mur
        Wall wall = new Wall();
        Stone stone0 = new Stone(new Position(MAX_ROW, MAX_COL, 3, 2));
        Stone stone1 = new Stone(new Position(MAX_ROW, MAX_COL, 3, 3));
        Stone stone2 = new Stone(new Position(MAX_ROW, MAX_COL, 3, 4));
        Stone stone3 = new Stone(new Position(MAX_ROW, MAX_COL, 3, 5));
        Stone stone4 = new Stone(new Position(MAX_ROW, MAX_COL, 3, 6));

        wall.addStone(stone0);
        wall.addStone(stone1);
        wall.addStone(stone2);
        wall.addStone(stone3);
        wall.addStone(stone4);

        //Hunter hunter2 = new Hunter('D', new Position(10, 30, 5, 28), 4);

        // adding them to the list
        listCharacter.add(hunter);
        //listMoveables.add(hunter2);

        // adding them to the grid
        grid.add(hunter.getPosition(), hunter);

        // -Wall
        grid.add(stone0.getPosition(), stone0);
        grid.add(stone1.getPosition(), stone1);
        grid.add(stone2.getPosition(), stone2);
        grid.add(stone3.getPosition(), stone3);
        grid.add(stone4.getPosition(), stone4);

        //grid.add(hunter2.getPosition(), hunter2);

        System.out.println(grid);
        for(int i = 0; i < 50; i++) {
            move(0);
            //move(1);
            System.out.println(grid);
        }
    }
}
