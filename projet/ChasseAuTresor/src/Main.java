import jeu_console.Jeu;

import java.io.IOException;

public class Main {
    private static Jeu jeu;
    public static void main(String[] args) throws IOException, InterruptedException {
        //jeu = new Jeu(10, 20, 0, 1, 20, 0, 0, 0, 0, 0);
        jeu = new Jeu();
        jeu.play(500);
    }
}
