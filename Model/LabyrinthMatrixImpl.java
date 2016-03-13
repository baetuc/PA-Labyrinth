package Model;

import com.google.common.base.Preconditions;

/**
 * Created by Cip on 07-Mar-16.
 */
public class LabyrinthMatrixImpl implements Labyrinth {

    CellType[][] representation;


    public LabyrinthMatrixImpl(CellType[][] representation) throws NullPointerException {
        Preconditions.checkNotNull(representation, "The representation of the labyrinth must be not null.");
        this.representation = representation;
    }

    @Override
    public int getRowCount() throws NullPointerException {
        Preconditions.checkNotNull(representation);
        return representation.length;
    }

    @Override
    public int getColumnCount() throws NullPointerException {
        Preconditions.checkNotNull(representation);
        Preconditions.checkNotNull(representation[0]);
        return representation[0].length;
    }

    @Override
    public boolean isFreeAt(int row, int column) throws IllegalArgumentException {
        Preconditions.checkArgument(0 <= row && row < representation.length, "Row is out of bounds.\n");
        Preconditions.checkArgument(0 <= column && column < representation[row].length, "Column is out of bound.\n");
        return representation[row][column].equals(CellType.FREE);
    }

    @Override
    public boolean isWallAt(int row, int column) throws IllegalArgumentException {
        Preconditions.checkArgument(0 <= row && row < representation.length, "Row is out of bounds.\n");
        Preconditions.checkArgument(0 <= column && column < representation[row].length, "Column is out of bound.\n");
        return representation[row][column].equals(CellType.WALL);
    }

    @Override
    public Cell getStartCell() throws UnsupportedOperationException {
        for (int i = 0; i < representation.length; ++i) {
            for (int j = 0; j < representation[i].length; ++j) {
                if (representation[i][j].equals(CellType.START)) {
                    return new Cell(i, j);
                }
            }
        }
        throw new UnsupportedOperationException("Labyrinth must have a start cell.\n");
    }

    @Override
    public Cell getFinishCell() throws UnsupportedOperationException {
        for (int i = 0; i < representation.length; ++i) {
            for (int j = 0; j < representation[i].length; ++j) {
                if (representation[i][j].equals(CellType.FINISH)) {
                    return new Cell(i, j);
                }
            }
        }
        throw new UnsupportedOperationException("Labyrinth must have a finish cell.\n");
    }

}
