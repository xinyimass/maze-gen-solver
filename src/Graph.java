public class Graph {

    int numNodes;
    Node[] nodes;

    public void addEdge(int i, int j) {
        if (i<numNodes-1) {
            nodes[i].addToNeighbors(nodes[j]);
            nodes[j].addToNeighbors(nodes[i]);
            return;
        }
    }

    public Graph(int num) {
        numNodes = num;
        nodes = new Node[numNodes];
        for(int i = 0; i < numNodes; i++) {
            nodes[i] = new Node(i);
        }

        
    }

    //checks if edge exists between two nodes
    public boolean edgeExist (Node node1, Node node2) {
        return node1.contain(node2);
    }

}