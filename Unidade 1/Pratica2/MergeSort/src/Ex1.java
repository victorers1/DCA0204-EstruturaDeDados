/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Victor Emanuel
 */
import java.util.LinkedList;
import java.util.ListIterator;

// A classe Mergesort a ser completada
class Mergesort {

    static void split(LinkedList<Integer> l, LinkedList<Integer> l1, LinkedList<Integer> l2) {
        int meio = l.size()/2;
        
        int i;
        for(i=0; i<meio; i++)
            l1.add(l.get(i));
        
        for(int j=i; j<l.size(); j++)
            l2.add(l.get(j));

        /*
        for(int k=0; k<meio; k++){
            l1.add(l.get(k));
            l2.add(l.get(meio+k));
        }
        */
        //l1 = l.subList(0, meio);
        //l2 = l.subList(meio+1, l.size());
    }

    static LinkedList<Integer> merge(LinkedList<Integer> l1,
                                     LinkedList<Integer> l2) {
        
        LinkedList<Integer> temp = new LinkedList<>();
        
        ListIterator<Integer> iter1 = l1.listIterator();
        ListIterator<Integer> iter2 = l2.listIterator();
        int tamanho = l1.size() + l2.size();
        
        for(int i=0; i<tamanho; i++){
            if(iter1.hasNext() && iter2.hasNext()){
                if(l1.get(iter1.nextIndex()) < l2.get(iter2.nextIndex())){
                    temp.add(iter1.next());
                } else{
                    temp.add(iter2.next());
                }
            } else{
                if(iter1.hasNext()){
                   temp.add(iter1.next());
                } else{
                   temp.add(iter2.next()); 
                }
            }
        }
        return temp; // a ser completada
    }

    static LinkedList<Integer> mergesort(LinkedList<Integer> l) {
        if (l.size() > 1){
            LinkedList<Integer> l1 = new LinkedList<>();
            LinkedList<Integer> l2 = new LinkedList<>();
            
            split(l, l1, l2);
            
            mergesort(l1);
            mergesort(l2);
            
            return merge(l1,l2);
        } else {
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
        LinkedList<Integer> l = new LinkedList<>();
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
            LinkedList<Integer> l1 = new LinkedList<>(), l2 = new LinkedList<>();
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
            LinkedList<Integer> l1 = new LinkedList<>(), l2 = new LinkedList<>();
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
