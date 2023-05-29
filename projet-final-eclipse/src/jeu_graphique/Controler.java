package jeu_graphique;

import modele.*;
import modele.Character;
import modele.occupant_no_moveable.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Controler implements ActionListener{

    //pour le jeu :

    private static Grid grid;
    private final int height;
    private final int width;
    private Position treasurePosition;
    private static List<Character> listCharacter;
    // private int round;
    // private boolean win;
    private final int nbHunter;
    private final int nbCheater;
    private final int nbWiseman;
    private final int nbWalls;
    private final int nbPickaxe;
    private final int nbLadder;
    private final int nbGlue;
    private final int nbRoadMap;

    private final int nbTours;


    private Fenetre fenetre;

    public Controler(int height, int width, int nbWalls, int nbHunter, int nbCheater, int nbWiseman, int nbPickaxe, int nbLadder, int nbGlue, int nbRoadMap, int nbTours) throws IOException, InterruptedException {
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
        this.nbTours = nbTours;

        this.fenetre = new Fenetre(height, width);
        this.fenetre.addEcouteur(this);

        init();
        afficherGrid();
        play(nbTours);
    }

    public Controler(int height, int width) throws IOException, InterruptedException {
        this(height, width, 5, 0, 0, 0, 0, 0, 0, 0, 10);
    }

    public Controler() throws IOException, InterruptedException {
        this(15, 30);
    }

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

    private static void moveOne(int i, Position from, Position to) {
        grid.remove(from, listCharacter.get(i));
        grid.add(to, listCharacter.get(i));
        listCharacter.get(i).setNextPosition(to);
    }

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

    public void play(int nbTours) throws InterruptedException, IOException {
        fenetre.activerNouvelle(false);
        for (int i = 0; i < nbTours; i++) {
            for (int j = 0; j < listCharacter.size(); j++) {
                move(j);
            }
            afficherGrid();
            Thread.sleep(300);

            // stop the game if the treasure is found
            if (isFinished()) {
                Hunter winner = (Hunter) grid.get(treasurePosition).get(grid.get(treasurePosition).size() - 1);
                System.out.println("Le tresor a ete trouve !");
                System.out.println("Le gagnant est " + winner);
                System.out.println("Il a trouve le tresor en " + i + " tours");
                fenetre.activerNouvelle(true);
                return;
            }
        }
        fenetre.activerNouvelle(true);
        System.out.println("Fin du jeu, le tresor n'a pas ete trouve");
    }

    public boolean isFinished() {
        return grid.get(treasurePosition) != null && grid.get(treasurePosition).size() > 0 &&
                grid.get(treasurePosition).get(grid.get(treasurePosition).size() - 1) instanceof Hunter;
    }

    public Position getRandomFreePosition() {
        Position pos;
        do {
            pos = getRandomPosition();
        }while (!isPosFree(pos));
        return pos;
    }

    public Position getRandomPosition() {
        int row = (int)(Math.random() * (height-2) + 1);
        int col = (int)(Math.random() * (width-2) + 1);
        return new Position(height, width, row, col);
    }


    /**
     * This method is used to initialize a new game. It is called when the "Nouvelle Partie" button is pressed in the GUI.
     * The purpose of this method is to reset the game to its initial state, thus allowing players to start over with a clean board.
     * <p>
     * The function does this by first creating a new Grid and ArrayList for the characters, and then calling the 'init' method to initialize
     * the game's elements (such as walls, treasure, pickaxe, ladder, glue, roadmap, characters, etc.) in random positions on the grid.
     * <p>
     * After initializing the game, it calls the 'afficherGrid' method to update and display the new game state in the GUI,
     * and then calls the 'play' method to start the game logic for the specified number of turns (nbTours).
     * <p>
     * Any errors occurring during IO or threading operations are caught and handled by throwing a RuntimeException, ensuring
     * that the program doesn't crash due to these exceptions.
     *
     */
    private void nouvellePartie() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                grid = new Grid(height, width);
                listCharacter = new ArrayList<>();
                init();
                afficherGrid();
                play(nbTours);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }


    private void afficherGrid(){
        for(int row = 0; row < height; ++row){
            for(int col = 0; col < width; ++col){
                Position pos = new Position(height, width, row, col);
                List<Occupant> occupants = grid.get(pos);
                if(grid.get(pos) != null){
                    afficherOccupants(occupants, row, col);
                }else{
                    fenetre.setImageAt(row, col, "/asset/final/grass/grass.png");
                }
            }
        }
        fenetre.update();
    }

    private String getDirectionLetter(int direction) {
        switch (direction) {
            case 1:
            case 2:
            case 3:
                return "u";
            case 4:
            case 5:
                return "l";
            case 0:
            case 7:
                return "r";
            default:
                return "d";
        }
    }
    private String getColorLetter(String hunter){
        char h = hunter.charAt(0);
        int c = (h - 'A') % 4;
        switch (c){
            case 0:
                return "b";
            case 1:
                return "g";
            case 2:
                return "r";
            default:
                return "y";
        }
    }

    private void afficherOccupants(List<Occupant> occupants, int row, int col) {
        String path = "/asset/final";
        if(occupants.size() == 1){
            Occupant occ = occupants.get(0);
            if(occ instanceof Stone && !((Stone)occ).isStoneBroken() || occ instanceof Border) {
                path += "/stone/stone.png";
            }else if(Objects.equals(occ.toString(), " ")) {
                path += "/grass/grass.png";
            }else if(Objects.equals(occ.toString(), "~")) {
                path += "/glue/glue.png";
            }else if(Objects.equals(occ.toString(), "7")) {
                path += "/grass/items/pickaxe.png";
            }else if(Objects.equals(occ.toString(), "/")) {
                path += "/grass/items/ladder.png";
            }else if(Objects.equals(occ.toString(), "$")) {
                path += "/grass/items/chest.png";
            }else if(Objects.equals(occ.toString(), "?")) {
                path += "/grass/items/roadmap.png";
            }else if(occ instanceof Cheater) {
                path += "/grass/cheater/c" + getDirectionLetter(((Cheater)occ).getDirection()) + ".png";
            }else if(occ instanceof WiseMan) {
                path += "/grass/wiseMan/w" + getDirectionLetter(((WiseMan)occ).getDirection()) + ".png";
            }else if(occ instanceof Hunter) {
                path += "/grass/hunter/h" + getColorLetter(occ.toString()) + getDirectionLetter(((Hunter)occ).getDirection()) + ".png";
            }
        }else if (occupants.size() > 1){
            Occupant occ0 = occupants.get(0);
            Occupant occ1 = occupants.get(1);
            if(occ0 instanceof Stone && !((Stone)occ0).isStoneBroken() && occ1 instanceof Hunter) {
                path += "/stone/hunter/h" + getColorLetter(occ1.toString()) + getDirectionLetter(((Hunter) occ1).getDirection()) + ".png";
            }else if(occ0 instanceof Glue) {
                if (occ1 instanceof Hunter) {
                    path += "/glue/hunter/h" + getColorLetter(occ1.toString()) + getDirectionLetter(((Hunter) occ1).getDirection()) + ".png";
                } else if (occ1 instanceof Cheater) {
                    path += "/glue/cheater/c" + getDirectionLetter(((Cheater) occ1).getDirection()) + ".png";
                } else if (occ1 instanceof WiseMan) {
                    path += "/glue/wiseMan/w" + getDirectionLetter(((WiseMan) occ1).getDirection()) + ".png";
                }
            }else if(occ1 instanceof Hunter) {
                path += "/grass/hunter/h" + getColorLetter(occ1.toString()) + getDirectionLetter(((Hunter) occ1).getDirection()) + ".png";
            }else if(occ1 instanceof Cheater) {
                path += "/grass/cheater/c" + getDirectionLetter(((Cheater) occ1).getDirection()) + ".png";
            }else if(occ1 instanceof WiseMan) {
                path += "/grass/wiseMan/w" + getDirectionLetter(((WiseMan) occ1).getDirection()) + ".png";
            }
        }else{
            path += "/grass/grass.png";
        }
        fenetre.setImageAt(row, col, path);
    }

    public boolean isPosFree(Position pos) {
        return grid.get(pos) == null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Nouvelle Partie")) {
            nouvellePartie();
        } else {
            System.out.println("Commande inconnue");
            System.out.println(command);
        }
    }
}
