package Observer;

import Controller.AbstractLabyrinthSolver;
import Model.Cell;
import Model.Labyrinth;
import Model.LabyrinthPath;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Cip on 17-Mar-16.
 */
public class StoreSolutionsObserver implements LabyrinthObserver {
    private AbstractLabyrinthSolver solver;
    private Queue<LabyrinthPath> completedPaths;
    private LabyrinthPath currentPath;

    public StoreSolutionsObserver(AbstractLabyrinthSolver solver) {
        this.solver = solver;
        this.solver.attach(this);
        currentPath = new LabyrinthPath();
        // lambda expression example inside comparator.
        completedPaths = new PriorityQueue<>(10, (LabyrinthPath first, LabyrinthPath second) -> {
            return first.getPath().size() - second.getPath().size();
        });
    }
    @Override
    public void update(Cell cell) {
        currentPath.addCellToPath(cell);
    }

    @Override
    public void processCell() {

    }

    @Override
    public void processSolution() {
        completedPaths.add(currentPath);
        currentPath = new LabyrinthPath();
    }
}
