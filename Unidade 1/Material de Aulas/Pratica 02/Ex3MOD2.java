
// Classe Quicksort a ser completada
class Quicksort {

    static void swap(int[] a, int i, int j) {
        // a ser completada
    }

    static int partition(int[] a, int l, int r) {
        return 0; // a ser completada
    }

    static void quickrec(int[] a, int l, int r) {
        // a ser completada
    }

    static void quicksort(int[] a) {
        // a ser completada
    }

}

// A classe Ex3 é fornecida, para testar o código de Quicksort
class Ex3 {
    static boolean is_sorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (!(a[i-1] <= a[i])) return false;
        return true;
    }

    static final int M = 10; // os elementos estão entre 0..M-1

    static int[] occurrences(int[] a) {
        int[] occ = new int[M];
        for (int i = 0; i < a.length; i++)
            occ[a[i]]++;
        return occ;
    }

    static boolean is_permut(int[] occ1, int[] occ2) {
        for (int i = 0; i < M; i++)
            if (occ1[i] != occ2[i]) return false;
        return true;
    }

    static String print(int[] a) {
        String s = "[";
        for (int i = 0; i < a.length; i++)
            s += (i == 0 ? "" : ", ") + a[i];
        return s + "]";
    }

    static int[] random_array(int len) {
        int[] a = new int[len];
        for (int i = 0; i < len; i++)
            a[i] = (int)(M * Math.random());
        return a;
    }

    static void test_partition(int[] a, int l, int r) {
        int v = a[l];
        System.out.println("  teste com      a = " + print(a) + " v = " + v);
        int[] occ1 = occurrences(a);
        int m = Quicksort.partition(a,l,r);
        System.out.println("  partition(a,"+l+","+r+") = " + print(a) + " m = " + m);
        int[] occ2 = occurrences(a);
        if (!is_permut(occ1, occ2)) {
            System.out.println("ERRO : os elementos diferem");
            System.exit(1);
        }
        for (int i = l; i <= r; i++)
            if (!(i < m ? a[i] < v : a[i] >= v)) {
                System.out.println("ERRO : particionamento errado");
                System.exit(1);
            }
    }

    static void test(int[] a) {
        System.out.println("  teste com       a = " + print(a));
        int[] occ1 = occurrences(a);
        Quicksort.quicksort(a);
        int[] occ2 = occurrences(a);
        System.out.println("  quicksort(a) => a = " + print(a));
        if (!is_sorted(a)) {
            System.out.println("ERRO : o resultado nao esta ordenado");
            System.exit(1);
        }
        if (!is_permut(occ1, occ2)) {
            System.out.println("ERRO : os elementos diferem");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        System.out.println("teste de partition");
        for (int len = 0; len < 10; len++)
            for (int l = 0; l < len; l++)
                for (int r = l+1; r < len; r++)
                    test_partition(random_array(len), l, r);
        System.out.println("teste de quicksort");
        for (int len = 0; len < 10; len++)
            for (int j = 0; j <= len; j++)
                test(random_array(len));
        System.out.println("SUCESSO");
    }

}
