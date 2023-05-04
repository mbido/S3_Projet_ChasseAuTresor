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

    @Override
    public void process(Moveable m) {
        m.setDirection(new Random().nextInt(8));
    }
}
