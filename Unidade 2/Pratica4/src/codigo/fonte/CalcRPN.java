package codigo.fonte;

/**
 * @brief Classe que modela uma calculadora
 * @author Victor Emanuel
 */
import java.io.*;
import static java.lang.System.exit;
import java.util.NoSuchElementException;

class CalcRPN {
    // variáveis da instancia :
    // uma pilha para os cálculos
    Pilha<Double> aPilha;
    Pilha<Operacao> hist;

// construtor
    CalcRPN() {
        aPilha = new Pilha<>();
        hist = new Pilha<>();
    }

    /**
     * @brief Adição de dois elementos do topo da pilha.
     * @throws Error 
     */
    void mais() throws Error {
        try {
            Double a = aPilha.desempilha();
            Double b = aPilha.desempilha();
            aPilha.empilha(a + b);
            hist.empilha(new Operacao('+', a, b));

        } catch (NoSuchElementException e) {//ativado quando a lista está vazia e alguém tenta tirar um elemento
            throw new Error("Tentativa de acesso a lista vazia");
        }
    }

    /**
     * @brief Subtração de dois elementos do topo da pilha.
     * @throws Error 
     */
    void menos() throws Error {
        try {
            Double a = aPilha.desempilha();
            Double b = aPilha.desempilha();
            aPilha.empilha(b - a);
            hist.empilha(new Operacao('-', b, a)); // A ordem dos argumentos aqui importa para a redução no código do método cancela()
        } catch (NoSuchElementException e) {
            throw new Error("Tentativa de acesso a lista vazia");
        }
    }

    /**
     * @brief Multiplicação de dois elementos do topo da pilha.
     * @throws Error 
     */
    void vezes() throws Error {
        try {
            Double a = aPilha.desempilha();
            Double b = aPilha.desempilha();
            aPilha.empilha(a * b);
            hist.empilha(new Operacao('*', a, b));

        } catch (NoSuchElementException e) {
            throw new Error("Tentativa de acesso a lista vazia");
        }
    }
    
    /**
     * @brief Divisão de dois elementos no topo da pilha.
     * @throws Error 
     */
    void dividido() throws Error {
        try {
            Double a = aPilha.desempilha();
            Double b = aPilha.desempilha();
            aPilha.empilha(b / a);
            hist.empilha(new Operacao('/', b, a));
        } catch (NoSuchElementException e1) {
            throw new Error("Tentativa de acesso a lista vazia");
        } catch (ArithmeticException e2) {
            throw new Error("Tentativa de operação inválida");
        }
    }

    /**
     * @brief Retorna o conteudo do topo da pilha.
     * @return Primeiro item da pilha.
     * @throws Error 
     */
    Double resultado() throws Error {
        try {
            return aPilha.topo();
        } catch (NoSuchElementException e) {
            throw new Error("Tentativa de acesso a lista vazia");
        }

    }

    /**
     * @brief Interpretador de comandos.
     * @param cmd String com um dos comandos conhecidos a ser executado. Caso seja um número, deve apenas empilhá-lo.
     */
    void exec(String cmd) {
        switch (cmd) {
            case "+":
                try {
                    mais(); // esse método pode retornar "Error"
                } catch (Error e) {
                    System.out.println(e.toString()); // O tratamento é feito imprimindo na tela a mensagem do "Error"
                    //throw new Error(e); //repassamos o erro para a função que chamou
                    exit(0);
                }
                break;
            case "-":
                try {
                    menos();
                } catch (Error e) {
                    System.out.println(e.toString());
                    exit(0);
                }
                break;
            case "*":
                try {
                    vezes();
                } catch (Error e) {
                    System.out.println(e.toString());
                    exit(0);
                }

                break;
            case "/":
                try {
                    dividido();
                } catch (Error e) {
                    System.out.println(e.toString());
                    exit(0);
                }
                break;
            case "clear":
                aPilha.clear(); //não precisa de try, comando sempre dá certo
                hist.clear();
                break;
            case "hist":
                System.out.println("Histórico = " + hist.toStringInverse());
                break;
            case "undo":
                try{
                    cancela();
                } catch(Error e){
                    System.out.println(e.toString());
                    exit(0);
                }
                break;
            default: //se não é um operador, cmd é um Double             
                try {
                    Double temp = Double.parseDouble(cmd);
                    aPilha.empilha(temp); // Optei por usar a função pois sei quais exceções ela retorna
                    hist.empilha(new Operacao(temp));
                    //comando alternativo: aPilha.empilha(new Double(cmd));
                } catch (NumberFormatException e1) { //caso a String cmd não contenha um Double | caso a String cmd esteja vazia
                    System.out.println("Conversão impossível");
                    exit(0);
                } catch (NullPointerException e2) {
                    System.out.println("String vazia");
                    exit(0);
                }
                break;
        }
    }

    /**
     * @brief Desfaz a última ação feita pelo usuário. Desempilha um número ou desfaz a operação aritmética.
     */
    void cancela() {
        try {
            if (hist.topo().code.equals('e')) {
                aPilha.desempilha(); // Remove último elemento empilhado
                hist.desempilha();
            } else {
                aPilha.desempilha(); // Remove resultado da última operação

                aPilha.empilha(hist.topo().b); // Esta lógica serve para todas operações
                aPilha.empilha(hist.topo().a); // Pois na função menos() e dividir() a ordem de atribuição está trocada

                hist.desempilha(); // Remove última operação
            }
        } catch (NoSuchElementException e) {
            throw new Error("Tentativa de acesso a pilha vazia");
        }
    }

    /**
     * @brief Testa as operações básicas da calculadora.
     */
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

    /**
     * @brief Cria uma console no qual o usuário pode inserir os comandos reconhecíveis pela calculadora.
     * @throws IOException 
     */
    static void interfaceUsuario() throws IOException {
        CalcRPN calc = new CalcRPN();
        String line;
        System.out.println("Digite os caracteres na calculadora: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            for (String s : line.split(" ")) {
                calc.exec(s);
            }
            System.out.println("Pilha = " + calc.aPilha);
        }
        System.out.println("Até logo");
    }

    public static void main(String[] args) throws IOException {
        test();
        interfaceUsuario();
        exit(0);
    }
}
