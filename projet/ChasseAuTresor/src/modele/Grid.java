package modele;

import javax.sql.ConnectionPoolDataSource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class Grid {
    // We will be using LinkedLists, so we can access the first element and the last one in O(1)
    private static Map<Position, List<Occupant>> map = new HashMap<>();
    private final int width, height;
    public Grid(int height, int width) {
        this.width = width;
        this.height = height;
        map = new HashMap<>();
        setMap();
    }
    public Grid() {
        this(10, 30);
    }
    public List<Occupant> get(Position p) {
        return map.getOrDefault(p, null);
    }
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

    public void add(Position p, Occupant o) {
        if (map.containsKey(p)) {
            get(p).add(o);
        } else {
            LinkedList<Occupant> list = new LinkedList<>();
            list.add(o);
            map.put(p, list);
        }
    }

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

    public void remove(Position p) {
        map.remove(p);
    }

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
