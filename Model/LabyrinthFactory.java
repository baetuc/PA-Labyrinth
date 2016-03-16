package Model;

import com.google.common.base.Preconditions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Cip on 10-Mar-16.
 */
public class LabyrinthFactory {
    private final static String FILE = "labyrinth.txt";


    private static Labyrinth createRandomMatrixLabyrinth(int rowSize, int columnSize) {
        Random randomGenerator = new Random();
        CellType[][] representation = new CellType[rowSize][columnSize];
        Arrays.fill(representation, CellType.UNKNOWN);
        // We create now the random Start Point and Finish Point
        int row, column;
        row = randomGenerator.nextInt(rowSize);
        column = randomGenerator.nextInt(columnSize);
        representation[row][column] = CellType.START;

        row = randomGenerator.nextInt(rowSize);
        column = randomGenerator.nextInt(columnSize);
        while (representation[row][column] == CellType.START) {
            row = randomGenerator.nextInt(rowSize);
            column = randomGenerator.nextInt(columnSize);
        }
        representation[row][column] = CellType.FINISH;

        List<CellType> VALUES = CellType.getUsefulCellType();
        int SIZE = VALUES.size();
        for (int i = 0; i < rowSize; ++i) {
            for (int j = 0; j < columnSize; ++j) {
                if (representation[i][j].equals(CellType.UNKNOWN)) {
                    representation[i][j] = VALUES.get(randomGenerator.nextInt(SIZE));
                }
            }
        }
        return new LabyrinthMatrixImpl(representation);
    }

    private static Labyrinth createRandomListLabyrinth(int rowCount, int columnCount) {
        Random randomGenerator = new Random();

        int row = randomGenerator.nextInt(rowCount);
        int column = randomGenerator.nextInt(columnCount);
        Cell startCell = new Cell(row, column);

        row = randomGenerator.nextInt(rowCount);
        column = randomGenerator.nextInt(columnCount);
        Cell finishCell = new Cell(row, column);
        while (finishCell.equals(startCell)) {
            row = randomGenerator.nextInt(rowCount);
            column = randomGenerator.nextInt(columnCount);
            finishCell = new Cell(row, column);
        }
        List<CellType> VALUES = CellType.getUsefulCellType();
        int SIZE = VALUES.size();
        Set<Cell> representation = new HashSet<>();
        for (int i = 0; i < rowCount; ++i) {
            for (int j = 0; j < columnCount; ++j) {
                CellType type = VALUES.get(randomGenerator.nextInt(SIZE));
                if (type.equals(CellType.WALL)) {
                    Cell newCell = new Cell(i, j);
                    if ((!newCell.equals(startCell)) && (!newCell.equals(finishCell))) {
                        representation.add(newCell);
                    }
                }
            }
        }
        return new LabyrinthListImpl(representation, startCell, finishCell, rowCount, columnCount);
    }

    public static Labyrinth createRandomLabyrinth() {
        Random randomGenerator = new Random();
        Labyrinth labyrinth = null;
        int row = randomGenerator.nextInt(200) + 1;
        int column = 0;
        while (row + column < 3) {
            column = randomGenerator.nextInt(200);
        }
        if (row * column < 500) {
            labyrinth = createRandomMatrixLabyrinth(row, column);
        } else {
            labyrinth = createRandomListLabyrinth(row, column);
        }
        return labyrinth;
    }

    private static CellType getCellTypeForString(String input) throws IllegalStateException {
        switch (input) {
            case "S":
                return CellType.START;
            case "F":
                return CellType.FINISH;
            case "*":
                return CellType.WALL;
            case " ":
                return CellType.FREE;
            default:
                throw new IllegalStateException();
        }
    }

    private static Labyrinth readMatrixLabyrinthFromFile(BufferedReader br, int rowCount, int columnCount)
            throws NullPointerException {
        try {
            CellType[][] representation = new CellType[rowCount][columnCount];
            for (int i = 0; i < rowCount; ++i) {
                String line = br.readLine();
                String[] cells = line.split("|");
                if (cells.length != columnCount) {
                    throw new IllegalStateException();
                }
                for (int j = 0; j < cells.length; ++j) {
                    representation[i][j] = getCellTypeForString(cells[j]);
                }
            }
            return new LabyrinthMatrixImpl(representation);
        } catch (IOException e) {
            System.out.println("Encountered a problem from reading file. The labyrinth will be generated automatically.");
            return createRandomMatrixLabyrinth(rowCount, columnCount);
        } catch (IllegalStateException e) {
            System.out.println("The input file was incorrect. The labyrinth will be generated automatically.");
            return createRandomMatrixLabyrinth(rowCount, columnCount);
        }
    }

    private static Labyrinth readListLabyrinthFromFile(BufferedReader br, int rowCount, int columnCount)
            throws NullPointerException, IllegalArgumentException {
        try {
            Set<Cell> representation = new HashSet<>();
            Cell startCell = null, finishCell = null;
            for (int i = 0; i < rowCount; ++i) {
                String line = br.readLine();
                String[] cells = line.split("|");
                if (cells.length != columnCount) {
                    throw new IllegalStateException();
                }
                for (int j = 0; j < cells.length; ++j) {
                    switch (getCellTypeForString(cells[j])) {
                        case WALL:
                            Cell wall = new Cell(i, j);
                            representation.add(wall);
                            break;
                        case START:
                            startCell = new Cell(i, j);
                            break;
                        case FINISH:
                            finishCell = new Cell(i, j);
                    }
                }
            }
            return new LabyrinthListImpl(representation, startCell, finishCell, rowCount, columnCount);
        } catch (IOException e) {
            System.out.println("Encountered a problem from reading file. The labyrinth will be generated automatically.");
            return createRandomListLabyrinth(rowCount, columnCount);
        } catch (IllegalStateException e) {
            System.out.println("The input file was incorrect. The labyrinth will be generated automatically.");
            return createRandomListLabyrinth(rowCount, columnCount);
        }
    }

    /**
     * The file must contain a valid labyrinth
     *
     * @return the labyrinth from file, if exists
     */
    public static Labyrinth readLabyrinthFromFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            int row, column;
            row = Integer.parseInt(br.readLine());
            column = Integer.parseInt(br.readLine());
            Preconditions.checkState(0 < row && 0 < column && row + column > 2, "Invalid number of rows or columns");
            if (row * column < 500) {
                return readMatrixLabyrinthFromFile(br, row, column);
            } else {
                return readListLabyrinthFromFile(br, row, column);
            }
        } catch (IOException e) {
            throw new IOException("Error at reading file.");
        }
    }

    public static Labyrinth recreateExample() {
        CellType[][] representation;
        representation = new CellType[][]{{CellType.START, CellType.WALL, CellType.WALL, CellType.WALL, CellType.WALL},
                {CellType.FREE, CellType.FREE, CellType.WALL, CellType.WALL, CellType.WALL},
                {CellType.WALL, CellType.FREE, CellType.WALL, CellType.WALL, CellType.WALL},
                {CellType.WALL, CellType.FREE, CellType.FREE, CellType.WALL, CellType.WALL},
                {CellType.WALL, CellType.WALL, CellType.FINISH, CellType.WALL, CellType.WALL}};
        return new LabyrinthMatrixImpl(representation);
    }
}
