package codigo.fonte;

import java.io.*;

class CalcRPN {

    // variáveis da instancia :
    // uma pilha para os cálculos
    Pilha<Double> aPilha;
// construtor

    CalcRPN() {
        //Talvez executar o construtor da classe pilha
        //throw new Error("a ser completado");
        aPilha.clear();
    }

// Adição de dois elementos do topo da pilha
    void mais() {
        //try{
        Double a = aPilha.desempilha();
        Double b = aPilha.desempilha();
        aPilha.empilha(a + b);
        /*}  catch( INVALID OPERATION ) {
            throw new Error("a ser completado");
        }*/

    }

// Subtração de dois elementos do topo da pilha
    void menos() {
        Double a = aPilha.desempilha();
        Double b = aPilha.desempilha();
        aPilha.empilha(b - a);

        //throw new Error("a ser completado");
    }

// Multiplicação de dois elementos do topo da pilha
    void vezes() {
        Double a = aPilha.desempilha();
        Double b = aPilha.desempilha();
        aPilha.empilha(a * b);
        //throw new Error("a ser completado");
    }

// Divisão de dois elementos no topo da pilha
    void dividido() {
        Double a = aPilha.desempilha();
        Double b = aPilha.desempilha();
        aPilha.empilha(b / a);
        //throw new Error("a ser completado");
    }

// retorna o conteudo do topo da pilha
    Double resultado() {
        return aPilha.topo();
        //throw new Error("a ser completado");
    }

// interpretador de comandos
    void exec(String cmd) {
        //throw new Error("a ser completado");
    }

    static void test() {
        CalcRPN calc = new CalcRPN();
        System.out.print("3 2 + = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.mais();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("3 2 - = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.menos();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("3 2 * = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.vezes();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("3 2 / = ");
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(2.0);
        calc.dividido();
        System.out.println(calc.resultado());
        calc = new CalcRPN();
        System.out.print("1 2 + 3 4 - / 10 3 - * = ");
        calc.aPilha.empilha(1.0);
        calc.aPilha.empilha(2.0);
        calc.mais();
        calc.aPilha.empilha(3.0);
        calc.aPilha.empilha(4.0);
        calc.menos();
        calc.dividido();
        calc.aPilha.empilha(10.0);
        calc.aPilha.empilha(3.0);
        calc.menos();
        calc.vezes();
        System.out.println(calc.resultado());
    }

    public static void main(String[] args) {
        test();
    }
}
