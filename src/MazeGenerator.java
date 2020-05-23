import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class MazeGenerator {

    public void run(int n) throws IOException {

        // creates all cells
        Cell[][] mazeMap = new Cell[n][n];

        initializeCells(mazeMap);

        // create a list of all internal walls, and links the cells and walls
        Wall[] walls = getWalls(mazeMap);

        createMaze(walls, mazeMap);

        printAndWriteMaze(mazeMap);

        //ask for user input whether to proceed to solving
        Scanner sc = new Scanner(System.in);

        System.out.println("Solve it? (enter y to solve, n to stop)");

        String input = sc.next();

        while (!input.equals("y") && !input.equals("n")){
            System.out.println("Please enter either y or n.");
            input = sc.next();
        }

        if (input.equals("y")){
            try {
                new MazeSolver().run("input/maze.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("OK.");
        }

    }

    public void createMaze(Wall[] walls, Cell[][] mazeMap) {

        int n = mazeMap.length;

        //make a set for every cell
        UnionFind union_find = new UnionFind();
        for(int i = 0; i<n; i++){
            for(int j =0; j<n; j++){
                union_find.makeset(mazeMap[i][j]);
            }
        }

        //join sets until there is only one set
        Random rand = new Random();
        while (!isComplete(mazeMap)) {
            int chosen_wall = rand.nextInt(2 * n * (n - 1));
            Wall chosen = walls[chosen_wall];
            if (chosen.visible) {
                if(union_find.find(chosen.first)!=union_find.find(chosen.second)) {
                    union_find.union(chosen.first, chosen.second);
                    chosen.visible = false;
                }
            }

        }

        //create entrance and exit
        mazeMap[0][0].left.visible=false;
        mazeMap[n-1][n-1].right.visible=false;

    }

    //check if there is only one set
    public boolean isComplete(Cell[][] maze){
        for (int i = 0; i<maze.length; i++){
            for(int j = 0; j<maze[i].length; j++){
                if (maze[0][0].head != maze[i][j].head){
                    return false;
                }
            }
        }
        return true;
    }


    // print out the maze in a specific format, and write the maze to file.
    public void printAndWriteMaze(Cell[][] maze) throws IOException {

        FileWriter fw = new FileWriter("input/maze.txt");
        PrintWriter pw = new PrintWriter(fw);

        pw.println(maze.length);


        for(int i = 0; i < maze.length; i++) {
            // print the up walls for row i
            for(int j = 0; j < maze.length; j++) {
                Wall up = maze[i][j].up;
                if(up != null && up.visible) {
                    System.out.print("+--");
                    pw.print("+--");
                }
                else {
                    System.out.print("+  ");
                    pw.print("+  ");
                }
            }
            System.out.println("+");
            pw.println("+");

            // print the left walls and the cells in row i
            for(int j = 0; j < maze.length; j++) {
                Wall left = maze[i][j].left;
                if(left != null && left.visible) {
                    System.out.print("|  ");
                    pw.print("|  ");
                }
                else {
                    System.out.print("   ");
                    pw.print("   ");
                }
            }

            //print the last wall on the far right of row i
            Wall lastRight = maze[i][maze.length-1].right;
            if(lastRight != null && lastRight.visible){
                System.out.println("|");
                pw.println("|");
            }
            else {
                System.out.println(" ");
                pw.println(" ");
            }
        }

        // print the last row's down walls
        for(int i = 0; i < maze.length; i++) {
            Wall down = maze[maze.length-1][i].down;
            if(down != null && down.visible) {
                System.out.print("+--");
                pw.print("+--");
            }
            else {
                System.out.print("+  ");
                pw.print("+  ");
            }
        }
        System.out.println("+");
        pw.println("+");

        pw.close();


    }


    // create a new Cell for each position of the maze
    public void initializeCells(Cell[][] maze) {
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[0].length; j++) {
                maze[i][j] = new Cell();
            }
        }
    }

    // create all walls and link walls and cells
    public Wall[] getWalls(Cell[][] mazeMap) {

        int n = mazeMap.length;

        Wall[] walls = new Wall[2*n*(n+1)];
        int wallCtr = 0;

        // each "inner" cell adds its right and down walls
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                // add down wall
                if(i < n-1) {
                    walls[wallCtr] = new Wall(mazeMap[i][j], mazeMap[i+1][j]);
                    mazeMap[i][j].down = walls[wallCtr];
                    mazeMap[i+1][j].up = walls[wallCtr];
                    wallCtr++;
                }

                // add right wall
                if(j < n-1) {
                    walls[wallCtr] = new Wall(mazeMap[i][j], mazeMap[i][j+1]);
                    mazeMap[i][j].right = walls[wallCtr];
                    mazeMap[i][j+1].left = walls[wallCtr];
                    wallCtr++;
                }
            }
        }

        // "outer" cells add their outer walls
        for(int i = 0; i < n; i++) {
            // add left walls for the first column
            walls[wallCtr] = new Wall(null, mazeMap[i][0]);
            mazeMap[i][0].left = walls[wallCtr];
            wallCtr++;

            // add up walls for the top row
            walls[wallCtr] = new Wall(null, mazeMap[0][i]);
            mazeMap[0][i].up = walls[wallCtr];
            wallCtr++;

            // add down walls for the bottom row
            walls[wallCtr] = new Wall(null, mazeMap[n-1][i]);
            mazeMap[n-1][i].down = walls[wallCtr];
            wallCtr++;

            // add right walls for the last column
            walls[wallCtr] = new Wall(null, mazeMap[i][n-1]);
            mazeMap[i][n-1].right = walls[wallCtr];
            wallCtr++;
        }


        return walls;
    }


    public static void main(String [] args) throws IOException{
        if(args.length > 0) {
            int n = Integer.parseInt(args[0]);
            new MazeGenerator().run(n);
        }
        else new MazeGenerator().run(5);
    }

}