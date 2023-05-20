package modele;

/**
 * Interface Questionnable
 * A questionnable object is an object that can interact with a character
 * by processing that interaction.
 */
public interface Questionable {
    /**
     * Process the interaction between the Questionable and a character.
     *
     * @param m the character to interact with
     */
    public void process(Character m);
}
