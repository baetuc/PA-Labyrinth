package Controller;

import Model.Cell;
import Model.Labyrinth;
import Model.LabyrinthPath;
import Observer.LabyrinthObserver;
import View.LabyrinthView;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Cip on 13-Mar-16.
 */
public abstract class AbstractLabyrinthSolver implements LabyrinthSolver {
    protected Labyrinth labyrinth;
    protected LabyrinthView view;
    protected Cell currentPosition;
    protected List<LabyrinthObserver> observers = new LinkedList<>();

    public void setLabyrinth(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    public void setView(LabyrinthView view) {
        this.view = view;
    }

    public void attach(LabyrinthObserver observer) {
        this.observers.add(observer);
    }

    protected void notifyAllObservers(Cell newCell) {
        for (LabyrinthObserver observer : observers) {
            observer.update(newCell);
            if(labyrinth.getFinishCell().equals(currentPosition)) {
                observer.processSolution();
            }
            else {
                observer.processCell();
            }
        }
    }

    public void updateView() {
        if(view != null) {
            view.printLabyrinth(labyrinth, currentPosition);
        }
    }

    public void updateViewSolution(LabyrinthPath path) {
        if(view != null) {
            view.printSolution(labyrinth, path);
        }
    }

    protected Cell exploreCellUp() {
        if (currentPosition.getRow() == 0 ||
                labyrinth.isWallAt(currentPosition.getRow() - 1, currentPosition.getColumn())) {
            return new Cell(currentPosition.getRow(), currentPosition.getColumn());
        } else {
            return new Cell(currentPosition.getRow() - 1, currentPosition.getColumn());
        }
    }

    protected Cell exploreCellDown() {
        if (currentPosition.getRow() == labyrinth.getRowCount() - 1 ||
                labyrinth.isWallAt(currentPosition.getRow() + 1, currentPosition.getColumn())) {
            return new Cell(currentPosition.getRow(), currentPosition.getColumn());
        } else {
            return new Cell(currentPosition.getRow() + 1, currentPosition.getColumn());
        }
    }

    protected Cell exploreCellLeft() {
        if (currentPosition.getColumn() == 0 ||
                labyrinth.isWallAt(currentPosition.getRow(), currentPosition.getColumn() - 1)) {
            return new Cell(currentPosition.getRow(), currentPosition.getColumn());
        } else {
            return new Cell(currentPosition.getRow(), currentPosition.getColumn() - 1);
        }
    }

    protected Cell exploreCellRight() {
        if (currentPosition.getColumn() == labyrinth.getColumnCount() - 1 ||
                labyrinth.isWallAt(currentPosition.getRow(), currentPosition.getColumn() + 1)) {
            return new Cell(currentPosition.getRow(), currentPosition.getColumn());
        } else {
            return new Cell(currentPosition.getRow(), currentPosition.getColumn() + 1);
        }
    }

    public abstract void solve();

    public abstract Cell nextCellToExplore() throws IOException;

}
