import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Author: Chris Castillo
 * Class: CSC 210
 * Purpose: This program reads in a command file that performs specific actions on a 
 *          garden which contains 3 types of plants (Tree/Flower/Vegetable). The commands
 *          have slight variations which perform different actions on the garden. General
 *          commands are GROW, HARVEST, PICK, CUT, PRINT
 * 
 * This program contains the public main() and several other helper functions:
 * initGarden(Scanner file) - Creates a garden object of row,col size
 * getCoord(String point) - Converts string coordinates to int[] coordinates
 * isType(String type) - Returns True if type is valid, false otherwise
 * isClassType(String class) - Returns True if class is valid, else false otherwise
 * ring input commands)
 * plant(int row, int col, Plant p) - Plant
 * (NOTE: Helper methods below vary on diffes a new plant in the garden
 * grow() - Grows plants in the garden
 * harvest() - removes vegetables from the garden
 * pick() - removes flowers from the garden
 * cut() - removes trees from the garden
 * 
 * USAGE:
 * args[0] is a command File that has various commands PRINT,HARVEST,GROW,CUT,PICK
 * (NOTE: Each line is a different command)
 * 
 * EXAMPLE INPUT:
 * row: 7
 * col: 7
 * 
 * PLANT (0,0) sunflower
 * plant (1,1) oak
 * Plant (2,2) tulip
 * Plant (4,4) tulip
 * print
 * grow 1 (3,3)
 * PRINT
 * GROW 2 (3,3)
 * GROW 1 tulip
 * Grow 2 sunflower
 * pick tulip
 * plant (3,3) oak
 * GROW 2 Tree
 * harvest (2,2)
 * pick sunflower
 * CUT
 * PRINT
 * 
 * COMMAND TYPES: The program breaks the input down into 3 command arguments
 * Argument One is Action performed (PLANT,GROW,HARVEST,CUT,PICK,PRINT)
 * Argument Two is number of times it performs the action.
 *      - (NOTE: Applicable only on GROW,HARVEST,CUT,PICK commands)
 * Command Three is where/type to perform the actions on.
 *      - (NOTE: Applicable only on GROW,HARVEST,CUT,PICK commands)
 * (ie GROW (arg[0]) 3 (arg[1]) Oak (arg[2]) --> GROW 3 OAK)
 *      - This grows the oak plants in the garden 3 times etc.
 *      
 * (NOTE: More detailed variations on the command types are found below in the helper
 * methods GROW, HARVEST, CUT, PICK)
 * 
 * All commands above are supported by this program. It assumes the input matches
 * similar formats as described above.
 */
public class PA5Main {
    
    /*
     * Public main method that takes in commands from the input file and creates a
     * garden object to perform those various commands on.
     * 
     * Purpose: Depending on the types of commands given, it will perform different
     *          actions on the garden object (ie grow,plant,print,harvest,cut,pick)
     *          
     * Input: Receives a command text file with format as ASSUMED ABOVE
     * 
     * TYPES OF COMMANDS:
     * plant
     * print
     * grow
     * harvest
     * pick
     * cut
     * 
     * (NOTE: Main file takes in and sorts commands and calls helper command functions
     * to perform the actions on the garden object)
     * 
     * If command is invalid, it will print out custom command error message
     * (ie Can't grow there)
     */
    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException {
        Scanner readFile = new Scanner(new File(args[0]));
        Garden garden = initGarden(readFile);

        if (!(garden == null)) {
            // Skips the emtpy line for the file
            readFile.nextLine();

            // Goes through the file and reads in all the commands
            while (readFile.hasNextLine()) {
                String[] command = readFile.nextLine().split(" ");

                if (command[0].toLowerCase().equals("plant")) {
                    plant(command, garden);

                } else if (command[0].toLowerCase().equals("print")) {
                    System.out.println("> PRINT");
                    garden.print();
                    System.out.print("\n");

                } else if (command[0].toLowerCase().equals("grow")) {
                    grow(command, garden);

                } else if (command[0].toLowerCase().equals("harvest")) {
                    harvest(command, garden);

                } else if (command[0].toLowerCase().equals("pick")) {
                    pick(command, garden);

                } else if (command[0].toLowerCase().equals("cut")) {
                    cut(command, garden);
                }
            } 
        }
        System.out.println("");
        readFile.close();
    }
    
    /*
     * A method that removes trees in the garden. (NOTE: DEPENDS ON CASE)
     * -General case removes ALL trees in the garden
     * -type case removes trees of specific type in the garden
     * -point case removes a tree at that specific point in the garden
     * 
     * Arguments: String[] command, Garden garden
     */
    public static void cut(String[] command, Garden garden) {
        if (command.length == 1) {
            System.out.println("> CUT\n");
            garden.cut();

            // Checks if it cuts a specific type
        } else if (isType(command[1], garden)) {
            System.out.println("> CUT " + command[1] + "\n");
            garden.cut(command[1]);

            // cuts a specific point
        } else {
            System.out.println("> CUT " + command[1] + "\n");
            int[] coords = getCoord(command[1].replaceAll("[()]", ""));
            garden.cut(coords[0], coords[1]);
        }
    }

    /*
     * A method that removes flowers in the garden. (NOTE: DEPENDS ON CASE)
     * -General case removes ALL flowers in the garden
     * -type case removes flowers of specific type in the garden
     * -point case removes the flower at that specific point in the garden
     * 
     * Arguments: String[] command, Garden garden
     */
    public static void pick(String[] command, Garden garden) {
        if (command.length == 1) {
            System.out.println("> PICK\n");
            garden.pick();

            // Checks if it picks a specific type
        } else if (isType(command[1], garden)) {
            System.out.println("> PICK " + command[1] + "\n");
            garden.pick(command[1]);

            // picks a specific point
        } else {
            System.out.println("> PICK " + command[1] + "\n");
            int[] coords = getCoord(command[1].replaceAll("[()]", ""));
            garden.pick(coords[0], coords[1]);
        }
    }

    /*
     * A method that harvests (removes) vegetables in the garden. (NOTE: DEPENDS ON CASE)
     * -General case removes all vegetables in garden.
     * -type case removes specific types of vegetables in the garden.
     * -point case removes specific vegetable at the coordinates in the garden
     * 
     * Arguments: String[] command, Garden garden
     */
    public static void harvest(String[] command, Garden garden) {
        if (command.length == 1) {
            System.out.println("> HARVEST\n");
            garden.harvest();

            // Checks if it harvests a specific type
        } else if (isType(command[1], garden)) {
            System.out.println("> HARVEST " + command[1] + "\n");
            garden.harvest(command[1]);

            // Harvests a specific point
        } else {
            System.out.println("> HARVEST " + command[1] + "\n");
            int[] coords = getCoord(command[1].replaceAll("[()]", ""));
            garden.harvest(coords[0], coords[1]);
        }
    }
    
    /*
     * A method that grows plants in the garden object based on specific cases.
     * -General Case where it grows all plants in garden by num times.
     * -classType Case where it grows plants of that class in garden num times.
     * -type case where it grows plants of specific type in garden num times.
     * -point case where it grows a plant only at a specific point in garden by
     * num times.
     * 
     * Arguments: String[] command, Garden garden
     */
    public static void grow(String[] command, Garden garden) throws ClassNotFoundException {
        int num = Integer.valueOf(command[1]);

        // Checks if it grows once for the ENTIRE garden
        if (command.length == 2) {
            System.out.println("> GROW " + num + "\n");
            garden.grow(num);

            // Checks if it grows a specific classType
        } else if (isClassType(command[2])) {
            System.out.println("> GROW " + num + " " + command[2] + "\n");
            garden.grow(command[2], num);

            // Checks if it grows a specific type only
        } else if (isType(command[2], garden)) {
            System.out.println("> GROW " + num + " " + command[2] + "\n");
            garden.grow(num, command[2]);
            
            // It grows a specific point in garden
        } else {
            System.out.println("> GROW " + num + " " + command[2] + "\n");
            int[] coords = getCoord(command[2].replaceAll("[()]", ""));
            garden.grow(num, coords[0], coords[1]);
        }
    }
    
    /*
     * A method that checks if classType is a valid Plant class type.
     * (ie Flower/Tree/Vegetable)
     * 
     * Arguments: String classType
     * 
     * Return Value: If classType is a valid class, it returns True, false if otherwise.
     */
    public static boolean isClassType(String classType) {
        if ("flowertreevegetable".contains(classType.toLowerCase())) {
            return true;
        } else return false;
    }
    
    /*
     * A method that checks if a string is of a specific type of tree/vegetable/flower.
     * 
     * Arguments: String type, Garden garden
     * 
     * Return Value: Returns True if type is a valid type, false if it isnt.
     */
    public static boolean isType(String type, Garden garden) {
        if (garden.getType(type) == null) {
            return false;
        } else return true;
    }
    
    /*
     * A method that takes in a plant command and puts a new plant in the garden.
     * (NOTE: Garden has its own plant method)
     * 
     * Arguments: String[] command, Garden garden
     */
    public static void plant(String[] command, Garden garden) {
        // Gets the coordinates of where to put the plant.
        int[] coords = getCoord(command[1].replaceAll("[()]", ""));
        // Gets the type of plant to be put in garden.
        Plant toBePlanted = garden.getType(command[2]);
        garden.plant(coords[0], coords[1], toBePlanted);
    }
    
    /*
     * A method that converts a string coordinate to an integer array where 0,1 index
     * represent x,y.
     * 
     * Arguments: String point
     */
    public static int[] getCoord(String point) {
        String[] p = point.split(",");
        return new int[] { Integer.valueOf(p[0]), Integer.valueOf(p[1]) };
    }
    
    /*
     * A method that creates a garden object of row,col size.
     * 
     * Arguments: Scanner readFile (ASSUMES First two lines of File are row: n col: y)
     */
    public static Garden initGarden(Scanner readFile) {
        String[] getRow = readFile.nextLine().split(" ");
        String[] getCol = readFile.nextLine().split(" ");
        // Garden's max col size must be less than 17
        if (Integer.valueOf(getCol[1]) > 16) {
            System.out.println("Too many plot columns.");
            return null;
        } else return new Garden(Integer.valueOf(getRow[1]), Integer.valueOf(getCol[1]));
    }
}