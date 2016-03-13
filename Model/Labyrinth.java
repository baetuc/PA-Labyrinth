package Model;

/**
 * Created by Cip on 07-Mar-16.
 */
public interface Labyrinth {
    int getRowCount();
    int getColumnCount();
    boolean isFreeAt(int row, int column);
    boolean isWallAt(int row, int column);
    Cell getStartCell();
    Cell getFinishCell();
}
