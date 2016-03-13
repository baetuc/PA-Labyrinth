package Observer;

import Model.Cell;

/**
 * Created by Cip on 13-Mar-16.
 */
public interface LabyrinthObserver {

    void update(Cell cell);
    void processCell();
    void processSolution();
}
