package modele;

import java.util.Random;

public class Hunter extends Player {
    boolean pickaxe = false;
    boolean ladder = false;

    public Hunter(char name, Position position, int direction) {
        super(name, position, direction);
    }
    public Hunter(char name, Position position) {
        super(name, position);
        
    }
    public boolean HavePickaxe() {
        return pickaxe;
    }
    public void setPickaxe(boolean pickaxe) {
        this.pickaxe = pickaxe;
    }

    public boolean HaveLadder() {
        return ladder;
    }

    public void setLadder(boolean ladder) {
        this.ladder = ladder;
    }

    @Override
    public void process(Moveable m) {
        m.setDirection(new Random().nextInt(8));
    }
}
