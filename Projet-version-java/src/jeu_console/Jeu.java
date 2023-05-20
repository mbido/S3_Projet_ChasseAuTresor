package jeu_console;

import modele.*;
import modele.occupant_no_moveable.*;
import modele.Character;

import java.util.*;
import java.io.IOException;

/**
 * The game class
 * It contains the grid and the characters
 * It plays a game on the console
 */
public class Jeu {
    private static Grid grid;
    private final int height;
    private final int width;
    private Position treasurePosition;
    private static List<Character> listCharacter;
    private int round;
    private boolean win;
    private final int nbHunter;
    private final int nbCheater;
    private final int nbWiseman;
    private final int nbWalls;
    private final int nbPickaxe;
    private final int nbLadder;
    private final int nbGlue;
    private final int nbRoadMap;

    /**
     * Create a game with a given height, width and number of Walls and Occupants
     * It places all the occupants randomly
     *
     * @param height the height of the grid
     * @param width the width of the grid
     * @param nbWalls the number of walls to add
     * @param nbHunter the number of hunter to add
     * @param nbCheater the number of cheater to add
     * @param nbWiseman the number of wiseman to add
     * @param nbPickaxe the number of pickaxe to add
     * @param nbLadder the number of ladder to add
     * @param nbGlue the number of glue to add
     * @param nbRoadMap the number of road map to add
     */
    public Jeu(int height, int width, int nbWalls, int nbHunter, int nbCheater, int nbWiseman, int nbPickaxe, int nbLadder, int nbGlue, int nbRoadMap) {
        this.nbHunter = nbHunter;
        this.nbCheater = nbCheater;
        this.nbWiseman = nbWiseman;
        this.nbWalls = nbWalls;
        this.nbPickaxe = nbPickaxe;
        this.nbLadder = nbLadder;
        this.nbGlue = nbGlue;
        this.nbRoadMap = nbRoadMap;
        listCharacter = new ArrayList<>();
        this.height = height;
        this.width = width;
        grid = new Grid(height, width);
        init();
    }

    /**
     * Create a game with default values
     */
    public Jeu(){
        this(30, 100, 30, 5, 5, 5, 5, 5, 5, 5);
    }

    /**
     * Initialize the game by adding all the occupants randomly
     */
    public void init() {
        // Ajoute aléatoirement les murs.
        addRandomWalls();

        // ajout du tresor a une position aleatoire
        Treasure treasure = new Treasure(getRandomFreePosition());
        treasurePosition = treasure.getPosition();
        grid.add(treasure.getPosition(), treasure);

        // Ajoute aléatoirement les pickaxe.
        for(int i = 0; i < nbPickaxe; i++) {
            Position pos = getRandomFreePosition();
            Pickaxe pickaxe = new Pickaxe(pos);
            grid.add(pos, pickaxe);
        }

        // Ajoute aléatoirement les ladder.
        for(int i = 0; i < nbLadder; i++) {
            Position pos = getRandomFreePosition();
            Ladder ladder = new Ladder(pos);
            grid.add(pos, ladder);
        }

        // Ajoute aléatoirement les glue.
        for(int i = 0; i < nbGlue; i++) {
            Position pos = getRandomFreePosition();
            Glue glue = new Glue(pos);
            grid.add(pos, glue);
        }

        // Ajoute aléatoirement les roadMap.
        for(int i = 0; i < nbRoadMap; i++) {
            Position pos = getRandomFreePosition();
            RoadMap roadMap = new RoadMap(pos, treasurePosition);
            grid.add(pos, roadMap);
        }

        // Ajoute aléatoirement les hunters.
        for(int i = 0; i < nbHunter; i++) {
            Position pos = getRandomFreePosition();
            Hunter hunter = new Hunter((char)('A' + i), pos);
            listCharacter.add(hunter);
            grid.add(pos, hunter);
        }

        // Ajoute aléatoirement les cheaters.
        // Remplacer par votre propre logique pour créer un Cheater.
        for(int i = 0; i < nbCheater; i++) {
            Position pos = getRandomFreePosition();
            Cheater cheater = new Cheater(pos, treasurePosition);
            listCharacter.add(cheater);
            grid.add(pos, cheater);
        }

        // Ajoute aléatoirement les wisemen.
        // Remplacer par votre propre logique pour créer un Wiseman.
        for(int i = 0; i < nbWiseman; i++) {
            Position pos = getRandomFreePosition();
            WiseMan wiseman = new WiseMan(pos, treasurePosition);
            listCharacter.add(wiseman);
            grid.add(pos, wiseman);
        }
    }

