/*
 * Author: Chris Castillo
 * Class: CSC 210
 * Purpose: This program extends off Plant class to create a specialized subclass callled
 *          tree with its own specialized growth method. Must be initialized with a name.
 * 
 * This class contains one public method:
 * grow() - Grows the tree object one unit. (NOTE: Trees grow upward)
 * 
 * EXAMPLE USAGE AND DECLARATION:
 * Tree myTree = new Tree(String name)
 * myTree.grow() - Grows the tree by one unit
 * 
 * (NOTE: Tree has a specialized grow method overriding Plant class' grow)
 * 
 * All commands above are supported by this program. It assumes all inputs for those
 * methods are of similar format as described above.
 */
public class Tree extends Plant {
    
    /*
     * A constructor that initializes the tree with a name (calls superclass constructor)
     * Sets default growth point for tree at grid[4][2]
     */
    public Tree(String name) {
        super(name);
        grid[4][2] = initial;
    }

    /*
     * A method that grows a tree one unit. (ie one space).
     */
    public void grow() {
        if (growthCount + 1 < MAX_GROWTH) {
            growthCount++;
            grid[4 - growthCount][2] = initial;
        }
    }
}