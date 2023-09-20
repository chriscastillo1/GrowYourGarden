/*
 * Author: Chris Castillo
 * Class: CSC 210
 * Purpose: This program extends the Plant class to create a subclass called Vegetable
 *          that has an independent grow method. Must be initialized with a String name.
 * 
 * This class contains one public method:
 * grow() - Grows the vegetable one time (downward)
 * 
 * EXAMPLE USAGE AND DECLARATION:
 * Vegetable myVeg = new Vegetable(String name)
 * myVeg.grow() - Grows the vegetable one unit
 * 
 * (NOTE: Vegetable calls its Super constructor)
 * 
 * All commands above are supported by this program. It assumes all inputs for those
 * methods are of similar format as described above.
 */
public class Vegetable extends Plant {
    
    /*
     * Main constructor that initializes the vegetable name and starting growth place.
     * It must take in a name (which passed onto super constructor).
     */
    public Vegetable(String name) {
        super(name);
        grid[0][2] = initial;
    }

    /*
     * A method that grows the vegetable one time. (NOTE: Vegetables have
     * unique growth and they grow downward).
     */
    public void grow() {
        if (growthCount + 1 < MAX_GROWTH) {
            growthCount++;
            grid[growthCount][2] = initial;
        }
    }
}