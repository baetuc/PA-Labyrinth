package Main;

import Controller.AbstractLabyrinthSolver;
import Controller.AutomatedSolver;
import Controller.InteractiveSolver;
import Model.Labyrinth;
import Model.LabyrinthFactory;
import Observer.LabyrinthObserver;
import Observer.PrintObserver;
import Observer.StoreSolutionsObserver;
import View.LabyrinthTextView;
import View.LabyrinthView;

/**
 * Created by Cip on 13-Mar-16.
 */
public class Main {
    public static void main(String[] args) {
        Labyrinth labyrinth = LabyrinthFactory.recreateExample();
        LabyrinthView view = new LabyrinthTextView();
        AbstractLabyrinthSolver solver = new AutomatedSolver(labyrinth, view);


        solver.setLabyrinth(labyrinth);
        solver.setView(view);
        LabyrinthObserver observer = new PrintObserver(solver);
        LabyrinthObserver storeObserver = new StoreSolutionsObserver(solver);

        solver.solve();
    }
}
