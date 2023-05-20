import jeu_console.Jeu;

import java.io.IOException;

public class Main {
    private static Jeu jeu;
    public static void main(String[] args) throws IOException, InterruptedException {
        //jeu = new Jeu(10, 20, 10, 1, 0, 0, 0, 20, 0, 0);
        jeu = new Jeu();
        jeu.play(10000);
    }
}
