# Maze

## Specification
- [Overview](#overview)
- [Maze Generation](#generation)
- [Maze Solving](#solve)

### Overview
This program is designed to generate and solve mazes. It first generates a maze of a customized size and displays it on the console. It gives the solution if the user instructs it to do so. The generator and solver parts can be used separately.

### Generation
The following classes generate a maze of a customized size.

- #### `Cell.java`
A `Cell` is one square in the n*n grid that represents the maze. Each `Cell` has four `Wall`s which are either visible or invisible.

- #### `Wall.java`
A `Wall` object has two adjacent `Cell`s. Its visibility can be toggled as we generate the maze.

- #### `MazeGenerator.java`
The program begins here. It initializes an n*n 2 dimensional array of `Cell`s, each of which is linked to visible `Wall`s. It then creates the maze by implementing a Union-Find data structure on the `Cell`s (Each `Cell` becomes a separate set), randomly joining two separate sets until there is only one set left. 

It prints the maze to console and writes it to file for solving. It initializes the MazeSolver and starts the solving part if the user gives permission.

- #### `LLAddOnly.java`
An modified version of a linked list of Cell objects, which only allows add operations.

- #### `UnionFind.java`
This classe contains the methods supported by the Union-Find data structure. It is like a user-defined set interface with only three methods--`makeset`, `find`, and `union`, and specifically uses an LLAddOnly to represent each set.


### Solve

The following classes solve the maze.

- #### `Node.java`

A `Node` represents a cell in the maze grid. Each `Node` has an index that uniquely identifies it (if a graph has n nodes, then it has indices 0 through (n-1)). Each `Node` also has a list of neighbors, which are the nodes adjacent to it.

- #### `Graph.java`
The `Graph` class represents the maze grid. It has an array of `Nodes` representing each cell of the maze. It also has an `addedge` method that adds an edge between two nodes. This method is called when we build the graph.

- #### `MazeSolver.java`
The methods in this class parse the unsolved maze, generate a `Graph` based the parsed information, solve the maze, and print the maze after solving.

The `solve` emthod implements a depth-first search to find a solution path from the first cell to the last.

- #### `Stack.java`
This class implements a customized stack to help with the `solve` method in `MazeSolver.java`. 

## Example outputs
A maze of size 8
```
+--+--+--+--+--+--+--+--+
   |  |  |              |
+  +  +  +--+  +--+  +--+
|                 |     |
+  +  +  +--+--+--+--+  +
|  |  |        |     |  |
+--+--+  +--+--+--+  +  +
|     |        |  |     |
+--+  +--+  +--+  +--+  +
|        |  |  |  |     |
+  +  +--+  +  +  +  +--+
|  |  |           |     |
+  +  +--+--+--+  +--+--+
|  |              |     |
+--+--+--+  +  +  +  +--+
|           |  |         
+--+--+--+--+--+--+--+--+
Solve it? (enter y to solve, n to stop)
```
Solution:
```
+--+--+--+--+--+--+--+--+
 XX|  |  |              |
+  +  +  +--+  +--+  +--+
|XX XX XX         |     |
+  +  +  +--+--+--+--+  +
|..|..|XX      |     |  |
+--+--+  +--+--+--+  +  +
|     |XX XX   |  |     |
+--+  +--+  +--+  +--+  +
|        |XX|  |  |     |
+  +  +--+  +  +  +  +--+
|  |  |   XX XX XX|     |
+  +  +--+--+--+  +--+--+
|  |            XX|     |
+--+--+--+  +  +  +  +--+
|           |  |XX XX XX 
+--+--+--+--+--+--+--+--+

```