package jeu_graphique;

import modele.*;
import modele.Character;
import modele.occupant_no_moveable.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Fenetre extends JFrame {

    private int gridRow;
    private int gridCol;

    // Frame
    private JFrame fenetre;

    // Panels
    private JPanel principale;
    private JPanel sud; // for the controls
    private JPanel centre; // for the map

    // Grid
    private JLabel[][] grid;

    // Buttons
    private JButton nouvellePartie;

    public Fenetre(int gridRow, int gridCol) {
        this.gridRow = gridRow;
        this.gridCol = gridCol;

        // Define the unit size for each cell
        int cellSize = (gridRow < gridCol) ? 800 / gridCol : 800 / gridRow;

        // Calculate the total width and height required for the centre JPanel
        int centreWidth = gridCol * cellSize;
        int centreHeight = gridRow * cellSize;

        // Creating JFrame
        fenetre = new JFrame("Chasse au Trésor");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setResizable(false);

        principale = new JPanel();
        principale.setLayout(new BorderLayout());

        sud = new JPanel();
        sud.setLayout(new BorderLayout());

        // Adding the button in the center of sud part
        nouvellePartie = new JButton("Nouvelle Partie");
        sud.add(nouvellePartie, BorderLayout.CENTER);

        centre = new JPanel();
        centre.setPreferredSize(new Dimension(centreWidth, centreHeight));
        centre.setLayout(new GridLayout(this.gridRow, this.gridCol));

        // Creating the grid
        grid = new JLabel[gridRow][gridCol];
        for (int i = 0; i < gridRow; i++) {
            for (int j = 0; j < gridCol; j++) {
                grid[i][j] = new JLabel();
                centre.add(grid[i][j]);
            }
        }

        principale.add(sud, BorderLayout.SOUTH);
        principale.add(centre, BorderLayout.CENTER);

        fenetre.setContentPane(principale);
        fenetre.pack();
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
    }

    public void setImageAt(int row, int col, String imagePath) {
        // Create ImageIcon
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl == null) {
            System.out.println("Error loading image: " + imagePath);
            return;
        }
        ImageIcon imageIcon = new ImageIcon(imageUrl);

        // Get the image from the ImageIcon
        Image image = imageIcon.getImage();

        // Resize the image
        Image resizedImage = image.getScaledInstance(grid[row][col].getWidth(), grid[row][col].getHeight(), Image.SCALE_DEFAULT);

        // Set the JLabel's image
        grid[row][col].setIcon(new ImageIcon(resizedImage));

    }

    public void update(){
        fenetre.revalidate();
        fenetre.repaint();
    }




    public void addEcouteur(ActionListener actionListener) {
        nouvellePartie.addActionListener(actionListener);
    }
}
