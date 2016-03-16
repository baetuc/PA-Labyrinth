package Controller;

import Model.Cell;
import Model.Labyrinth;
import View.LabyrinthView;
import com.google.common.base.Preconditions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by Cip on 13-Mar-16.
 */
public class InteractiveSolver extends AbstractLabyrinthSolver {

    public InteractiveSolver(Labyrinth labyrinth, LabyrinthView view) {
        Preconditions.checkNotNull(labyrinth, "Labyrinth input must be not null at solver constructor.");
        Preconditions.checkNotNull(view, "View input must be not null at solver constructor.");
        this.labyrinth = labyrinth;
        this.view = view;
        currentPosition = labyrinth.getStartCell();
        observers = new LinkedList<>();
    }

    @Override
    public Cell nextCellToExplore() throws IOException, IllegalArgumentException {
        try (BufferedReader br = new BufferedReader(new CustomInputStreamReader(System.in))) {
            String line = br.readLine().trim();
            switch (line) {
                case "U":
                    return exploreCellUp();
                case "D":
                    return exploreCellDown();
                case "L":
                    return exploreCellLeft();
                case "R":
                    return exploreCellRight();
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void solve() {
        try {
            while (!currentPosition.equals(labyrinth.getFinishCell())) {
                System.out.println("Introduceti o noua mutare: \n");
                Cell newCell = null;
                newCell = nextCellToExplore();
                if (!currentPosition.equals(newCell)) {
                    currentPosition = newCell;
                    notifyAllObservers(newCell);
                }
            }
        } catch (IOException e) {
            System.out.println("Inputul introdus nu a putut fi procesat.");
        } catch (IllegalArgumentException e) {
            System.out.println("Inputul introdus nu este valid. Introduceti una dintre valorile U, D, L, R:\n");
        }
    }
}
