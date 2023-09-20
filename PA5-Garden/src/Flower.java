/*
 * Author: Chris Castillo
 * Class: CSC 210
 * Purpose: This program creates a subclass of Plant - Flower which has its own
 *          specialized growth method. Initializes with a String name (from superclass).
 *          
 * This class contains one public method and one private helper method:
 * grow() - Grows the flower by one unit. (NOTE: Overrides Plant's grow() method)
 * grow(int num) - Grows the flower by n units.
 * 
 * EXAMPLE USAGE AND DECLARATION:
 * Flower myFlower = new Flower(String name)
 * myFlower.grow() - Grows flower by 1 unit.
 *       - grow(int num) is a helper method to grow flower more than 1 unit)
 * 
 * All commands above are supported by this program. It assumes all inputs for those
 * methods are of similar format as described above.
 */
public class Flower extends Plant {
    
    /*
     * A constructor that initializes the flower name and object. (NOTE: calls the 
     * Plant super-constructor). Sets default growth point at grid[2][2].
     */
    public Flower(String name) {
        super(name);
        grid[2][2] = initial;
    }

    /*
     * A public method that grows the flower by 1 unit.
     * (NOTE: Flowers grow from the middle of grid outward)
     */
    public void grow() {
        growthCount++;
        if (growthCount < MAX_GROWTH) {
            grow(growthCount);
        }
    }
    
    /*
     * A private helper to grow the flower n units.
     * 
     * Arguments: int num
     * 
     * Since the flower grows from center outward, to grow more than 1 unit, it calls
     * the helper function to grow n times (as long as n is less than MAX_GROWTH = 5)
     */
    private void grow(int num) {
        if (num == 1) {
            grid[3][2] = grid[2][3] = grid[1][2] = grid[2][1] = initial;
        } else if (num == 2) {
            grid[4][2] = grid[2][4] = grid[0][2] = grid[2][0] = grid[1][1] = grid[3][3] = 
                    grid[1][3] = grid[3][1] = initial;
            grow(num - 1);
        } else if (num == 3) {
            grid[1][0] = grid[3][0] = grid[0][1] = grid[4][1] = grid[0][3] = grid[4][3] = 
                    grid[1][4] = grid[3][4] = initial;
            grow(num - 1);
        } else if (num == 4) {
            grid[0][0] = grid[0][4] = grid[4][0] = grid[4][4] = initial;
            grow(num - 1);
        }
    }
}