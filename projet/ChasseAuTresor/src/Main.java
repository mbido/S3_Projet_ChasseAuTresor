import jeu_console.Jeu;

import java.io.IOException;

public class Main {
    private static Jeu jeu;
    public static void main(String[] args) throws IOException, InterruptedException {
        jeu = new Jeu(30, 150, 0, 0, 0, 100);
        jeu.afficher();
    }
}
