package codigo.fonte;

/**
 * @since 22/05/2018
 * @author Victor Emanuel
 */

public class Aa {
    private static final int R=0; //RUBRO
    private static final int N=1; //NEGRO
    private int valor; // Conteúdo do nó
    private int cor; // Pode ser rubro ou negro
    private Aa esq;
    private Aa dir;
    
    /**
     *  Cria um nó da árvore AA
     * @param v inteiro conteúdo do nó
     * @param c inteiro cor do nó
     * @param esq ponteiro para filho à esquerda
     * @param dir ponteiro para filho à direita
     */
    Aa(int v, int c, Aa esq, Aa dir){
        valor = v;
        cor = c;
        this.esq = esq;
        this.dir = dir;
    }
    
    /**
     * Imprime no console os nós da árvore em ordem
     * @param a raiz da árvore consderada
     * @return String com os valores de todos os nós
     */
    static String infixe(Aa a){
        String s="";
        if(a.esq != null)
            s += infixe(a.esq);
        
        s += " " + a.valor;
        
        if(a.dir != null)
            s += infixe(a.dir);
        
        
        return s;
    }
    
    static void test1(){
        Aa a = new Aa (3, N,
                new Aa (1, N, null, null),
                new Aa (8, R,
                        new Aa (5, N,
                                null,
                                new Aa (6, R, null, null)),
                        new Aa (9, N,
                                null,
                                new Aa (11, R, null, null))));
        System.out.println (infixe(a));
    }
    
    static void test2(){
        
    }
    
    public static void main(String[] args) {
        test1();
    }
}
