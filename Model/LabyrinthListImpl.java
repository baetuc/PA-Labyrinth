package Model;

import com.google.common.base.Preconditions;

import java.util.Set;

/**
 * Created by Cip on 07-Mar-16.
 */
public class LabyrinthListImpl implements Labyrinth {
    public Set<Cell> occupiedCells;
    public Cell startCell;
    public Cell finishCell;
    int rowCount;
    int columnCount;

    LabyrinthListImpl(Set<Cell> occupiedCells, Cell startCell, Cell finishCell, int rowCount, int columnCount)
            throws NullPointerException, IllegalArgumentException {
        Preconditions.checkNotNull(startCell, "Start cell must not be null.\n");
        Preconditions.checkNotNull(finishCell, "Finish cell must not be null.\n");
        Preconditions.checkArgument(rowCount > 0, "Number of rows in the labyrinth must be positive.\n");
        Preconditions.checkArgument(columnCount > 0, "Number of columns in the labyrinth must be positive");
        Preconditions.checkArgument(0 <= startCell.getRow() && startCell.getRow() < rowCount,
                "Start cell must be inside the labyrinth.\n");
        Preconditions.checkArgument(0 <= finishCell.getRow() && finishCell.getRow() < rowCount,
                "Finish cell must be inside the labyrinth.\n");
        Preconditions.checkArgument(0 <= startCell.getColumn() && startCell.getColumn() < columnCount,
                "Start cell must be inside the labyrinth.\n");
        Preconditions.checkArgument(0 <= finishCell.getColumn() && finishCell.getColumn() < columnCount,
                "Finish cell must be inside the labyrinth.\n");

        this.occupiedCells = occupiedCells;
        this.startCell = startCell;
        this.finishCell = finishCell;
        this.rowCount = rowCount;
        this.columnCount = columnCount;

    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public boolean isFreeAt(int row, int column) {
        Cell wantedCell = new Cell(row, column);
        if (startCell.equals(wantedCell) || finishCell.equals(wantedCell)) {
            return false;
        }
        return (!occupiedCells.contains(wantedCell));
    }

    @Override
    public boolean isWallAt(int row, int column) {
        Cell wantedCell = new Cell(row, column);
        if (startCell.equals(wantedCell) || finishCell.equals(wantedCell)) {
            return false;
        }
        return (occupiedCells.contains(wantedCell));
    }

    @Override
    public Cell getStartCell() {
        return startCell;
    }

    @Override
    public Cell getFinishCell() {
        return finishCell;
    }

}
