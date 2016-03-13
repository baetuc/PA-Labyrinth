package Model;

import com.google.common.base.Preconditions;

import java.util.Objects;

/**
 * Created by Cip on 07-Mar-16.
 */
public class Cell {
    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Cell)) {
            return false;
        }
        Cell that = (Cell) obj;
        return this.row == that.row && this.column == that.column;
    }

}
