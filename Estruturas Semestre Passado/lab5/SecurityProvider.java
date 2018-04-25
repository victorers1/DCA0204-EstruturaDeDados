package lab_cripto;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;


public class SecurityProvider extends lab_cripto.Conta{
	
	public SecurityProvider(String nomeCliente, String saldo) {
		super(nomeCliente, saldo);
		// TODO Auto-generated constructor stub
	}
	public SecurityProvider(String agencia, String conta, String senha) {
		super(agencia, conta, senha);
		// TODO Auto-generated constructor stub
	}
	public SecurityProvider(String agencia, String conta, String senha, String saldo, String nomeCliente) {
		super(agencia, conta, senha, saldo, nomeCliente);
		// TODO Auto-generated constructor stub
	}

	public static String salt = "5a1t";
	public static String md5(String stringToConvert){
		
		String hashtext="";
		stringToConvert +=salt;
		MessageDigest m;
		try
			{
				m = MessageDigest.getInstance("MD5");
				m.reset();
				m.update(stringToConvert.getBytes());
				byte[] digest = m.digest();
				BigInteger bigInt = new BigInteger(1,digest);
				hashtext = bigInt.toString(16);
			}
		catch (NoSuchAlgorithmException ex){
			Logger.getLogger(SecurityProvider.class.getName()).
			log(Level.SEVERE, null, ex);
		}
	return hashtext;
	}	
	
	public static String md5ToServer(Conta conta){
		String stringToConvert = null;
		if(conta.agencia != null && conta.conta != null && conta.senha != null){
			stringToConvert = conta.agencia + conta.conta + conta.senha + SecurityProvider.salt;
			return md5(stringToConvert);
		}
		return null;
	}
	
	public static String[] md5ToClient(Conta conta){
		String toCrypt = conta.getNomeCliente()+" "+conta.getSaldo();
		String criptografados[] = new String[toCrypt.length()];
		for(int i=0; i < toCrypt.length(); i++){
			criptografados[i] = md5(String.valueOf(toCrypt.charAt(i)));
		}
		return criptografados;
	}
	
	public static void test1(){
		System.out.println(SecurityProvider.md5("teste"));
	}
	
	public static void test2(){
		Conta c = new Conta("1234", "2222","1245");
		System.out.println(SecurityProvider.md5ToServer(c));
	}

	public static void test4()
	{
	    Conta c = new Conta("124", "333", "1234","10", "john doe");
	    ServerDatabase.insereConta(c);
	    String chave = SecurityProvider.md5ToServer(c);
	    Conta conta = ServerDatabase.getConta(chave);
	    String chars[];
	    chars = SecurityProvider.md5ToClient(conta);
	    for(int i=0;i<chars.length;i++)
	        {
	            System.out.println(chars[i]);
	} }
	
	

	
}