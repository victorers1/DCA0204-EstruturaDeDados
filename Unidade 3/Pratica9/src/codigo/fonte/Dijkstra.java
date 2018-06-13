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
        naoacomodados = new PriorityQueue<>();

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE; // Distâncias para cada nó começa +inf
            pred[i] = -1; // Predecessor de cada nós não definido
            settled[i] = false; // Nenhum nó está acomodado
        }
        dist[source] = 0; // Distância do começo a si próprio é 0
        pred[source] = source; // Predecessor no começo é ele mesmo
        naoacomodados.add(new Node(source, dist[source]));
    }

    // Atualizacao da distancia, da prioridade, e do predecessor de um no
    // Considera-se que os nós em questão são são acomodados ainda
    void update(int v, int x) { //Dado que estou em x, verifica o caminho pra v
        if (dist[v] > dist[x] + g.value(x, v)) {
            dist[v] = dist[x] + g.value(x, v); //value(x, y) == value(y, x)
            pred[v] = x; // Atualização do predecessor
        }
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
            no = naoacomodados.poll(); // poll() retira e retorna o nó
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
        if(x==-1){
            return x;
        }
        settled[x] = true; // Marce-o como acomodado

        // Pressione Ctrl e clique na função successors() para ver sua implementação
        ListIterator<Integer> it = g.successors(x).listIterator(); // g.successors(i) retorna um ArrayList dos sucessores de i;

        /**
         * Para cada sucessor v de x: 
         *  Atualize(v, x); 
         * fimPara
         */
        while (it.hasNext()) {
            update(it.next(), x);
        }
        return x; //nó i que acabou de ser acomodado
    }

    /**
     * Calcula e retorna o comprimento de um caminho mais curto. Enquanto o nó t
     * de destino ainda não estiver acomodado e restar pelo menos um nó não
     * acomodado, realizamos uma iteração.
     *
     * @return o valor do menor custo
     */
    int compute() {
        int menor=oneStep(), aux;
        do{
            aux = oneStep();
            if(aux < menor) menor = aux;
        }while(!settled[dest] && !naoacomodados.isEmpty());
        
        if(menor>-1){
            return dist[dest];
        }else{
            return -1;
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
        System.out.println("Teste 2 : inicialização da classe Dijkstra");
        Graph g = new Graph("C:\\Users\\user41\\Desktop\\DCA0204-EstruturaDeDados\\Unidade 3\\Pratica9\\src\\codigo\\fonte\\mini.gr");
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

    public static void test3() throws Exception {
        System.out.println("Teste 3 : teste do algoritmo de Dijkstra");

        // petit graphe
        System.out.println("\ngrafo pequeno");
        Graph g = new Graph("C:\\Users\\user41\\Desktop\\DCA0204-EstruturaDeDados\\Unidade 3\\Pratica9\\src\\codigo\\fonte\\mini.gr");
        Dijkstra d = new Dijkstra(g, 2, 7);
        System.out.println("caminho mais curto entre 2 e 7 = " + d.compute());
        d = new Dijkstra(g, 7, 2);
        System.out.println("caminho mais curto entre 7 e 2 = " + d.compute());

        // gros graphe
        System.out.println("\ngrafo grande");
        g = new Graph("C:\\Users\\user41\\Desktop\\DCA0204-EstruturaDeDados\\Unidade 3\\Pratica9\\src\\codigo\\fonte\\USA-road-d-NY.gr");
        d = new Dijkstra(g, 190637, 187333);
        System.out.println("caminho mais curto entre 190637 e 187333 = " + d.compute());
    }

    public static void test4() throws Exception{
        System.out.println("Teste 4 : teste do algoritmo de Dijkstra");

        // preparation de la fenetre
        Graph g = new Graph("C:\\Users\\user41\\Desktop\\DCA0204-EstruturaDeDados\\Unidade 3\\Pratica9\\src\\codigo\\fonte\\USA-road-d-NY.gr");
        g.setCoordinates("C:\\Users\\user41\\Desktop\\DCA0204-EstruturaDeDados\\Unidade 3\\Pratica9\\src\\codigo\\fonte\\USA-road-d-NY.co");
        Fenetre f;
        f = new Fenetre("C:\\Users\\user41\\Desktop\\DCA0204-EstruturaDeDados\\Unidade 3\\Pratica9\\src\\codigo\\fonte\\NY_Metropolitan.png", "Dijkstra", -73.9987, -73.9437, 40.7523, 40.78085);
        g.drawGraph(f);
        f.repaint();

        // algorithme de Dijkstra
        int source = 190637, destination = 187333;
        Dijkstra d = new Dijkstra(g, source, destination);
        d.f = f;
        System.out.println("caminho mais curto entre " + source + " et " + destination + " = " + d.compute());
        g.drawSourceDestination(f, source, destination);
        g.drawPath(f, d.pred, destination);
    }

    public static void main(String[] args) throws Exception {
        test2();
        test3();
        test4();
    }
}
