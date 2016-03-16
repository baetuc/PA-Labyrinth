package View;

import Model.Cell;
import Model.Labyrinth;
import Model.LabyrinthPath;

/**
 * Created by Cip on 13-Mar-16.
 */
public class LabyrinthTextView implements LabyrinthView {

    @Override
    public void printLabyrinth(Labyrinth labyrinth, Cell currentPosition) {
        Cell startCell = labyrinth.getStartCell();
        Cell finishCell = labyrinth.getFinishCell();
        for (int i = 0; i < labyrinth.getRowCount(); ++i) {
            System.out.print("|");
            for (int j = 0; j < labyrinth.getColumnCount(); ++j) {
                if (currentPosition.getRow() == i && currentPosition.getColumn() == j) {
                    System.out.print("P|");
                } else if (labyrinth.isWallAt(i, j)) {
                    System.out.print("*|");
                } else if (labyrinth.isFreeAt(i, j)) {
                    System.out.print(" |");
                } else if (startCell.getRow() == i && startCell.getColumn() == j) {
                    System.out.print("S|");
                } else if (finishCell.getRow() == i && finishCell.getColumn() == j) {
                    System.out.print("F|");
                }
            }
            System.out.print('\n');
        }
    }

    @Override
    public void printSolution(Labyrinth labyrinth, LabyrinthPath path) {
        for (int i = 0; i < labyrinth.getRowCount(); ++i) {
            System.out.print("|");
            for (int j = 0; j < labyrinth.getColumnCount(); ++j) {
                Cell cell = new Cell(i, j);
                if (labyrinth.getStartCell().getRow() == i && labyrinth.getStartCell().getColumn() == j) {
                    System.out.print("S|");
                } else if (labyrinth.getFinishCell().getRow() == i && labyrinth.getFinishCell().getColumn() == j) {
                    System.out.print("F|");
                } else if (path.containsCell(cell)) {
                    System.out.print("X|");
                } else if (labyrinth.isWallAt(i, j)) {
                    System.out.print("*|");
                } else if (labyrinth.isFreeAt(i, j)) {
                    System.out.print(" |");
                }
            }
            System.out.print('\n');
        }
    }
}
