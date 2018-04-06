package codigo.fonte;

import java.util.LinkedList;

/**
 *
 * @author C6-PROF
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

    public T desempilha() {
        return conteudo.removeFirst();
    }

    public T topo() {
        return conteudo.getFirst();
    }

    public void clear() {
        conteudo.clear();
    }

    @Override
    public String toString() {
        return conteudo.toString();
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