    /**
     * Creates walls with a random position, direction, and size.
     * For the size, it adds a stone to the wall with a certain probability.
     * We can then determine the average size of the walls and the standard deviation because the size follows a
     * geometric distribution with parameter p = 0.2 (this value can be changed in the while loop to experiment
     * with different wall sizes).
     * Let X be the random variable representing the size of a wall, we have :
     * E(X) = 1/p = 5 ; V(X) = (1-p)/p^2 = 20 ; sigma(X) = 4.47.
     */
    private void addRandomWalls(){
        for(int i = 0; i < nbWalls; i++){
            Position firstPos = getRandomWallStartPosition();
            Position pos = firstPos;
            Wall wall = new Wall();
            boolean isVertical = Math.random() > 0.5;
            boolean run = true;
            do{
                Stone stone = new Stone(pos);
                wall.addStone(stone);
                grid.add(pos, stone);
                if (isVertical){
                    pos = new Position(height, width, pos.getRow() + 1, pos.getCol());
                    Position pos5 = new Position(height, width, pos.getRow() + 1, pos.getCol() - 1);
                    Position pos6 = new Position(height, width, pos.getRow() + 1, pos.getCol());
                    Position pos7 = new Position(height, width, pos.getRow() + 1, pos.getCol() + 1);
                    run = isPosFree(pos) && isPosFree(pos5) && isPosFree(pos6) && isPosFree(pos7);
                } else {
                    pos = new Position(height, width, pos.getRow(), pos.getCol() + 1);
                    Position pos7 = new Position(height, width, pos.getRow() + 1, pos.getCol() + 1);
                    Position pos0 = new Position(height, width, pos.getRow(), pos.getCol() + 1);
                    Position pos1 = new Position(height, width, pos.getRow() - 1, pos.getCol() + 1);
                    run = isPosFree(pos) && isPosFree(pos7) && isPosFree(pos0) && isPosFree(pos1);
                }
            }while(Math.random() > 0.2 && run);// 80% de chance d'ajouter une pierre
            // on supprime le mur s'il est de taille 1
            if(wall.size() == 1){
                grid.remove(firstPos);
                --i;
            }
        }
    }

    /**
     * Gets a random free position in the grid for a wall.
     * A free position is a position that is not occupied and not adjacent to an occupied position.
     *
     * @return a random free position in the grid.
     */
    private Position getRandomWallStartPosition(){
        Position pos;
        boolean stop;
        do {
            pos = getRandomFreePosition();
            Position pos0 = new Position(height, width, pos.getRow() , pos.getCol() + 1);
            Position pos1 = new Position(height, width, pos.getRow() - 1, pos.getCol() + 1);
            Position pos2 = new Position(height, width, pos.getRow() - 1, pos.getCol());
            Position pos3 = new Position(height, width, pos.getRow() - 1, pos.getCol() - 1);
            Position pos4 = new Position(height, width, pos.getRow(), pos.getCol() - 1);
            Position pos5 = new Position(height, width, pos.getRow() + 1, pos.getCol() - 1);
            Position pos6 = new Position(height, width, pos.getRow() + 1, pos.getCol());
            Position pos7 = new Position(height, width, pos.getRow() + 1, pos.getCol() + 1);

            stop = isPosFree(pos0) && isPosFree(pos1) && isPosFree(pos2) && isPosFree(pos3) &&
                    isPosFree(pos4) && isPosFree(pos5) && isPosFree(pos6) && isPosFree(pos7);
        }while (!stop);
        return pos;
    }

