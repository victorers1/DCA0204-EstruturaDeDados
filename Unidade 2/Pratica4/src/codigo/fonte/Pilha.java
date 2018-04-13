package codigo.fonte;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @brief Classe que implementa uma pilha simples a partir da classe LinkedList nativa do Java.
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
    
    /**
     * @brief Remove primeiro elemento da pilha
     * OBS.: A função removeFirst() retorna a exceção NoSuchElementException caso não haja elementos na lista. Por isso, não preciso usar throw nesse escopo
     * @return item removido da pilha
     * @throws NoSuchElementException
     */
    public T desempilha() throws NoSuchElementException {
        return conteudo.removeFirst();
    }

    /**
     * @brief Olha qual o primro elemento da pilha
     * OBS.: A função getFirst() retorna a exceção acima caso não haja elementos na lista. Por isso, não preciso chamar o throw
     * @return O primeiro elemento da pilha
     * @throws NoSuchElementException 
     */
    public T topo() throws NoSuchElementException {
        return conteudo.getFirst();
    }

    /**
     * @brief Remove primeiro elemento da pilha
     */
    public void clear() {
        conteudo.clear();
    }

    @Override
    public String toString() {
        return conteudo.toString();
    }

    /**
     * @brief Imprime todo conteúdo da pilha na ordem inversa
     * @return String com a representação em String de cada elemento da pilha em ordem inversa
     */
    public String toStringInverse(){
        Iterator it = conteudo.descendingIterator();
        
        String str = "[";
        while(it.hasNext()){
            str = str + it.next().toString() + ", ";
        }
        str = str +"]";
        
        return str;
    }
    
    /**
     * @brief Testa a função empilha, topo e desempilha
     */
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

    /**
     * @brief Testa a representação em String da pilha
     */
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
