package treeprivate;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Victor Emanuel
 * @param <T>
 */
public class Node<T> {
    private T data;
    private ArrayList<Node> children;
    
    Node(T data){
        this.data = data;
        children = new ArrayList<>();
    }
    Node(){
        data = null;
        children = null;
    }
    
    private void addChild(Node<T> child){
        children.add(child);
    }
    private boolean hasChildren(){
        return !children.isEmpty();
    }
    
    @Override
    public String toString(){
        String s = "Pai: " + data.toString();

        Iterator<Node> it = children.iterator();
        if (hasChildren()) {
            s = s + " -> Filhos: ";
            do {
                s = s + it.next().data.toString() + ", ";
            } while (it.hasNext());
        } else{
            s = s + " -> sem filhos.";
        }
        return s;
    }
    
    private Node getChild(T data) {
        Iterator<Node> it = children.iterator();
        Node child;
        while (it.hasNext()) {
            child = it.next();
            if (data == child.data) {
                return child;
            }
        }
        return null;
    }

    public T getData() {
        return data;
    }

    private void setData(T data) {
        this.data = data;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    private void setChildren(ArrayList<Node> children) {
        this.children = children;
    }
    
    
}
