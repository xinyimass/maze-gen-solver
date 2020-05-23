public class UnionFind {

    public void makeset (Cell c){
        LLAddOnly set = new LLAddOnly();
        set.add(c);
    }

    public LLAddOnly find (Cell c){
        return c.head;
    }

    public void union (Cell c1, Cell c2){
        LLAddOnly head1 = find(c1);
        LLAddOnly head2 = find(c2);
        if (head1==head2){
            return;
        }
        else{
            Cell current = head1.first;
            while(current!=null){
                Cell toAdd = current;
                current=current.next;
                head2.add(toAdd);
            }
        }
    }

}
