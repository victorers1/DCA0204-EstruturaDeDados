// A classe DutchFlag a ser completada
class DutchFlag {

    static void swap(int[] a, int i, int j) {
       int aux;
       aux = a[i];
       a[i] = a[j];
       a[j] = aux;
    }

    static void dutch_flag(int[] a) {
    	int i = 0, b = 0, r = a.length - 1;
    	while(i<=r){
    		if(a[i] == 0){
    			swap(a,i,b);
    			i++;
    			b++;
    		}else if(a[i] == 1){
    			i++;
    		}else if(a[i] == 2){
    			swap(a,i,r);
    			r--;
    		}
    	}
    }
}

// A classe Ex2 � fornecida, para testar o c�digo de DutchFlag
class Ex2 {
    static boolean is_sorted(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (!(a[i-1] <= a[i])) return false;
        return true;
    }

    static final int M = 3; // os elementos est�o entre 0..2

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

    static void test_swap(int[] a, int i, int j) {
        int[] old = a.clone();
        System.out.println("            a = " + print(a));
        DutchFlag.swap(a, i, j);
        System.out.println("  swap(a,"+i+","+j+") = " + print(a));
        for (int k = 0; k < a.length; k++) {
            int l = k == i ? j : k == j ? i : k;
            if (a[k] != old[l]) {
                System.out.println("ERRO");
                System.exit(1);
            }
        }
    }

    static void test(int[] a) {
        System.out.println("  teste com        a = " + print(a));
        int[] occ1 = occurrences(a);
        DutchFlag.dutch_flag(a);
        int[] occ2 = occurrences(a);
        System.out.println("  dutch_flag(a) => a = " + print(a));
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
        System.out.println("teste de swap");
        test_swap(new int[] { 0, 1 }, 0, 1);
        test_swap(new int[] { 0, 1 }, 0, 0);
        test_swap(new int[] { 0, 1 }, 1, 1);
        test_swap(new int[] { 0, 1, 2 }, 0, 2);
        test_swap(new int[] { 0, 1, 2 }, 1, 2);
        test_swap(new int[] { 0, 1, 2 }, 1, 1);
        System.out.println("teste de dutch_flag");
        for (int len = 0; len < 10; len++){
            for (int j = 0; j <= len; j++) {
                int[] a = new int[len];
                for (int i = 0; i < len; i++)
                    a[i] = (int)(M * Math.random());
                test(a);
            }
        }
        System.out.println("SUCESSO");
    }

}