    /**
     * Gets a Character from a position to another.
     *
     * @param i the index of the character in the list of characters.
     * @param from the position from which the character moves.
     * @param to the position to which the character moves.
     */
    private static void moveOne(int i, Position from, Position to) {
        grid.remove(from, listCharacter.get(i));
        grid.add(to, listCharacter.get(i));
        listCharacter.get(i).setNextPosition(to);
    }

    /**
     * Gets a Character from a position to another if it can.
     * Also checks if the character have to interact with the occupant of the next position.
     * The function also take care of the waiting time of the character, its pickaxe if it's a hunter.
     */
    private static void move(int i) {
        Character c = (Character) listCharacter.get(i);
        Position current = c.getPosition();
        if(c.getWaitingTime() > 0) {
            c.setWaitingTime(c.getWaitingTime() - 1);
            return;
        }
        Position next = c.getNextPosition();
        List<Occupant> occupants = grid.get(next);
        if (occupants == null) {
            moveOne(i, current, next);
            return;
        }else if(occupants.get(occupants.size() - 1).isStepEnabled()){
            moveOne(i, current, next);
        }
        Occupant oc1 = occupants.get(0);
        if (oc1 instanceof Stone) {
            Stone st1 = (Stone) oc1;
            // cas ou on peut monter dessus :
            if (st1.isStoneBroken() || grid.get(current).get(0) instanceof Stone) {
                moveOne(i, current, next);
            }else if (c instanceof Hunter && ((Hunter)c).getLadder()){
                ((Hunter)c).setLadder(false);
                moveOne(i, current, next);
            }else{
                // cas ou on ne peut pas monter dessus :
                st1.process(c);
            }
        }else{
            // cas ou l'element qui process est au meme endroit que le character
            if (next.equals(c.getPosition())){
                occupants.get(0).process(c);
            }else{
                occupants.get(occupants.size() - 1).process(c);
            }
        }
    }

    /**
     * Plays the game for a given number of turns.
     * It checks if the game is finished.
     *
     * @param nbTours the number of turns to play.
     *
     * @throws InterruptedException if the thread is interrupted.
     * @throws IOException if an I/O error occurs.
     */
    public void play(int nbTours) throws InterruptedException, IOException {
        System.out.println(grid);
        for (int i = 0; i < nbTours; i++) {
            for (int j = 0; j < listCharacter.size(); j++) {
                move(j);
            }
            System.out.println(grid);

            // stop the game if the treasure is found
            if (isFinished()) {
                Hunter winner = (Hunter) grid.get(treasurePosition).get(grid.get(treasurePosition).size() - 1);
                System.out.println("Le tresor a ete trouve !");
                System.out.println("Le gagnant est " + winner);
                System.out.println("Il a trouve le tresor en " + i + " tours");
                return;
            }
        }
        System.out.println("Fin du jeu, le tresor n'a pas ete trouve");
    }

    /**
     * Checks if the game is finished.
     * The game is finished if the treasure is found by a hunter.
     *
     * @return true if the game is finished, false otherwise.
     */
    public boolean isFinished() {
        return grid.get(treasurePosition) != null && grid.get(treasurePosition).size() > 0 &&
                grid.get(treasurePosition).get(grid.get(treasurePosition).size() - 1) instanceof Hunter;
    }

    /**
     * Gets a random free position in the grid.
     *
     * @return a random free position in the grid.
     */
    public Position getRandomFreePosition() {
        Position pos;
        do {
            pos = getRandomPosition();
        }while (!isPosFree(pos));
        return pos;
    }

    /**
     * Gets a random position in the grid.
     * It does not create a position with 'Position(height, width)' even if it would give a random position
     * because it could be a position outside the grid (more specifically, it could be on the border of the grid)
     * and it would cause an error for some methods.
     *
     * @return a random position in the grid.
     */
    public Position getRandomPosition() {
        int row = (int)(Math.random() * (height-2) + 1);
        int col = (int)(Math.random() * (width-2) + 1);
        return new Position(height, width, row, col);
    }

    /**
     * Checks if a position is free.
     *
     * @param pos the position to check.
     *
     * @return true if the position is free, false otherwise.
     */
    public boolean isPosFree(Position pos) {
        return grid.get(pos) == null;
    }

}