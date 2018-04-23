package tree;

import java.util.Iterator;

/**
 *
 * @author Victor Emanuel
 * @param <T>
 */
public class Tree<T> {

    private final Node<T> root;
    
    Tree(Node n){
        root = n;
    }
    
    public void dfs(){
        dfs_general(root);
    }
    
    /**
     * Realiza a percurso por profundidade
     * @param n Nó do tipo T a ser considerado como raiz
     */
    private void dfs_general(Node<T> n){
        
        // Pré-processamento opcional
        System.out.println(n.toString());
        
        Iterator<Node> it = n.getChildren().iterator();
        while(it.hasNext()){
            dfs_general(it.next());
        }
        // Pós-processamento opcional
    }
            
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Como a classe Node é pública, podemos criar a árvore manualmente
        Node<Integer> n1 = new Node<>(10);
        Node<Integer> n2 = new Node<>(20);
        Node<Integer> n3 = new Node<>(30);
        Node<Integer> n4 = new Node<>(40);
        Node<Integer> n5 = new Node<>(50);
        Node<Integer> n6 = new Node<>(60);
        Node<Integer> n7 = new Node<>(70);
        
        n1.addChild(n2);
        n1.addChild(n3);
        
        n2.addChild(n4);
        n2.addChild(n5);
        
        n3.addChild(n6);
        n3.addChild(n7);
        
        /*
        System.out.println(n1.toString());
        System.out.println(n2.toString());
        System.out.println(n3.toString());
        System.out.println(n4.toString());
        System.out.println(n5.toString());
        */
        
        Tree<Integer> arvore = new Tree<>(n1);
        arvore.dfs();
        
        
    }
    
}
