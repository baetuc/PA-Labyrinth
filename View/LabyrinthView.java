package View;

import Model.Cell;
import Model.Labyrinth;
import Model.LabyrinthPath;

/**
 * Created by Cip on 13-Mar-16.
 */
public interface LabyrinthView {
    void printLabyrinth(Labyrinth labyrinth, Cell currentPosition);
    void printSolution(Labyrinth labyrinth, LabyrinthPath path);
}
