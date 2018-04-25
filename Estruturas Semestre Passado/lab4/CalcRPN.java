package quarto_lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalcRPN {
	

	Pilha<Double> aPilha;
	Pilha<Operacao> hist;
	
	
	CalcRPN () {
		aPilha = new Pilha<Double>();
		hist = new Pilha<Operacao>();
	}
	
	
	void mais() {
		Double elemento1, elemento2;
		elemento1 = aPilha.desempilha();
		elemento2 = aPilha.desempilha();
		hist.empilha(new Operacao('+', elemento1, elemento2));
		aPilha.empilha(elemento1 + elemento2);
	}
	
	
	void menos() {
		Double elemento1, elemento2;
		elemento1 = aPilha.desempilha();
		elemento2 = aPilha.desempilha();
		hist.empilha(new Operacao('-', elemento1, elemento2));
		aPilha.empilha(elemento2 - elemento1);
		
	}
	
	
	void vezes() {
		Double elemento1, elemento2;
		elemento1 = aPilha.desempilha();
		elemento2 = aPilha.desempilha();
		hist.empilha(new Operacao('*', elemento1, elemento2));
		aPilha.empilha(elemento1*elemento2);
		
	}
	
	
	void dividido() {
		Double elemento1, elemento2;
		elemento1 = aPilha.desempilha();
		elemento2 = aPilha.desempilha();
		hist.empilha(new Operacao('/', elemento1, elemento2));
		aPilha.empilha(elemento2/elemento1);
		
	}
	
	
	Double resultado() {
		return aPilha.topo();
	}
	
	
	void exec(String cmd) {
		if(cmd.equals("+"))
			mais();
		else if(cmd.equals("-"))
			menos();
		else if(cmd.equals("*"))
			vezes();
		else if(cmd.equals("/"))
			dividido();
		else if(cmd.equals("clear")){
			aPilha.reinicialize();
			hist.reinicialize();
		}	
		else if(cmd.equals("hist"))
			hist.toString();
		else
			aPilha.empilha(Double.parseDouble(cmd));
		
	}
	
	static void interfaceUsuario() throws IOException {CalcRPN calc = new CalcRPN() ;String line;BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));while((line = reader.readLine()) != null) {if (line.isEmpty())continue;for (String s : line.split(" "))calc.exec(s);System.out.println("Pilha = " + calc.aPilha);}System.out.println("At ́e logo");}
	
	
	public static void main (String[] args) throws IOException {
		interfaceUsuario();
	}
	

}