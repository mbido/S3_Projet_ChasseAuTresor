import jeu_graphique.*;

import java.io.IOException;

public class Main {
    private static Controler controler;
    public static void main(String[] args) throws IOException, InterruptedException {
        controler = new Controler(25, 50, 50, 5, 5, 5, 5, 5, 10, 5, 10000);
    }
}
