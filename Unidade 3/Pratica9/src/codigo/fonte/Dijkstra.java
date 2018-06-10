package codigo.fonte;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.PriorityQueue;

// Algoritmo de Dijkstra
public class Dijkstra {

    final Graph g;
    final int n; // Quantidade de nós
    final int source;  //Nó de começo
    final int dest;  //Nó de término
    int[] dist; // Contém a distância mais curta entre o nó 's' e o nó 'i'
    int[] pred; // Contém o inteiro que representa o nó predecessor ao nó 'i'
    boolean[] settled; // Indica quais nós estão acomodados
    PriorityQueue<Node> naoacomodados; // Árvore de nós não acomodados
    Fenetre f;

    // construtor
    Dijkstra(Graph g, int source, int dest) {
        this.g = g;
        n = g.n;
        this.source = source;
        this.dest = dest;
        dist = new int[n];
        pred = new int[n];
        settled = new boolean[n];

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE; // Distâncias para cada nó começa +inf
            pred[i] = -1; // Predecessor de cada nós não definido
            settled[i] = false; // Nenhum nó está acomodado
        }
        dist[source] = 0; // Distância do começo a si próprio é 0
        pred[source] = source; // Predecessor no começo é ele mesmo

    }

    // Atualizacao da distancia, da prioridade, e do predecessor de um no
    // Considera-se que os nós em questão são são acomodados ainda
    void update(int v, int x) { //Dado que estou em x, verifica o caminho pra v
        if (dist[v] > dist[x] + g.value(x, v)) {
            dist[v] = dist[x] + g.value(x, v); //value(x, y) == value(y, x)
        }
        pred[v] = x; // Atualização do predecessor
        naoacomodados.add(new Node(v, dist[v])); // S = S + {v}
    }

    /**
     * Retira de naoacomodados os nós de acordo com sua prioridade, até
     * encontrar um nó ainda não acomodado e o retorna
     *
     * @return nó não acomodado com menor dist
     */
    int nextNode() {
        Node no;
        while (!naoacomodados.isEmpty()) {
            no = naoacomodados.poll(); // poll() Retira e retorna o nó
            if (!settled[no.id]) { // Se não for um nó acomodado
                return no.id;
            }
        }
        return -1;
    }

    /**
     * Realiza uma iteração do laço principal do algoritmo de Dijsktra: ou seja,
     * extrai de S o próximo nó i a ser acomodado, marca i como acomodado,
     * atualiza todos os sucessores de i, e retorna o nó i que acaba de ser
     * acomodado.
     *
     * @return
     */
    int oneStep() {
        int x = nextNode();
        settled[x] = true; // Marce-o como acomodado

        // Pressione Ctrl e clique na função successors() para ver sua implementação
        ListIterator<Integer> it = g.successors(x).listIterator(); // g.successors(i) retorna um ArrayList dos sucessores de i;

        /**
         * Para cada sucessor v de x: Atualize(v, x); fimPara
         */
        while (it.hasNext()) {
            update(it.next(), x);
        }
        return x;
    }

    /**
     * Calcula e retorna o comprimento de um caminho mais curto. Enquanto o nó t
     * de destino ainda não estiver acomodado e restar pelo menos um nó não
     * acomodado, realizamos uma iteração.
     *
     * @return o valor do menor custo
     */
    int compute() {
        while(!settled[dest]){
            oneStep();
        }
    }

    // desacelera o visualizador
    void slow() {
        if (f == null) {
            return;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
        }
    }

    public static void test2() throws Exception {
        System.out.println("Teste 2 : inicializaÃ§Ã£o da classe Dijkstra");
        Graph g = new Graph("C:\\Users\\ana\\Documents\\OneDriveVictor\\OneDrive\\UFRN\\Estrutura de Dados\\Unidade 3\\pratica9\\Pratica9\\src\\codigo\\fonte\\mini.gr");
        Dijkstra d = new Dijkstra(g, 2, 7);
        int n = g.n;
        System.out.println("---Grafo---");
        System.out.println(g);
        System.out.println("---Dijkstra---");
        System.out.println("origem : " + d.source);
        System.out.println("destino : " + d.dest);
        for (int i = 0; i < n; i++) {
            System.out.println(i + " dist = " + d.dist[i] + "   pred = " + d.pred[i] + "   settled = " + d.settled[i]);
        }
    }

    public static void test3() {

    }

    public static void main(String[] args) {

    }
}
