import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 * Author: Chris Castillo
 * Class: CSC 210
 * Purpose: This program constructs a garden class that can hold plants. This class
 *          can have plants GROW, HARVEST, PLANT, PICK, CUT. The garden is represented
 *          as a grid of rows and cols (max col size is 16).
 * 
 * This class contains several public methods:
 * plant(int row, int col, Plant plant) - Puts a specific plant at the coordinates
 * getType(String type) - Returns the type of the string (if its a tree, veg, flower)
 * 
 * NOTE: grow() method is overloaded
 * grow() - grows every plant in the garden by 1
 * grow(int num, int row, int col) - grows plant by num times at the coordinates
 * grow(int num, String type) - Grows only specific plants of that type by num times.
 * grow(String classType, int num) - Grows plants only of specific class type by num times.
 * 
 * NOTE: harvest() method is overloaded
 * harvest() - Removes every vegetable in the garden
 * harvest(int row, int col) - Removes vegetable at the coords in the garden
 * harvest(String type) - Removes every vegetable of that specific type in garden
 * 
 * NOTE: pick() method is overloaded
 * pick() - Removes every flower in the garden
 * pick(int row, int col) - Removes flower at specific coordinate in the garden
 * pick(String type) - Removes flowers of that specific type
 * 
 * NOTE: cut() method is overloaded
 * cut() - Removes every tree in the garden
 * cut(int row, int col) - Removes tree at the specific coordinate
 * cut(String type) - Removes trees of that specific type in the garden
 * 
 * isOutofBounds(int row, int col) - Returns true/false if coordinates are out of bounds
 *                                   of the garden
 * print() - Prints out the representation of the garden object with all the plants.
 * 
 * EXAMPLE USAGE AND DECLARATION:
 * Garden myGarden = new Garden(int row, int col)
 * myGarden.plant(0,0, Oak)
 * myGarden.grow()
 * 
 * All commands above are supported by this program. It assumes all inputs for those
 * methods are of similar format as described above.
 */
public class Garden {
    // Declares private plot variable used to represent the Garden object.
    private Plant[][] plot;
    
    /*
     * A constructor that initializes the garden object with a specific row and col size.
     */
    public Garden(int row, int col) {
        plot = new Plant[row][col];
    }

    /*
     * A method that places a plant at a specific coordinate in the garden.
     * 
     * Arguments: int row, int col, Plant newPlant
     * (NOTE: If the location is out of bounds it does nothing)
     */
    public void plant(int row, int col, Plant newPlant) {
        if (!this.isOutOfBounds(row, col)) {
            plot[row][col] = newPlant;
        }
    }
    
    /*
     * A method that returns a Tree,Flower,Vegetable plant based from the input type.
     * 
     * Arguments: String type (Where type represents a type of flower,tree,vegetable)
     * 
     * Return Value: Returns a Flower/Tree/Vegetable if the input type is valid.
     */
    public Plant getType(String type) {
        Set<String> flowers = new HashSet<>();
        Collections.addAll(flowers, "iris", "lily", "rose", "daisy", "tulip", "sunflower");
        
        Set<String> trees = new HashSet<>();
        Collections.addAll(trees, "oak", "willow", "banana", "coconut", "pine");
        
        Set<String> vegetables = new HashSet<>();
        Collections.addAll(vegetables, "garlic", "zucchini", "tomato", "yam", "lettuce");
        
        if (flowers.contains(type.toLowerCase())) {
            return new Flower(type);
        } else if (trees.contains(type.toLowerCase())) {
            return new Tree(type);
        } else if (vegetables.contains(type.toLowerCase())) {
            return new Vegetable(type);
        } else return null;
    }

    // GROW METHODS
    /*
     * A method that grows every plant in the garden by n times.
     * (NOTE: Each plant has its own way of growing which is defined in Plant
     * classes)
     * (NOTE: This method is overloaded)
     * 
     * Arguments: int num
     */
    public void grow(int num) {
        // Recurses through the entire Garden
        for (int i = 0; i < plot.length; i++) {
            for (int j = 0; j < plot[i].length; j++) {
                // If the spot is a plant, it grows that by n times.
                if (!(plot[i][j] == null)) {
                    for (int k = 0; k < num; k++) {
                        plot[i][j].grow();
                    }
                }
            }
        }
    }

    /*
     * A method that grows a plant num times at a specific coordinate row,col.
     * (NOTE: This method is overloaded)
     * 
     * Arguments: int num, int row, int col
     */
    public void grow(int num, int row, int col) {
        // If out of bounds, prints error, else checks garden location for plant
        if (!this.isOutOfBounds(row, col)) {
            if (!(plot[row][col] == null)) {
                for (int k = 0; k < num; k++) {
                    plot[row][col].grow();
                }
            }
        } else System.out.println("Can't grow there.\n");
    }
    
    /*
     * A method that grows all plants of a specific type num times.
     * (NOTE: This method is overloaded)
     * 
     * Arguments: int num, String type (ie type of flower/tree/vegetable like rose,oak,etc.)
     */
    public void grow(int num, String type) {
        for (int i = 0; i < plot.length; i++) {
            for (int j = 0; j < plot[i].length; j++) {
                if (!(plot[i][j] == null)) {
                    // If the name of the plant equals the type, it grows it num times.
                    if (type.toLowerCase().equals(plot[i][j].getName().toLowerCase())) {
                        for (int k = 0; k < num; k++) {
                            plot[i][j].grow();
                        }
                    }
                }
            }
        }
    }
    
