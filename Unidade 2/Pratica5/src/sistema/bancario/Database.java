package sistema.bancario;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Victor Emanuel
 */
public class Database {

    public static final ArrayList<Letra> CARACTERES;

    static {
        CARACTERES = new ArrayList<>();
        
        // Caracteres estão dispostos conforme precedência na tabela ASCII
        CARACTERES.add(new Letra(" "));
        for (Integer i=0; i<=9; i++) { // De 0 à 9
            CARACTERES.add(new Letra(i.toString()));
        }
        for (int i=65; i<=90; i++) { // De A à Z
            CARACTERES.add(new Letra(Character.toString((char) i)));
        }
        for (int i=97; i<=122; i++) { // De a à z
            CARACTERES.add(new Letra(Character.toString((char) i)));
        }
    }
    
    public static Conta getConta(String[] res){
        String nomeCliente="";
        String saldo="";
        String[] num = {"0","1","2","3","4","5","6","7","8","9"};
        String palavra[] = new String[res.length];
        for(int i=0; i<res.length; i++){
            palavra[i] = getLetra(res[i]).getCaractere();  
            for(int j=0; j<10; j++){
                if(palavra[i].equals(num[j])){
                    saldo+=palavra[i];
                    break;
                }
                else if(j==9) nomeCliente+=palavra[i];
            }
        }
        Conta conta = new Conta(nomeCliente, saldo);
        return conta;
    }

    public static Letra getLetra(String md5) {
        Iterator<Letra> it = CARACTERES.iterator();
        
        Letra le;
        while(it.hasNext()){
            le = it.next();
            if(le.md5Code.equals(md5)){
                return le;
            }
        }
        return null;
    }

    public static void test6() {
        Letra l = new Letra("a");
        String md5 = l.getMd5Code();
        System.out.println(md5);
        Letra ll = Database.getLetra(md5);
        System.out.println(ll.getCaractere());
    }

    public static void main(String[] args) {
        test6();
    }
}
