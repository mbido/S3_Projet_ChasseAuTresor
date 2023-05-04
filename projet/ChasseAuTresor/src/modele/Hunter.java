package modele;

import java.util.Random;

public class Hunter extends Player {

    public Hunter(char name, Position position, int direction) {
        super(name, position, direction);
    }
    public Hunter(char name, Position position) {
        super(name, position);
    }

    @Override
    public void process(Moveable m) {
        m.setDirection(new Random().nextInt(8));
        //sysout new dir
        System.out.println("New direction of "+ toString() +": " + m.getDirection());
    }
}
