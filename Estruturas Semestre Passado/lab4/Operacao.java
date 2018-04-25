package quarto_lab;

public class Operacao {
	private char code;
	private double a, b;
	
	public Operacao(double a){
		this.code = 'e';
		this.a = a;
	}
	
	public Operacao(char code, double a, double b){
		this.code = code;
		this.a = a;
		this.b = b;
	}
	
	@Override
	public String toString(){
		if(this.code == 'e')
			return a + " ";
		else
			return code + " ";
	}
	
	
}