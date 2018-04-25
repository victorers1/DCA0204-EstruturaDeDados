import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.PriorityQueue;

// Algoritmo de Dijkstra
public class Dijkstra {
	final Graph g; 
	final int n; 
	final int source; 
	final int dest; 
	public Fenetre f;
	
	public int dist[];
	public int pred[];
	public boolean settled[];
	public PriorityQueue<Node> naoacomodados;

	// construtor
	Dijkstra(Graph g, int source, int   dest) {
		this.g = g;
		n = g.n;
		this.source = source;
		this.dest = dest;
		this.dist = new int[n];
		this.pred = new int[n];
		this.settled = new boolean[n];

		for(int i=0; i<n; i++){
			this.dist[i] = Integer.MAX_VALUE;
			this.pred[i] = -1;
			this.settled[i] = false;
		}
		this.pred[this.source] = this.source;
		this.dist[this.source] = 0;

		this.naoacomodados = new PriorityQueue<Node>();
	}
	
	// atualizacao da distancia, da prioridade, e do predecessor de um no
	void update(int y, int x) {
		if (this.dist[y] > this.dist[x] + this.g.value(x, y)){
			this.dist[y] = this.dist[x] + this.g.value(x, y);
			this.pred[y] = x;
			this.naoacomodados.add(new Node(y, this.dist[y]));
			g.drawUnsettledPoint(this.f,y);
		}
	}
	
	// retorna o próximo nó a ser acomodado
	int nextNode() {
		if(this.naoacomodados.isEmpty())
			return -1;
		else{
			int no = this.naoacomodados.poll().id;
			if(settled[no] == false)
				return no;
			return nextNode();
			
		}
	}
	
	// uma etapa do algoritmo de Dijkstra
	int oneStep() {
		slow();
		int no = nextNode();
		if(no == -1)
			return -1;
		else{
			this.settled[no] = true;
			g.drawSettledPoint(this.f, no);
			for(int i=0; i<this.g.successors(no).size(); i++){
				update(this.g.successors(no).get(i), no);
			}
			return no;
		}
	}
	
	// algoritmo de Dijsktra completo
	int compute() {
			this.naoacomodados.add(new Node(this.source, 0));
			g.drawUnsettledPoint(this.f,this.source);
			while(!this.settled[this.dest]){
				int no = oneStep();
				if (no == -1) 
					return -1;
			}
			return this.dist[this.dest];
		
	}
	
	// desacelera o visualizador
	void slow(){
	    if(f == null) return;
	    try {
	        Thread.sleep(5);
	    } catch (InterruptedException e) {}
	}
}
