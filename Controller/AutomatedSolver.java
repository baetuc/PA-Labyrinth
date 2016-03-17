package Controller;

import Model.Cell;
import Model.Labyrinth;
import Model.LabyrinthPath;
import View.LabyrinthView;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Cip on 17-Mar-16.
 */
public class AutomatedSolver extends AbstractLabyrinthSolver {
    public AutomatedSolver(Labyrinth labyrinth, LabyrinthView view) {
        Preconditions.checkNotNull(labyrinth, "Labyrinth input must be not null at solver constructor.");
        Preconditions.checkNotNull(view, "View input must be not null at solver constructor.");
        this.labyrinth = labyrinth;
        this.view = view;
        currentPosition = labyrinth.getStartCell();
        observers = new LinkedList<>();
    }

    @Override
    public Cell nextCellToExplore() {
        return null;
    }

    @Override
    public void solve() {
        Queue<LabyrinthPath> allPaths = getAllPaths();
        for (LabyrinthPath path : allPaths) {
            for (Cell newCell : path.getPath()) {
                currentPosition = newCell;
                notifyAllObservers(newCell);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private boolean addElementToQueue(Queue<LabyrinthPath> paths, boolean visited[][], LabyrinthPath current, Cell futureCell) {
        if (!futureCell.equals(this.currentPosition) && (!visited[futureCell.getRow()][futureCell.getColumn()] ||
                futureCell.equals(labyrinth.getFinishCell()))) {
            visited[futureCell.getRow()][futureCell.getColumn()] = true;
            LabyrinthPath newPath = new LabyrinthPath(current);
            newPath.addCellToPath(futureCell);
            paths.add(newPath);
            return true;
        }
        return false;
    }

    private boolean modifyList(Queue<LabyrinthPath> paths, boolean visited[][], LabyrinthPath current) {
        boolean result = false;
        if (!currentPosition.equals(labyrinth.getFinishCell())) {
            Cell futureCell = exploreCellUp();
            result = result || addElementToQueue(paths, visited, current, futureCell);
            futureCell = exploreCellDown();
            result = result || addElementToQueue(paths, visited, current, futureCell);
            futureCell = exploreCellLeft();
            result = result || addElementToQueue(paths, visited, current, futureCell);
            futureCell = exploreCellRight();
            result = result || addElementToQueue(paths, visited, current, futureCell);
        }
        else {
            paths.add(current);
        }
        return result;
    }


    private Queue<LabyrinthPath> getAllPaths() {
        Queue<LabyrinthPath> paths = new LinkedBlockingQueue<>();
        boolean visited[][] = new boolean[labyrinth.getRowCount()][labyrinth.getColumnCount()];
        for (int i = 0; i < labyrinth.getRowCount(); ++i) {
            for (int j = 0; j < labyrinth.getColumnCount(); ++j) {
                visited[i][j] = false;
            }
        }
        LabyrinthPath initial = new LabyrinthPath();
        initial.addCellToPath(labyrinth.getStartCell());
        visited[labyrinth.getStartCell().getRow()][labyrinth.getStartCell().getColumn()] = true;

        paths.add(initial);

        boolean finished = false;
        while (!finished) {
            LabyrinthPath current = paths.poll();
            this.currentPosition = Iterables.getLast(current.getPath());
            finished = !(modifyList(paths, visited, current));
        }

        return paths;
    }
}

