package Controller;

import Model.Cell;
import Model.Direction;
import Model.Labyrinth;
import View.LabyrinthView;
import com.google.common.base.Preconditions;

import java.io.BufferedReader;
import java.io.IOException;
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
    public Cell nextCellToExplore() {
        Direction direction = view.getNextDirection();
        switch (direction) {
            case UP:
                return exploreCellUp();
            case DOWN:
                return exploreCellDown();
            case LEFT:
                return exploreCellLeft();
            case RIGHT:
                return exploreCellRight();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public void solve() {
        System.out.println("The initial look:");
        view.printLabyrinth(labyrinth, currentPosition);
        Cell newCell;
        newCell = labyrinth.getStartCell();
        while (!newCell.equals(labyrinth.getFinishCell())) {
            newCell = nextCellToExplore();
            if (!currentPosition.equals(newCell)) {
                currentPosition = newCell;
                notifyAllObservers(newCell);
            }
        }
        System.out.println("Do you want to continue another game? [Y/N]");
        boolean wantToContinue = false;
        try (BufferedReader br = new BufferedReader(new CustomInputStreamReader(System.in))) {
            String response = br.readLine();
            switch (response) {
                case "Y":
                    wantToContinue = true;
                    break;
                case "N":
                    break;
                default :
                    System.out.println("You introduced a wrong value.");
            }
        } catch (IOException e) {
            System.out.println("Error at reading from user input.");
        }
        if(wantToContinue) {
            currentPosition = labyrinth.getStartCell();
            solve();
        }
    }
}
