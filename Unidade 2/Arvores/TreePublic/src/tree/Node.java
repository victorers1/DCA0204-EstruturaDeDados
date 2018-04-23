package tree;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Victor Emanuel
 * @param <T>
 */
public class Node<T> {

    private T data;
    private ArrayList<Node> children; // Each element of children is a child

    Node() {
        data = null;
        children = new ArrayList<>();
    }

    Node(T data) {
        this.data = data;
        children = new ArrayList<>();
    }

    public void addChild(Node<T> child) {
        children.add(child);
    }

    public void addChild(T data) {
        Node<T> child = new Node<>(data);
        children.add(child);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public String toString() {
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

        /* Código alternativo
        if (hasChildren()) {
            s = s.concat("\nFilhos: "); //s = s + "\nFilhos: ";
            Iterator<Node> it = children.iterator();

            while (it.hasNext()) {
                s = s + it.next().data.toString() + ", ";
            }
        } else{
            s = s + ", sem filhos.";
        }
         */
        return s;
    }

    Node getChild(T data) {
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
    public void setData(T data) {
        this.data = data;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

}
