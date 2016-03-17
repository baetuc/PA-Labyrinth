package Model;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Cip on 13-Mar-16.
 */
public class LabyrinthPath {
    private Set<Cell> path;;

    public LabyrinthPath() {
        path = new LinkedHashSet<>();
    }

    public LabyrinthPath(LabyrinthPath other) {
        path = new LinkedHashSet<>();
        for(Cell cell : other.getPath()) {
            this.path.add(cell);
        }
    }

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
