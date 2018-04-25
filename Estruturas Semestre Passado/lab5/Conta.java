package lab_cripto;

public class Conta {
	String nomeCliente = null;
	String saldo = null;
	String agencia= null;
	String conta = null;
	String senha = null;
	String md5 = null;
	
	//Construtores
	public Conta(String agencia, String conta, String senha){
		this.agencia = agencia;
		this.conta = conta;
		this.senha = senha;
		this.md5 = SecurityProvider.md5ToServer(this);
	}
	
	public Conta(String agencia, String conta, String senha, String saldo, String nomeCliente){
		this.agencia = agencia;
		this.conta = conta;
		this.senha = senha;
		this.nomeCliente = nomeCliente;
		this.saldo = saldo;
		this.md5 = SecurityProvider.md5ToServer(this);
	}
	
	public Conta(String nomeCliente, String saldo){
		this.nomeCliente = nomeCliente;
		this.saldo = saldo;
		this.md5 = SecurityProvider.md5ToServer(this);
	}
	
	//Metodos GET
	public String getNomeCliente(){
		return this.nomeCliente;
	}
	public String getSaldo(){
		return this.saldo;
	}
	public String getAgencia(){
		return this.agencia;
	}
	public String getConta(){
		return this.conta;
	}
	public String getSenha(){
		return this.senha;
	}
	public String getMd5(){
		return this.md5;
	}
	//Metodos SET
	public void setNomeCliente(String nomeCliente){
		this.nomeCliente = nomeCliente;
	}
	public void setSaldo(String saldo){
		this.saldo = saldo;
	}
	public void setAgencia(String agencia){
		this.agencia = agencia;
	}
	public void setNumero(String conta){
		this.conta = conta;
	}
	public void setSenha(String senha){
		this.senha = senha;
	}
	public void setMd5(String md5){
		this.md5 = md5;
	}
	
	@Override 
	public String toString(){
		return "AGENCIA:   " + this.agencia + "\n" +
				"CONTA:   " + this.conta + "\n" +
				"SENHA:   " + this.senha + "\n" +
				"NOME DO CLIENTE:   " + this.nomeCliente + "\n" +
				"SALDO:   " + this.saldo + "\n" +
				"MD5:   " + this.md5 + "\n" ;
	}
	
	public static void test(){
	    Conta c1 = new Conta("124", "333", "1234","10", "john doe");
	    System.out.println(c1);
	    Conta c2 = new Conta("John Doe", "10");
	    System.out.println(c2);
	    Conta c3 = new Conta("123", "321","666");
	    System.out.println(c3);
	}
	
	
	
}

