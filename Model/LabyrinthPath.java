package Model;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Cip on 13-Mar-16.
 */
public class LabyrinthPath {
    private Set<Cell> path = new LinkedHashSet<>();

    public Set<Cell> getPath() {
        return path;
    }

    public boolean containsCell(Cell cell) {
        return path.contains(cell);
    }

    public void addCellToPath(Cell newCell) {
        path.add(newCell);
    }

}
