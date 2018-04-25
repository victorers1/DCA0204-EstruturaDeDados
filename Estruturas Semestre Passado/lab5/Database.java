package lab_cripto;

import java.util.ArrayList;

public class Database {
	public static final ArrayList<Letra> caracteres;
    static{
        caracteres = new ArrayList<Letra>();
    }
    
    public static Letra getLetra(String md5){
    	int i = 0;
    	while(caracteres.size() > i){
    		if(caracteres.get(i).md5code.equals(md5)){
    			return caracteres.get(i);
    		}
    	}
    	return null;
    }
    
    public static Conta getConta(String[] md5){
    	int i = 0;
    	String nome = null;
    	String saldo = null;
    	boolean espaco = false;
    	for(int j = 0; j < md5.length; j++){
    		while(caracteres.size() > i){
    			if(md5[j].equals(caracteres.get(i).md5code)){
    				if(caracteres.get(i).caractere == " " && caracteres.get(i+1).caractere == "1" 
    													  || caracteres.get(i+1).caractere == "2"
    													  || caracteres.get(i+1).caractere == "3"
    													  || caracteres.get(i+1).caractere == "4"
    													  || caracteres.get(i+1).caractere == "5"
    													  || caracteres.get(i+1).caractere == "6"
    													  || caracteres.get(i+1).caractere == "7"
    													  || caracteres.get(i+1).caractere == "8"
    													  || caracteres.get(i+1).caractere == "9"
    													  || caracteres.get(i+1).caractere == "0"){
    					espaco = true;
    					continue;
    				}
    				if(!espaco)
    					nome.concat(caracteres.get(i).caractere);
    				else if(espaco)
    					saldo.concat(caracteres.get(i).caractere);
    			}
    		}
    	}
    	Conta conta = new Conta(nome, saldo);
    	return conta;
    }
    
    //ADICIONAR TODOS OS CARACTERES NO DATABASE ('A', 'a', até 'Z' e ' ')
    //UTILIZE A TABELA ASCII COM UM FOR OU WHILE
    
    public static void test6()
    {
        Letra l = new Letra("a");
        String md5 = l.getMd5code();
        System.out.println(md5);
        Letra ll = Database.getLetra(md5);
        System.out.println(ll.getCaractere());
    }
    
    public static void main(String[] args) {
		test6();

	}
}
