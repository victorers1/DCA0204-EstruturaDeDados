package sistema.bancario;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Victor Emanuel
 */
public class ServerDatabase {

    public static final ArrayList<ArrayList<Conta>> CONTAS;
    public static final int N = 100;

    static {
        CONTAS = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            CONTAS.add(new ArrayList<>());
        }
    }

    public static int hashCode(String md5) {
        BigInteger bi = new BigInteger(md5, 16);
        BigInteger m = new BigInteger(Integer.toString(N), 10);
        int pos;
        pos = bi.mod(m).intValue();
        return pos;
    }

    /**
     * Recebe uma conta e salva na Tabela Hash
     *
     * @param conta item a ser adicionado
     */
    public static void insereConta(Conta conta) {
        int pos = hashCode(conta.getMd5());
        CONTAS.get(pos).add(conta); //CONTAS.get(pos) retorna uma lista encadeada
    }

    public static Conta getConta(String md5) {
        int pos = hashCode(md5); // Calcula a posição da Tabela Hash dado um md5
        ArrayList<Conta> al = CONTAS.get(pos); // "al" é o ArrayList na qual vamos procurar a conta
        Iterator<Conta> it = al.iterator(); // Iterador para percorer o ArrayList
        // O iterador é preciso pois mais de um md5 pode gerar o mesmo hashcode

        Conta c;
        while (it.hasNext()) {
            c = it.next();
            if (c.md5.equals(md5)) { // Quando o item do ArrayList tem mesmo md5 passado
                return c;
            }
        }
        return null;// Caso não encontre nenhuma conta com tal md5
    }

    public static String[] md5ToClient(Conta conta) {
        String toCrypt = conta.getNomeCliente() + " " + conta.getSaldo();
        String res[] = new String[toCrypt.length()];
        
        for(int i=0; i<toCrypt.length(); i++){
            res[i] = SecurityProvider.md5(toCrypt.substring(i, i+1));
        }
        return res;
    }

    public static void test3() {
        Conta c = new Conta("1234", "2222", "1245");
        ServerDatabase.insereConta(c);
        String chave = SecurityProvider.md5ToServer(c);
        System.out.println(chave);
        Conta conta = ServerDatabase.getConta(chave);
        System.out.println(conta);
    }

    public static void test4() {
        Conta c = new Conta("124", "333", "1234", "10", "john doe");
        ServerDatabase.insereConta(c);
        String chave = SecurityProvider.md5ToServer(c);
        Conta conta = ServerDatabase.getConta(chave);
        String chars[];
        chars = md5ToClient(conta);
        for (String char1 : chars) {
            System.out.println(char1);
        }
    }

    public static void main(String[] args) {
        System.out.println("###TESTE 1###");
        test3();
        
        System.out.println("###TESTE 2###");
        test4();
    }
}
