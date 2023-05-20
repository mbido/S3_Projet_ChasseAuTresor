package modele.occupant_no_moveable;

import modele.Occupant;
import modele.Position;

/**
 * Class that represents a tool
 *
 */
public abstract class Tool extends Occupant{

    /**
     * Constructor of the class Tool
     * A tool is an occupant that can be picked up by the hunter
     * A tool is can be walked on
     *
     * @param position the position of the tool
     */
    public Tool(Position position) {
        super(position, true);
    }
}
