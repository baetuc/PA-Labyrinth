package Observer;

import Controller.AbstractLabyrinthSolver;
import Model.Cell;
import Model.LabyrinthPath;

/**
 * Created by Cip on 13-Mar-16.
 */
public class PrintObserver implements LabyrinthObserver {
    private AbstractLabyrinthSolver solver;
    private LabyrinthPath path;

    public PrintObserver(AbstractLabyrinthSolver solver) {
        this.solver = solver;
        this.solver.attach(this);
        path = new LabyrinthPath();
    }

    @Override
    public void update(Cell cell) {
        path.addCellToPath(cell);
    }

    @Override
    public void processCell() {
        solver.updateView();
    }

    @Override
    public void processSolution() {
        solver.updateViewSolution(path);
    }

}
