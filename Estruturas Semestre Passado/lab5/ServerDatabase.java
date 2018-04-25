package lab_cripto;

import java.math.BigInteger;
import java.util.ArrayList;

//O profi colocou extends Database, mas nao ta funcionando...

public class ServerDatabase {
	public static final ArrayList<ArrayList<Conta>> contas;
	public static final int N = 100;
	static {
		contas = new ArrayList<ArrayList<Conta>>();
		for(int i=0;i<N;i++){
			contas.add(new ArrayList<Conta>());
		}
	}
	
	public static int hashCode(String md5){
		BigInteger bi = new BigInteger(md5, 16);
		BigInteger m = new BigInteger(Integer.toString(N), 10);
		int pos;
		pos = bi.mod(m).intValue();
		return pos;
	}
	
	public static void insereConta(Conta conta){
		String aux = conta.getMd5();
		int pos = hashCode(aux);
		contas.get(pos).add(conta);
	}
	
	public static Conta getConta(String md5){
		int pos = hashCode(md5);
		int i = 0;
		while(contas.get(pos).size() > i){
			Conta aux = contas.get(pos).get(i);
			if(aux.md5.equals(md5)){
				return aux;
			}else{
				i++;
			}	
		}
		return null;
	}
	
	public static void test3()
	{
	    Conta c = new Conta("1234", "2222","1245");
	    ServerDatabase.insereConta(c);
	    String chave = SecurityProvider.md5ToServer(c);
	    System.out.println(chave);
	    Conta conta = ServerDatabase.getConta(chave);
	    System.out.println(conta);
	}
	
	
}