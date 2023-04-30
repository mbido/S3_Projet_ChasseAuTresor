package modele;

import java.util.Random;

public class Hunter extends Player{
    private int waitingTime;

    public Hunter(char name, Position position, int direction) {
        super(name, position, direction);
        this.waitingTime = 0;
    }
    public Hunter(char name, Position position) {
        super(name, position);
        this.waitingTime = 0;
    }
    public Hunter(char name) {
        super(name);
        this.waitingTime = 0;
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
