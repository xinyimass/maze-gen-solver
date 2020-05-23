import java.util.Vector;

public class Stack <E>{

    private Vector<E> myStack = new Vector<E>();


    public void push (E toAdd) {
        myStack.add(toAdd);
    }

    public E pop () {
        if (isEmpty()) {
            throw new NullPointerException("the stack is empty");
        } else {
            E toReturn = myStack.lastElement();
            myStack.removeElementAt(this.size() - 1);
            return toReturn;
        }
    }

    public E peek() {
        if (isEmpty()) {
            throw new NullPointerException("the stack is empty");
        } else {
            return myStack.lastElement();
        }
    }

    public int size(){
        return myStack.size();
    }

    public boolean isEmpty(){
        return myStack.isEmpty();
    }

    public String print(){
        String toPrint = "";
        for (int i = 0; i < myStack.size(); i++){
            toPrint = toPrint + myStack.elementAt(i) + " ";
        }
        return toPrint;
    }

    public void removeAll(){
        myStack.clear();
    }
}


