package jeu_console;

import modele.*;
import modele.occupant_no_moveable.*;
import modele.Character;

import java.util.*;
import java.io.IOException;

public class Jeu {
    private int round;
    private Grid grid;
    private List<Character> listCharacter;
    private boolean win;
    private final int MAX_ROW = 10;
    private final int MAX_COL = 30;

    public Jeu() {
        this.round = 0;
        this.grid = new Grid(MAX_ROW, MAX_COL);
        this.listCharacter = new ArrayList<>();
        this.win = false;
    }

    public void initialisation() {
        // Code pour initialiser votre jeu ici, en utilisant la logique de votre Main class.
        Hunter hunter = new Hunter('H', new Position(MAX_ROW, MAX_COL, 5, 2), 0);

        // - tresor
        Treasure treasure = new Treasure(new Position(MAX_ROW, MAX_COL, 8, 10));

        // - RoadMap
        RoadMap roadMap = new RoadMap(new Position(MAX_ROW, MAX_COL, 5, 7), treasure.getPosition());

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

        // adding them to the list
        listCharacter.add(hunter);

        // adding them to the grid
        grid.add(hunter.getPosition(), hunter);
        grid.add(treasure.getPosition(), treasure);
        grid.add(roadMap.getPosition(), roadMap);

        // -Wall
        grid.add(stone0.getPosition(), stone0);
        grid.add(stone1.getPosition(), stone1);
        grid.add(stone2.getPosition(), stone2);
        grid.add(stone3.getPosition(), stone3);
        grid.add(stone4.getPosition(), stone4);
    }

    private void moveOne(int i, Position from, Position to) {
        grid.remove(from, listCharacter.get(i));
        grid.add(to, listCharacter.get(i));
        listCharacter.get(i).setNextPosition(to);
    }

    private void move(int i) {
        // Implementation of the move method from your Main class
    }

    public void play() throws InterruptedException, IOException {
        System.out.println(grid);
        for (int i = 0; i < 50; i++) {
            move(0);
            System.out.println(grid);
        }
    }

    public void afficher() {
        // Code for afficher method here, if necessary.
    }

    public Position getFreeRandomWallPosition() {
        // A remplir avec votre logique.
        Position pos;
        do {
            pos = new Position((int)(Math.random() * (MAX_ROW-4) + 1), (int)(Math.random() * (MAX_COL-4) + 1));
        }while(!posIsFree(pos) || posIsNextToStone(pos));
        System.out.println(pos);
        return pos;
    }

    public Position getFreeRandomPosition() {
        // A remplir avec votre logique.
        Position pos;
        do {
            pos = getRandomPosition();
        }while (!posIsFree(pos));
        return pos;
    }

    public Position getRandomPosition() {
        // A remplir avec votre logique.
        int x = (int)(Math.random() * (MAX_ROW-2) + 1);
        int y = (int)(Math.random() * (MAX_COL-2) + 1);
        return new Position(x, y);
    }

    public boolean posIsNextToStone(Position pos) {
        // A remplir avec votre logique.
        return false;
    }

    public boolean posIsFree(Position pos) {
        // A remplir avec votre logique.
        return false;
    }

}