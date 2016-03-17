package View;

import Controller.CustomInputStreamReader;
import Model.Cell;
import Model.Direction;
import Model.Labyrinth;
import Model.LabyrinthPath;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;

/**
 * Created by Cip on 13-Mar-16.
 */
public class LabyrinthTextView implements LabyrinthView {

    @Override
    public Direction getNextDirection() {
        System.out.println("Please introduce the next direction to go:");
        try(BufferedReader br = new BufferedReader(new CustomInputStreamReader(System.in))) {
            String direction = br.readLine();
            switch(direction.toUpperCase()) {
                case "U" :
                    return Direction.UP;
                case "D" :
                    return Direction.DOWN;
                case "L" :
                    return Direction.LEFT;
                case "R" :
                    return Direction.RIGHT;
                default:
                    throw new IllegalArgumentException();
            }
        }
        catch (IOException e) {
            System.out.println("The input could not be processed. Please try again:");
            return getNextDirection();
        }
        catch (IllegalArgumentException e) {
            System.out.println("The only valid inputs are U(up), D(down), L(left) and R(right). Please try again:");
            return getNextDirection();
        }
    }

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
        System.out.println("\n");
    }

    @Override
    public void printSolution(Labyrinth labyrinth, LabyrinthPath path) {
        System.out.println("The solution found is: ");
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
