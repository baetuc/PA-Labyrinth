package Controller;

import Model.Cell;
import Model.Labyrinth;

import java.io.IOException;

/**
 * Created by Cip on 13-Mar-16.
 */
public interface LabyrinthSolver {
    Cell nextCellToExplore() throws IOException;
    void solve();
}
