package Model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Cip on 13-Mar-16.
 */
public class LabyrinthPath {
    private List<Cell> path = new LinkedList<>();

    public List<Cell> getPath() {
        return path;
    }

    public void addCellToPath(Cell newCell) {
        path.add(newCell);
    }

}
