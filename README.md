# GrowYourGarden
Java implementation of a class hierarchy that uses polymorphism and 2D arrays

# Garden Simulation:
It will read commands like PLANT, PRINT, GROW, HARVEST from a file and execute those commands.

The garden is an M x N matrix, where each spot consists of a plot where one plant can grow. That plot is represented as a 5x5 matrix of cells.

Plants are divided up into 3 categories: Trees, Flowers, and Vegetables

# Example
INPUT FILE:
rows: 1
cols: 1
PLANT (0,0) banana
PRINT
GROW 1
print

Output:
> PRINT
.....
.....
.....
.....
..b..

> GROW 1

> PRINT
.....
.....
.....
..b..
..b..
