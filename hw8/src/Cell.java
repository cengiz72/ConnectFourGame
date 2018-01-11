/**
 *
 * @author cengo
 */
public class Cell {
    private  String typeOfPlayer;
    private int row;
    private int column;

    /**
     * 
     */
    public Cell() {
        typeOfPlayer = ".";
    }

    /**
     *
     * @param x row
     * @param y column
     * @param player player type("X" or "O")
     */
    public Cell(int x,int y,String player) {
     row = x;
     column = y;
     typeOfPlayer = player;
    }
    /**
     *
     * @param yCoordinate column of Cell
     */
    public void set_column(int yCoordinate){ column = yCoordinate;}

    /**
     *
     * @param xCoordinate row of Cell object
     * 
     */
    public void set_row(int xCoordinate) { row=xCoordinate;}

    /**
     *
     * @param player
     */
    public void set_PlayerType(String player){ typeOfPlayer=player;}

    /**
     *
     * @return column of Cell
     */
    public int get_column() {
    
    return column;
    }

    /**
     *
     * @return row of Cell
     */
    public int get_row() {
    
    return row;
    }

    /**
     *
     * @return type of player("X" or "O")
     */
    public String get_player_type() {
    
    return typeOfPlayer;
    }
}
