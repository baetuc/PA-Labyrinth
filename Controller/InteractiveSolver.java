package Controller;

import Model.Cell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Cip on 13-Mar-16.
 */
public class InteractiveSolver extends AbstractLabyrinthSolver {

    @Override
    public Cell nextCellToExplore() throws IOException, IllegalArgumentException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
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
        while (!currentPosition.equals(labyrinth.getFinishCell())) {
            System.out.println("Introduceti o noua mutare: \n");
            try {
                Cell newCell = nextCellToExplore();
                if(!newCell.equals(currentPosition)) {
                    notifyAllObservers(newCell);
                }
            } catch (IOException e) {
                System.out.println("Inputul introdus nu a putut fi procesat. Introduceti din nou:\n");
            } catch (IllegalArgumentException e) {
                System.out.println("Inputul introdus nu este valid. Introduceti din nou una dintre valorile U, D, L, R:\n");
            }
        }
    }
}
