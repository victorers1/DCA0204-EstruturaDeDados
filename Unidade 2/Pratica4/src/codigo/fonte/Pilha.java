package codigo.fonte;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 *
 * @author Victor Emanuel
 * @param <T>
 */
public class Pilha<T> {

    private LinkedList<T> conteudo;

    public Pilha() {
        conteudo = new LinkedList<>();
    }

    public void empilha(T dado) {
        conteudo.addFirst(dado);
    }

    public T desempilha() throws NoSuchElementException {
        //a função removeFirst() retorna a exceção acima caso não haja elementos na lista
        //por isso, não preciso chamar o throw
        return conteudo.removeFirst();
    }

    public T topo() throws NoSuchElementException {
        //a função getFirst() retorna a exceção acima caso não haja elementos na lista
        //por isso, não preciso chamar o throw
        return conteudo.getFirst();
    }

    public void clear() {
        conteudo.clear();
    }

    @Override
    public String toString() {
        return conteudo.toString();
    }

    public String toStringInverse(){
        Iterator it = conteudo.descendingIterator();
        
        String str = "[";
        while(it.hasNext()){
            str = str + it.next().toString() + ", ";
        }
        str = str +"]";
        
        return str;
    }
    
    static void test1() {
        Pilha<Double> aPilha = new Pilha<>();
        aPilha.empilha(1.1);
        aPilha.empilha(2.1);
        aPilha.empilha(3.1);
        aPilha.empilha(4.1);
        aPilha.empilha(5.1);
        Double valor;
        valor = aPilha.topo();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.topo();
        System.out.println("topo pilha = " + valor);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
    }

    static void test2() {
        Pilha<Double> aPilha = new Pilha<>();
        System.out.println(aPilha);
        aPilha.empilha(1.1);
        System.out.println(aPilha);
        aPilha.empilha(2.1);
        System.out.println(aPilha);
        aPilha.empilha(3.1);
        System.out.println(aPilha);
        Double valor;
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        System.out.println(aPilha);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        System.out.println(aPilha);
        valor = aPilha.desempilha();
        System.out.println("topo pilha = " + valor);
        System.out.println(aPilha);
    }

    public static void main(String[] args) {
        System.out.println("TESTE 1:\n");
        test1();
        System.out.println("TESTE 2:\n");
        test2();
    }
}
