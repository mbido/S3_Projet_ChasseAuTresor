package modele;

// import javax.sql.ConnectionPoolDataSource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class represents the grid of the game
 * It is a map of positions and lists of occupants
 */
public class Grid {
    // We will be using LinkedLists, so we can access the first element and the last one in O(1)
    private static Map<Position, List<Occupant>> map = new HashMap<>();
    private final int width, height;

    /**
     * Create a grid with a given height and width
     * @param height the height of the grid
     * @param width the width of the grid
     */
    public Grid(int height, int width) {
        this.width = width;
        this.height = height;
        map = new HashMap<>();
        setMap();
    }

    /**
     * Create a grid with a default height and width
     */
    public Grid() {
        this(10, 30);
    }

    /**
     * Get the list of occupants at a given position
     * @param p the position of the occupants
     * @return the list of occupants at the given position or null if there is no occupant at this position
     */
    public List<Occupant> get(Position p) {
        return map.getOrDefault(p, null);
    }

    /**
     * Set the borders of the map
     */
    private void setMap() {
        // adding borders to the map
        for (int i = 0; i < width; i++) {
            Position p = new Position(height, width, 0, i);
            LinkedList<Occupant> rowTop = new LinkedList<>();
            rowTop.add(new Border(p));
            map.put(p, rowTop);

            p = new Position(height, width, height - 1, i);
            LinkedList<Occupant> rowBottom = new LinkedList<>();
            rowBottom.add(new Border(p));
            map.put(p, rowBottom);
        }
        for (int j = 0; j < height; j++) {
            Position p = new Position(height, width, j, 0);
            LinkedList<Occupant> colLeft = new LinkedList<>();
            colLeft.add(new Border(p));
            map.put(p, colLeft);

            p = new Position(height, width, j, width - 1);
            LinkedList<Occupant> colRight = new LinkedList<>();
            colRight.add(new Border(p));
            map.put(p, colRight);
        }
    }

    /**
     * Add an occupant at a given position
     * @param p the position of the occupant
     * @param o the occupant to add
     */
    public void add(Position p, Occupant o) {
        if (map.containsKey(p)) {
            get(p).add(o);
        } else {
            LinkedList<Occupant> list = new LinkedList<>();
            list.add(o);
            map.put(p, list);
        }
    }

    /**
     * Remove an occupant at a given position
     * Remove the position from the map if there is no occupant left
     *
     * @param p the position of the occupant
     * @param o the occupant to remove
     */
    public void remove(Position p, Occupant o) {
        List<Occupant> occupants = get(p);
        if (occupants != null && occupants.contains(o)) {
            occupants.remove(o);
            if (occupants.isEmpty()) {
                map.remove(p);
            }else{
                map.put(p, (LinkedList<Occupant>) occupants);
            }
        };
    }

    /**
     * Remove all occupants at a given position
     * Remove the position from the map
     *
     * @param p the position of the occupant
     */
    public void remove(Position p) {
        map.remove(p);
    }

    /**
     * Get the string representation of the grid
     *
     * @return the string representation of the grid
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Position p = new Position(height, width, i, j);

                if (map.containsKey(p)) {
                    res.append(((LinkedList<?>) map.get(p)).getLast());
                } else {
                    res.append(" ");
                }
            }
            res.append("\n");
        }
        return res.toString();
    }
}
