package System.out;

import java.math.BigInteger;
import java.util.ArrayList;
import sistema.bancario.Conta;

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
    
    public static void insereConta(Conta conta){
        int pos = hashCode(conta.getMd5());
        CONTAS.get(pos).add(conta);
    }
    
    public static Conta getConta(String md5){
        
    }
    
    public static void main(String[] args) {
        
    }
}
