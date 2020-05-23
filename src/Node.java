import java.util.LinkedList;

public class Node {

    int index;

    //list of neighbors
    LinkedList<Node> list;


    // to keep track of things as solving the maze
    boolean visited = false;
    boolean inSolution = false;

   
    static final String PATH = "X";
    static final String VISIT = ".";
    static final String NOT_VISIT = " ";

    public String toString() {
        if(visited) {
            if(inSolution) return PATH;
            else return VISIT;
        }
        else return NOT_VISIT;
    }

    public void addToNeighbors (Node toAdd){
        list.add(toAdd);
    }

    public boolean contain (Node n){
        return list.contains(n);
    }

    public Node(int i) {
        index = i;
        list = new LinkedList<>();
    }

}