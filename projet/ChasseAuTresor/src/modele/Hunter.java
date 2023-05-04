package modele;

import java.util.Random;

public class Hunter extends Player {

    public Hunter(char name, Position position, int direction) {
        super(name, position, direction);
    }
    public Hunter(char name, Position position) {
        super(name, position);
    }
    public Hunter(char name) {
        super(name);
    }

    @Override
    public void process(Moveable m) {
        int dir = m.getDirection();
        // setting the direction to random value different to the current one
        while (dir == m.getDirection()) {
            m.setDirection(new Random().nextInt(8));
        }
    }
}
