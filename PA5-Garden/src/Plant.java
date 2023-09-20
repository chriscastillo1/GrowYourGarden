/*
 * Author: Chris Castillo
 * Class: CSC 210
 * Purpose: This program constructs a Plant Class that represents a generic plant
 *          in a garden. It has several public methods and protected variables. It has a
 *          name and each plant is contained in its own 5x5 grid.
 * 
 * This class contains several public methods:
 * getName() - Returns the name of the plant
 * grow() - Dummy grow method (Gets overridden by subclass Flower, Tree, Vegetable)
 * getRow(int n) - Returns the n row of the grid.
 * print() - Prints out the entire plant grid.
 * 
 * EXAMPLE USAGE AND DECLARATION:
 * Plant myPlant = new Plant(String name)
 * myPlant.getRow()
 * 
 * All commands above are supported by this program. It assumes all inputs for those
 * methods are of similar format as described above.
 */
public class Plant {
    
    // Several protected variables (NOTE: ALL are shared/inherited by Flower,Tree,Veg Class
    // Shares name, initial, grid, growthcount, and each plant can only grow to MAX_GROWTH
    protected String name;
    protected String initial;
    protected String[][] grid;
    protected int growthCount = 0;
    protected static final int MAX_GROWTH = 5;
    
    /*
     * A constructor that initializes the Plant with a name, initial (first letter of name)
     * and its 5x5 grid.
     */
    public Plant(String name) {
        this.name = name;
        initial = name.substring(0, 1).toLowerCase();
        grid = new String[5][5];
    }
    
    /*
     * Dummy method grow() that MUST get overridden in the subclasses Tree, Veg, Flower
     */
    public void grow() {}

    /*
     * A method that returns the plant's name
     */
    public String getName() {
        return name;
    }

    /*
     * A method that gets the specified n row from the plant grid.
     * 
     * Arguments: int row
     * 
     * Return Value: Returns a string representation of the n row in the plant grid.
     */
    public String getRow(int row) {
        String wholeRow = "";
        for (String s : grid[row]) {
            if (s == null) wholeRow += ".";
            else wholeRow += s;
        }
        return wholeRow;
    }
    
    /*
     * A public method that prints out a Plant representation (With a 5x5 grid)
     */
    public void print() {
        for (int i = 0; i < 5; i++) {
            String s = "";
            for (int j = 0; j < 5; j++) {
                if (grid[i][j] == null) s += ".";
                else s += grid[i][j];
            }
            System.out.println(s);
        }
    }
}