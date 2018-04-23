package treeprivate;

import java.util.Iterator;

/**
 * A classe TreePrivate implementa uma árvore binária na qual não 
 * é possível inserir diretamente novas folhas abaixo das já
 * existentes.
 * @author Victor Emanuel
 * @param <T>
 */
public class TreePrivate<T> {
    Node<T> root;
    
    TreePrivate(){
        root = null;
    }
    TreePrivate(Node<T> root){
        this.root = root;
    }
    
    /**
     * Insere uma folha aonde a árvore desejar
     * @param leaf Nó do tipo T a ser inserido
     */
    public void addLeaf(Node<T> leaf){
        //TODO
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
        
    }
    
}
