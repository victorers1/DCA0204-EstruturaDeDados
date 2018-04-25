import java.util.LinkedList;

// A classe Mergesort a ser completada
class Mergesort {

    static void split(LinkedList<Integer> l, LinkedList<Integer> l1, LinkedList<Integer> l2) {
        	int tam;
        	tam= l.size();
        	for(int i = 0; i<tam; i++){
        		if(i<tam/2){
        			l1.add(l.get(i));
        		}
        		else {
        			l2.add(l.get(i));
        		}		
        	}
        }

    static LinkedList<Integer> merge(LinkedList<Integer> l1,
                                     LinkedList<Integer> l2) {
    	LinkedList Lista = new LinkedList();
    	int cont1 = 0; 
    	int cont2 = 0;
    	int tamanho = l1.size() +l2.size();
    
     for(int i = 0; i < tamanho; i++){
     	if((cont1<=(l1.size()-1))&&(cont2<=(l2.size()-1))){
     		if(l1.get(cont1)<l2.get(cont2)){
     			Lista.add(l1.get(cont1));
     			cont1++;
     		}else{
     			Lista.add(l2.get(cont2));
     			cont2++;
     		}
     	}else{
     		if(cont1<=(l1.size()-1)){
     			Lista.add(l1.get(cont1));
     			cont1++;
     		}else{
     			Lista.add(l2.get(cont2));
     			cont2++;
     		}
     	}
     }
     	return Lista;
    }

    static LinkedList<Integer> mergesort(LinkedList<Integer> l) {
    	LinkedList l1 = new LinkedList();
    	LinkedList l2 = new LinkedList();
    	int tamanho = l.size();
    	if(tamanho>1){
    	split(l, l1, l2);
    	l1=mergesort(l1);
    	l2=mergesort(l2);
    	return merge(l1,l2);
    }
    else{
    	return l;
        
    }
    }
}

// A classe Ex1 é fornecida fournie, para testar o código de Mergesort
public class Ex1 {

    static boolean is_sorted(LinkedList<Integer> l) {
        int v = Integer.MIN_VALUE;
        for (int x : l) {
            if (!(v <= x))
                return false;
            v = x;
        }
        return true;
    }

    static final int M = 10; // os elementos estão entre 0..M-1

    static int[] occurrences(LinkedList<Integer> l) {
        int[] occ = new int[M];
        for (int x : l)
            occ[x]++;
        return occ;
    }

    static boolean is_permut(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        int[] occ1 = occurrences(l1);
        int[] occ2 = occurrences(l2);
        for (int i = 0; i < M; i++)
            if (occ1[i] != occ2[i])
                return false;
        return true;
    }

    static void test(LinkedList<Integer> l) {
        System.out.println("           l = " + l);
        int[] old_occ = occurrences(l);
        LinkedList<Integer> sl = Mergesort.mergesort(l);
        int[] new_occ = occurrences(l);
        for (int i = 0; i < M; i++)
            if (old_occ[i] != new_occ[i]) {
                System.out.println("ERRO : mergesort modificou seu parametro");
                System.exit(1);
            }
        System.out.println("mergesort(l) = " + sl);
        if (!is_sorted(sl)) {
            System.out.println("ERRO: o resultado nao esta ordenado");
            System.exit(1);
        }
        if (!is_permut(l, sl)) {
            System.out.println("ERRO : os elementos diferem");
            System.exit(1);
        }
    }

    static LinkedList<Integer> random_list(int len) {
        LinkedList<Integer> l = new LinkedList<Integer>();
        for (int i = 0; i < len; i++)
            l.add((int) (M * Math.random()));
        return l;
    }

    public static void main(String[] args) {
        System.out.println("teste de split");
        for (int len = 0; len < 10; len++) {
            LinkedList<Integer> l = random_list(len);
            System.out.println("         l = " + l);
            int occ[] = occurrences(l);
            LinkedList<Integer> l1 = new LinkedList<Integer>(), l2 = new LinkedList<Integer>();
            Mergesort.split(l, l1, l2);
            int[] new_occ = occurrences(l);
            for (int i = 0; i < M; i++)
                if (occ[i] != new_occ[i]) {
                    System.out.println("ERRO : split modificou seu parametro (l = " + l + ")");
                    System.exit(1);
                }
            System.out.println("  split(l) = " + l1 + " / " + l2);
            int occ0[] = occurrences(l1);
            int occ1[] = occurrences(l2);
            for (int i = 0; i < M; i++)
                if (occ0[i] + occ1[i] != occ[i]) {
                    System.out.println("ERRO : os elementos diferem");
                    System.exit(1);
                }
        }
        System.out.println("teste de merge");
        for (int len = 0; len < 5; len++) {
            LinkedList<Integer> l1 = new LinkedList<Integer>(), l2 = new LinkedList<Integer>();
            for (int i = 0; i < len; i++) {
                l1.add(2 * i);
                l2.add(2 * i + 1);
            }
            System.out.println("            l1 = " + l1);
            System.out.println("            l2 = " + l2);
            int occ1[] = occurrences(l1);
            int occ2[] = occurrences(l2);
            LinkedList<Integer> l = Mergesort.merge(l1, l2);
            System.out.println("  merge(l1,l2) = " + l);
            if (!is_sorted(l)) {
                System.out.println("ERRO : o resultado nao esta ordenado");
                System.exit(1);
            }
            int occ[] = occurrences(l);
            for (int i = 0; i < M; i++)
                if (occ1[i] + occ2[i] != occ[i]) {
                    System.out.println("ERRO : os elementos diferem");
                    System.exit(1);
                }
        }
        System.out.println("teste de mergesort");
        for (int len = 0; len < 10; len++)
            for (int j = 0; j <= len; j++)
                test(random_list(len));
        System.out.println("SUCESSO");
    }

}
