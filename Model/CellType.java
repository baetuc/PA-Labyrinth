package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cip on 07-Mar-16.
 */
public enum CellType {
    FREE, WALL, START, FINISH, UNKNOWN

    static List<CellType> getUsefulCellType() {
        return Arrays.asList(START, FINISH);
    }
}


