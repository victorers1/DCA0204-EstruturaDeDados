
// arco de um grafo
public class Arc {
	int origin; 
	int destination; 
	
	// constructeur
	public Arc(int o, int d) {
		this.origin = o;
		this.destination = d;
	}

	
	public boolean equals(Object o) {
		Arc a=(Arc)o;
		return this.origin == a.origin && this.destination == a.destination;
	}
	
	
	public int hashCode() {
		return Graph.c*this.origin + this.destination;
	}
	
}
