package quarto_lab;

import java.util.LinkedList;

public class Pilha<T>{

	private LinkedList<T> p;
	
	public Pilha(){
		p = new LinkedList<>(); 
	}

	void empilha(T x){
		p.addFirst(x);
	}
	
	T desempilha(){
		if(!p.isEmpty()){
			return p.removeFirst();
		} else{
			throw new Error("Pilha está vazia!");
		}
	}
	
	T topo(){
		if(!p.isEmpty()){
			return p.getFirst();
		}else{
			throw new Error("Pilha está vazia!");
		}
	}
	
	void reinicialize(){
		p.clear();
	}
	
	boolean estaVazia(){
		return p.isEmpty();
	}
	
	@Override 
	public String toString(){
		return p.toString();
	}
	
//	public String toStringInverse(){
//		
//	}
}

