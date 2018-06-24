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
    
    static Aa rodeDir(Aa n) {
        if(n.getEsq()==null || n.getEsq().getCor()!=R){
            return n;
        }
        
        // Nomenclatura das variáveis segue a da imagem no PDF dado pelo professor
        Aa a0, a1, a2, m;
        m = n.getEsq();
        a0 = n.getEsq().getEsq();
        a1 = n.getEsq().getDir();
        a2 = n.getDir();

        return new Aa(m.getValor(), m.getCor(),
                new Aa(a0.getValor(), a0.getCor(), null, null), // Filho a esquerda de m
                new Aa(n.getValor(), n.getCor(), a1, a2)); // Filho a direita de m
    }

    static Aa rodeEsq(Aa m){
        if (m.getDir()== null || m.getDir().getDir() == null || m.getDir().getCor() != R || m.getDir().getDir().getCor() != R) {
            return m;
        }
        
        Aa n, p, a0, a1, a2, a3;
        a0 = m.getEsq();
        n = m.getDir(); 
        a1 = n.getEsq();
        p = n.getDir();
        a2 = p.getEsq();
        a3 = p.getDir();
        
        return new Aa(n.getValor(), n.getCor(), 
                new Aa(m.getValor(),m.getCor(), a0,a1), 
                new Aa(p.getValor(),p.getCor(),a2,a3));
    }
    
    static Aa insere(Aa a, int valor){
        if(a==null){
            a = new Aa(valor, R, null, null);
            return a;
        } else {
            if(valor < a.getValor()){
                a.setEsq(insere(a.getEsq(), valor));
                a = rodeDir(a);
                a = rodeEsq(a);
                return a;
            } else{
                a.setDir(insere(a.getDir(), valor));
                a = rodeDir(a);
                a = rodeEsq(a);
                return a;
            }
        }
    }
    
    static int nivel(Aa a){
        if(a==null){
            return 0;
        } else if(a.getCor()==R){
            return 0 + nivel(a.getEsq());
        } else{
            return 1 + nivel(a.getEsq());
        }
    }
    
    static Aa inserirECorrigeRaiz(Aa a, int i){
        a = insere(a, i);
        if(a.getCor() == R){
            a.setCor(N);
        }
        return a;
    }
    
    static boolean testSubArvoreAa(Aa a, int n, boolean raizPodeSerVermelha){
        if(a!=null){
            if(a.getCor()==R && raizPodeSerVermelha==false){
                return false;
            } else if(a.getCor()==R){
                return testSubArvoreAa(a.getDir(), n, false) && testSubArvoreAa(a.getDir(), n, false);
            } else{
                return testSubArvoreAa(a.getDir(), n-1, true) && testSubArvoreAa(a.getDir(), n-1, true);
            }
        } else{
            return a==null && n==0;
        }
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
        //Fenetre f = new Fenetre(a, "Arvore");
        System.out.println (infixe(a));
    }
    
    static void test2() {
        Aa a = new Aa(4, R,
                new Aa(2, R,
                        new Aa(1, N, null, null),
                        new Aa(3, N, null, null)),
                new Aa(5, N, null, null));
        Fenetre f = new Fenetre(a, "Teste2 antes");
        a = rodeDir(a);
        f = new Fenetre(a, "Teste2 depois");
    }

    static void test3() {
        Aa a = new Aa(2, N,
                new Aa(1, N, null, null),
                new Aa(4, R,
                        new Aa(3, N, null, null),
                        new Aa(6, R,
                                new Aa(5, N, null, null),
                                new Aa(7, N, null, null))));

        Fenetre f = new Fenetre(a, "Teste3 antes");
        a = rodeEsq(a);
        f = new Fenetre(a, "Teste3 depois");
    }
    
    static void test4(){
        Aa a = new Aa(2, N,
                new Aa(1, N, null, null),
                new Aa(4, R,
                        new Aa(3, N, null, null),
                        new Aa(6, R,
                                new Aa(5, N, null, null),
                                new Aa(7, N, null, null))));
        if(testSubArvoreAa(a, nivel(a), false)){
            System.out.println("É AA");
        }else{
            System.out.println("Não é AA");
        }
    }
    
    public int getValor() {
        return valor;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
    public int getCor() {
        return cor;
    }
    public void setCor(int cor) {
        this.cor = cor;
    }
    public Aa getEsq() {
        return esq;
    }
    public void setEsq(Aa esq) {
        this.esq = esq;
    }
    public Aa getDir() {
        return dir;
    }
    public static int getR() {
        return R;
    }
    public static int getN() {
        return N;
    }
    public void setDir(Aa dir) {
        this.dir = dir;
    }
    
    
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
}
