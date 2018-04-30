package fonte;

/**
 * @author Victor Emanuel
 * @since 30/4/2018
 */
public class FenwickTree {

    /**
     * Variáveis. 
     * value contém: Para uma folha, o valor a que ela se refere; Para um nó, a soma dos valores das folhas abaixo dele. 
     * leftsize contém: Para uma folha, o valor 0; Para um nó, o número de folhas na sub-árvore esquerda. 
     * left e right contém: Para uma folha, null; Para um nó, ponteiros para as sub-árvores esquerda e direita, respectivamente.
     */
    public int value;
    public int leftsize;
    public FenwickTree left;
    public FenwickTree right;

    FenwickTree(int value) {
        this.value = value;
        left = null;
        right = null;
        leftsize = 0;
    }

    FenwickTree(int leftsize, FenwickTree left, FenwickTree right) {
        value = left.value + right.value;
        this.leftsize = leftsize;
        this.left = left;
        this.right = right;
    }

    @Override
    /**
     * Código feito fazendo vários teste cabulosos
     */
    public String toString() {
        String s = "[";
        
        s = s + Integer.toString(value)+ ", ";
        s = s + Integer.toString(leftsize);

        if (left != null) {
            s = s + ", " + left.toString();
        }
        if (right != null) {
            s = s + ", "+ right.toString();
        }
        s += "]";
        return s;
    }

    
    
    int size() {
    	if(right==null){
            return leftsize+1; //Deve somar mais um para contabilizar o próprio nó mais a direita
        } else{
            return leftsize + right.size();
        }
    }

    public void increment(int i, int delta) {
        value += delta; // Incrementa já na descida
        if (leftsize > i && left!=null) {
            left.increment(i, delta);
        } else if(leftsize > i && right!=null){
            right.increment(i - leftsize, delta);
        }
        
        /**Primeira versão do código (ERRADA):
         * value += delta;
           if (leftsize > i) {
                left.increment(i, delta);
            } else {
                right.increment(i - leftsize, delta);
            }
         */
    }

    static FenwickTree allZeros(int n) {
        if (n == 0) {
            return null;
        }
        if (n == 1) {
            return new FenwickTree(0);
        }
        int m = n / 2;
        return new FenwickTree(n - m, allZeros(n - m), allZeros(m));
    }

    public static void test1() {
        System.out.println("Construcao de FenwickTree(3) : " + new FenwickTree(3));
        System.out.println("Construcao da arvore da figura : "
                + new FenwickTree(3, new FenwickTree(1, new FenwickTree(4),
                  new FenwickTree(1, new FenwickTree(2), new FenwickTree(5))),
                  new FenwickTree(1, new FenwickTree(3),
                  new FenwickTree(1, new FenwickTree(6), new FenwickTree(1)))));
    }
    

    public static void test2() {
        System.out.println("Construcao de allZeros(3) : " + FenwickTree.allZeros(3));
        System.out.println("Construcao de allZeros(4) : " + FenwickTree.allZeros(4));
        System.out.println("Construcao de allZeros(5) : " + FenwickTree.allZeros(5));
        System.out.println("Construcao de allZeros(6) : " + FenwickTree.allZeros(6));
    }
    
    public static void test3() {
	// teste de correcao
	   System.out.println("Tamanho de FenwickTree(6) : "
	   + (new FenwickTree(6)).size());
	   System.out.println("Tamanho de allZeros(6) : "
	   + (FenwickTree.allZeros(6)).size());
	   System.out.println("Tamanho de allZeros(12) : "
	   + (FenwickTree.allZeros(12)).size());
	   FenwickTree T1 = new FenwickTree(3, new FenwickTree(1, new FenwickTree(4),
	   new FenwickTree(1, new FenwickTree(2), new FenwickTree(5))),
	   new FenwickTree(1, new FenwickTree(3),
	   new FenwickTree(1, new FenwickTree(6), new FenwickTree(1))));
	   System.out.println("Árvore this : " + T1);
	   System.out.println("Tamanho de this : " + T1.size());
   }

    public static void test4() {
        // teste de correcao
        FenwickTree T = new FenwickTree(3,
                new FenwickTree(1, new FenwickTree(0),
                        new FenwickTree(1, new FenwickTree(0), new FenwickTree(0))),
                new FenwickTree(1, new FenwickTree(0),
                        new FenwickTree(1, new FenwickTree(0), new FenwickTree(0))));
        System.out.println("Arvore this : " + T);
        T.increment(0, 4);
        System.out.println("Resultado de increment(0, 4) : " + T);
        T.increment(1, 2);
        System.out.println("Resultado de increment(1, 2) : " + T);
        T.increment(2, 5);
        System.out.println("Resultado de increment(2, 5) : " + T);
        T.increment(3, 3);
        System.out.println("Resultado de increment(3, 3) : " + T);
        T.increment(4, 6);
        System.out.println("Resultado de increment(4, 6) : " + T);
        T.increment(5, 1);
        System.out.println("Resultado de increment(5, 1) : " + T);
    }

    public static void main(String[] args) {
        System.out.println("###TESTE 1###");
        test1();
        System.out.println("###TESTE 2###");
        test2();
        System.out.println("###TESTE 3###");
        test3();
        System.out.println("###TESTE 4###");
        test4();
        
    }

}