    /*
     * A method that grows only plants of a specific class (ie Tree/Flower/Vegetable)
     * num times.
     * (NOTE: This method is overloaded)
     * 
     * Arguments: String classT, int num
     */
    public void grow(String classT, int num) throws ClassNotFoundException {
        for (int i = 0; i < plot.length; i++) {
            for (int j = 0; j < plot[i].length; j++) {
                if (!(plot[i][j] == null)) {
                    // If the classType is valid (Tree/Flower/Vegetable) then grow n times
                    if (Class.forName(classT.substring(0, 1).toUpperCase()
                        + classT.substring(1).toLowerCase()) == plot[i][j].getClass()) {
                        for (int k = 0; k < num; k++) {
                            plot[i][j].grow();
                        }
                    }
                }
            }
        }
    }

    // HARVEST METHODS
    /*
     * A method that removes ALL Vegetables in the garden.
     * (NOTE: This method is overloaded)
     */
    public void harvest() {
        // Loops through entire garden
        for (int i = 0; i < plot.length; i++) {
            for (int j = 0; j < plot[i].length; j++) {
                // If the plant at [i][j] is a vegetable, it removes it from garden
                if (plot[i][j] instanceof Vegetable) {
                    plot[i][j] = null;
                }
            }
        }
    }

    /*
     * A method that removes a vegetable plant at the specific coordinates
     * (NOTE: This method is overloaded)
     * 
     * Arguments: int row, int col
     */
    public void harvest(int row, int col) {
        if (!this.isOutOfBounds(row, col)) {
            if (plot[row][col] instanceof Vegetable) {
                plot[row][col] = null;
            } else System.out.println("Can't harvest there.\n");
        } else System.out.println("Can't harvest there.\n");
    }
    
    /*
     * A method that removes all vegetable plants of a specific type (ie tomato,yam,etc.)
     * (NOTE: This method is overloaded)
     * 
     * Arguments: String type
     */
    public void harvest(String type) {
        for (int i = 0; i < plot.length; i++) {
            for (int j = 0; j < plot[i].length; j++) {
                if (plot[i][j] instanceof Vegetable) {
                    if (plot[i][j].getName().toLowerCase().equals(type.toLowerCase())) {
                        plot[i][j] = null;
                    }
                }
            }
        }
    }

    // PICK METHODS
    /*
     * A method that removes all flowers from the garden.
     * (NOTE: This method is overloaded)
     */
    public void pick() {
        for (int i = 0; i < plot.length; i++) {
            for (int j = 0; j < plot[i].length; j++) {
                if (plot[i][j] instanceof Flower) {
                    plot[i][j] = null;
                }
            }
        }
    }
    
    /*
     * A method that removes a flower at a specific coordinate row,col in the garden.
     * 
     * Arguments: int row, int col
     */
    public void pick(int row, int col) {
        if (!this.isOutOfBounds(row, col)) {
            if (plot[row][col] instanceof Flower) {
                plot[row][col] = null;
            } else System.out.println("Can't pick there.\n");
        } else System.out.println("Can't pick there.\n");
    }

    /*
     * A method that removes all flowers of a specific type from the garden
     * (NOTE: This method is overloaded)
     * 
     * Arguments: String type (ie lily, rose, daisy, etc.)
     */
    public void pick(String type) {
        for (int i = 0; i < plot.length; i++) {
            for (int j = 0; j < plot[i].length; j++) {
                if (plot[i][j] instanceof Flower) {
                    // If the name of flower equals type, it removes from garden
                    if (plot[i][j].getName().toLowerCase().equals(type.toLowerCase())) {
                        plot[i][j] = null;
                    }
                }
            }
        }
    }

    // CUT METHODS
    /*
     * A method that removes all trees from the garden
     * (NOTE: This method is overloaded)
     */
    public void cut() {
        for (int i = 0; i < plot.length; i++) {
            for (int j = 0; j < plot[i].length; j++) {
                if (plot[i][j] instanceof Tree) {
                    plot[i][j] = null;
                }
            }
        }
    }

    /*
     * A method that removes a tree at a specific coordinate row,col
     * (NOTE: This method is overloaded)
     * 
     * Arguments: int row, int col
     */
    public void cut(int row, int col) {
        if (!this.isOutOfBounds(row, col)) {
            if (plot[row][col] instanceof Tree) {
                plot[row][col] = null;
            } else System.out.println("Can't cut there.\n");
        } else System.out.println("Can't cut there.\n");
    }

    /*
     * A method that removes trees of a specific type from the garden
     * (NOTE: This method is overloaded)
     * 
     * Arguments: String type (ie oak, pine, etc.)
     */
    public void cut(String type) {
        for (int i = 0; i < plot.length; i++) {
            for (int j = 0; j < plot[i].length; j++) {
                if (plot[i][j] instanceof Tree) {
                    if (plot[i][j].getName().toLowerCase().equals(type.toLowerCase())) {
                        plot[i][j] = null;
                    }
                }
            }
        }
    }
    
    /*
     * A method that checks if a certain index row/col is out of bounds of the plot.
     * 
     * Arguments: int row, int col
     * 
     * Return Value: Returns True if it is Out of bounds. False if it isn't.
     */
    public boolean isOutOfBounds(int row, int col) {
        if (row > plot.length - 1 || col > plot[row].length - 1) {
            return true;
        } else
            return false;
    }
    
    /*
     * A method that prints out a representation of the Garden. Each plot of the garden
     * is represented by a plant (which is represented by a 5x5 grid).
     */
    public void print() {
        for (int i = 0; i < plot.length; i++) {
            // Places row tracker to represent all plant rows in the first row of garden
            int tracker = 0;
            while (tracker < 5) {
                String s = "";
                for (int j = 0; j < plot[i].length; j++) {
                    if (!(plot[i][j] == null)) {
                        s += plot[i][j].getRow(tracker);
                    } else s += ".....";
                }
                tracker++;
                System.out.println(s);
            }
        }
    }
}