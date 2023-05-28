import jeu_graphique.*;

import java.io.IOException;

public class Main {
    private static Controler controler;
    public static void main(String[] args) throws IOException, InterruptedException {
        controler = new Controler(15, 20, 10, 5, 3, 3, 3, 3, 5, 5, 10000);
    }
}
