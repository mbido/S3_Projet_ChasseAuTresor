package jeu_console;

import modele.*;
import modele.occupant_no_moveable.*;
import modele.Character;

import java.util.*;
import java.io.IOException;

public class Jeu {
    private Grid grid;
    private List<Character> listCharacter;
    private int round;
    private boolean win;
    private int nbHunter;
    private int nbCheater;
    private int nbWiseman;
    private int nbWalls;
    private int height = 10;
    private int width = 30;

    public Jeu(int height, int width, int nbHunter, int nbCheater, int nbWiseman, int nbWalls) {
        this.nbHunter = nbHunter;
        this.nbCheater = nbCheater;
        this.nbWiseman = nbWiseman;
        this.nbWalls = nbWalls;
        listCharacter = new ArrayList<>();
        this.height = height;
        this.width = width;
        grid = new Grid(height, width);
        init();
    }

    public void init() {
        // Ajoute aléatoirement les murs.
        addRandomWalls();

        // ajout du tresor a une position aleatoire
        Treasure treasure = new Treasure(getFreeRandomPosition());
        grid.add(treasure.getPosition(), treasure);

        // Ajoute aléatoirement les hunters.
        for(int i = 0; i < nbHunter; i++) {
            Position pos = getFreeRandomPosition();
            Hunter hunter = new Hunter((char)('A' + i), pos, 0);
            listCharacter.add(hunter);
            grid.add(pos, hunter);
        }

        // Ajoute aléatoirement les cheaters.
        // Remplacer par votre propre logique pour créer un Cheater.
        for(int i = 0; i < nbCheater; i++) {
            Position pos = getFreeRandomPosition();
            Cheater cheater = new Cheater(pos, treasure.getPosition());
            listCharacter.add(cheater);
            grid.add(pos, cheater);
        }

        // Ajoute aléatoirement les wisemen.
        // Remplacer par votre propre logique pour créer un Wiseman.
        for(int i = 0; i < nbWiseman; i++) {
            Position pos = getFreeRandomPosition();
            WiseMan wiseman = new WiseMan(pos, treasure.getPosition());
            listCharacter.add(wiseman);
            grid.add(pos, wiseman);
        }
    }
    private void addRandomWalls(){
        for(int i = 0; i < nbWalls; i++){
            Position firstPos = getRandomWallStartPosition();
            Position pos = firstPos;
            Wall wall = new Wall();
            boolean isVertical = Math.random() > 0.5;
            boolean run = true;
            do{
                System.out.println("add stone");
                Stone stone = new Stone(pos);
                wall.addStone(stone);
                grid.add(pos, stone);
                if (isVertical){
                    pos = new Position(height, width, pos.getRow(), pos.getCol() + 1);
                    Position pos5 = new Position(height, width, pos.getRow() + 1, pos.getCol() - 1);
                    Position pos6 = new Position(height, width, pos.getRow() + 1, pos.getCol());
                    Position pos7 = new Position(height, width, pos.getRow() + 1, pos.getCol() + 1);
                    run = posIsFree(pos) && posIsFree(pos5) && posIsFree(pos6) && posIsFree(pos7);
                } else {
                    pos = new Position(height, width, pos.getRow() + 1, pos.getCol());
                    Position pos7 = new Position(height, width, pos.getRow() + 1, pos.getCol() + 1);
                    Position pos0 = new Position(height, width, pos.getRow(), pos.getCol() + 1);
                    Position pos1 = new Position(height, width, pos.getRow() - 1, pos.getCol() + 1);
                    run = posIsFree(pos) && posIsFree(pos7) && posIsFree(pos0) && posIsFree(pos1);
                }
            }while(Math.random() > 0.2 && run);// 80% de chance d'ajouter une pierre
            // on supprime le mur s'il est de taille 1
            if(wall.size() == 1){
                grid.remove(firstPos);
                --i;
            }
        }
    }

    private Position getRandomWallStartPosition(){
        Position pos;
        boolean stop;
        do {
            System.out.println("getRandomWallStartPosition");
            pos = getFreeRandomPosition();
            Position pos0 = new Position(height, width, pos.getRow() , pos.getCol() + 1);
            Position pos1 = new Position(height, width, pos.getRow() - 1, pos.getCol() + 1);
            Position pos2 = new Position(height, width, pos.getRow() - 1, pos.getCol());
            Position pos3 = new Position(height, width, pos.getRow() - 1, pos.getCol() - 1);
            Position pos4 = new Position(height, width, pos.getRow(), pos.getCol() - 1);
            Position pos5 = new Position(height, width, pos.getRow() + 1, pos.getCol() - 1);
            Position pos6 = new Position(height, width, pos.getRow() + 1, pos.getCol());
            Position pos7 = new Position(height, width, pos.getRow() + 1, pos.getCol() + 1);

            stop = posIsFree(pos0) && posIsFree(pos1) && posIsFree(pos2) && posIsFree(pos3) &&
                    posIsFree(pos4) && posIsFree(pos5) && posIsFree(pos6) && posIsFree(pos7);
        }while (!stop);
        return pos;
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
            for (int j = 0; j < listCharacter.size(); j++) {
                move(j);
            }
            System.out.println(grid);
        }
    }

    public void afficher() {
        System.out.println(grid);
    }

    public Position getFreeRandomPosition() {
        // A remplir avec votre logique.
        Position pos;
        do {
            System.out.println("getFreeRandomPosition");
            pos = getRandomPosition();
        }while (!posIsFree(pos));
        return pos;
    }

    public Position getRandomPosition() {
        // A remplir avec votre logique.
        int x = (int)(Math.random() * (height-2) + 1);
        int y = (int)(Math.random() * (width-2) + 1);
        return new Position(height, width, x, y);
    }

    public boolean posIsNextToStone(Position pos) {
        // A remplir avec votre logique.
        return false;
    }

    public boolean posIsFree(Position pos) {
        return grid.get(pos) == null || grid.get(pos).size() == 0;
    }

}