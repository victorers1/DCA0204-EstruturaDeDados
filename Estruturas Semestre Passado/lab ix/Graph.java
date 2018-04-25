import java.awt.Color;
import java.io.*;
import java.util.*;

// Implementação de um grafo
public class Graph {
	final static int c = 8; 
	final int n; 
	final int m;
	final protected ArrayList<Integer>[] succ; 
	final protected ArrayList<Integer>[] pred; 
	final HashMap<Arc, Integer> weights; 
	double[][] coordinates = null; 

	// construtores
	@SuppressWarnings("unchecked")
	public Graph(int n, int m) {
		this.n = n;
		this.m = m;
		this.succ = new ArrayList[n];
		this.pred = new ArrayList[n];
		this.weights = new HashMap<Arc, Integer>(c * n);

		for (int k = 0; k < n; k++) {
			succ[k] = new ArrayList<Integer>();
			pred[k] = new ArrayList<Integer>();
		}
	}

	public Graph(int n, int m, ArrayList<Integer>[] succ, ArrayList<Integer>[] pred, HashMap<Arc, Integer> weights) {
		this.n = n;
		this.m = m;
		this.succ = succ;
		this.pred = pred;
		this.weights = weights;
	}

	@SuppressWarnings("unchecked")
	public Graph(int n, double p, int max) {
		this.n = n;
		int mtmp = 0;
		succ = new ArrayList[n];
		pred = new ArrayList[n];
		this.weights = new HashMap<Arc, Integer>(c * n);

		for (int k = 0; k < n; k++) {
			succ[k] = new ArrayList<Integer>();
			pred[k] = new ArrayList<Integer>();
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (Math.random() < p) {
					mtmp++;
					int val = (int) (max * Math.random());
					succ[i].add(j);
					pred[j].add(i);
					this.addWeightedArc(i, j, val);
				} else {
				}
			}
		}

		this.m = mtmp;
	}

	@SuppressWarnings("unchecked")
	public Graph(String file) throws Exception {
		System.out.print("Carregando rede viaria do arquivo " + file + " ... ");
		BufferedReader br = new BufferedReader(new FileReader(file));

		String dataLine = br.readLine();
		while (dataLine.charAt(0) != 'p')
			dataLine = br.readLine();

		String[] tokens = dataLine.split("\\s");
		this.n = Integer.parseInt(tokens[2]);
		this.m = Integer.parseInt(tokens[3]);

		succ = new ArrayList[n];
		pred = new ArrayList[n];
		this.weights = new HashMap<Arc, Integer>(c * n);

		for (int k = 0; k < n; k++) {
			succ[k] = new ArrayList<Integer>();
			pred[k] = new ArrayList<Integer>();
		}

		while ((dataLine = br.readLine()) != null) {
			tokens = dataLine.split("\\s");
			if (tokens[0].equals("a")) {
				int i = Integer.parseInt(tokens[1]);
				int j = Integer.parseInt(tokens[2]);
				int v = Integer.parseInt(tokens[3]);
				succ[i - 1].add(j - 1);
				pred[j - 1].add(i - 1);
				this.addWeightedArc(i - 1, j - 1, v);
			}
		}
		br.close();
		System.out.println("done");
	}


	public void setCoordinates(String file) throws Exception {
		System.out.print("Carregando coordenadas geometricas do arquivo " + file + " ... ");
		BufferedReader br = new BufferedReader(new FileReader(file));

		String dataLine = br.readLine();
		while (dataLine.charAt(0) != 'p')
			dataLine = br.readLine();

		String[] tokens = dataLine.split("\\s");
		int nPoints = Integer.parseInt(tokens[4]);
		if (nPoints != this.n){
			br.close();
			throw new Error("The number of points does not match the number of nodes in the graph");
		}

		this.coordinates = new double[this.n][2];

		while ((dataLine = br.readLine()) != null) {
			tokens = dataLine.split("\\s");
			if (tokens[0].equals("v")) {
				int node = Integer.parseInt(tokens[1]);
				double x = Double.parseDouble(tokens[2]);
				double y = Double.parseDouble(tokens[3]);
				this.coordinates[node - 1][0] = x / 1000000.;
				this.coordinates[node - 1][1] = y / 1000000.;
			}
		}
		br.close();
		System.out.println("done");
	}

	public ArrayList<Integer> successors(int i) {
		return succ[i];
	}

	public ArrayList<Integer> predecessors(int i) {
		return pred[i];
	}


	public void addWeightedArc(int i, int j, int v) {
		this.weights.put(new Arc(i, j), v);
	}


	public int value(int i, int j) {
		if (!this.weights.containsKey(new Arc(i, j)))
			return 0;
		return this.weights.get(new Arc(i, j));
	}


	public Graph reverse() {
		HashMap<Arc, Integer> map = new HashMap<Arc, Integer>(c * n);
		for (int i = 0; i < n; i++) {
			for (Integer j : this.succ[i]) {
				int val = this.value(i, j);
				map.put(new Arc(j, i), val);
			}
		}
		Graph rg=new Graph(n, m, pred, succ, map);
		rg.coordinates=coordinates;
		return rg;
	}


	public String toString() {
		String s = "n=" + n + '\n' + "m=" + m + '\n';
		for (int i = 0; i < n; i++) {
			s = s + "Node " + i + " :" + '\n';
			for (int k : succ[i])
				s = s + "   " + i + " - " + k + " (" + this.value(i, k) + ")"
						+ '\n';
		}
		return s;
	}


	public void drawGraph(Fenetre f) {
		if (f != null && this.coordinates != null) {
			for (int i = 0; i < this.n; i++) {
				double x1 = this.coordinates[i][0];
				double y1 = this.coordinates[i][1];
				for (Integer j : this.pred[i]) {
					double x2 = this.coordinates[j][0];
					double y2 = this.coordinates[j][1];
					f.addSegment(x1, y1, x2, y2, 1, Color.BLACK);
				}
				f.addPoint(x1, y1, 1, Color.BLACK);
			}
		}
	}


	public void drawSettledPoint(Fenetre f, int p){
		if (f != null && this.coordinates != null) { 
			f.addPoint(this.coordinates[p][0], this.coordinates[p][1], 3, Color.RED);
		}
	}

	public void drawUnsettledPoint(Fenetre f, int p){
		if (f != null && this.coordinates != null) { 
			f.addPoint(this.coordinates[p][0], this.coordinates[p][1], 3, Color.GREEN);
		}
	}

	
	public void drawSourceDestination(Fenetre f, int origin, int destination) {
		if (f != null && this.coordinates != null) { 
			f.addPoint(this.coordinates[origin][0], this.coordinates[origin][1], 6, Color.BLUE);
			f.addPoint(this.coordinates[destination][0], this.coordinates[destination][1], 6, Color.BLUE);
		}
	}
	

	public void drawSpecialPoint(Fenetre f, int p) {
		if (f != null && this.coordinates != null) {
			f.addPoint(this.coordinates[p][0], this.coordinates[p][1], 6, Color.BLUE);
		}
	}


	public void drawPath(Fenetre f, int[] pred, int i) {
		if (f != null && this.coordinates != null) { 
			double x1 = this.coordinates[i][0];
			double y1 = this.coordinates[i][1];
			while (pred[i] != -1 && pred[i] != i) {
				double x2 = this.coordinates[pred[i]][0];
				double y2 = this.coordinates[pred[i]][1];
				f.addSegment(x1, y1, x2, y2, 10, Color.BLUE);
				x1 = x2;
				y1 = y2;
				i = pred[i];
			}
		}
	}


	public void drawPath(Fenetre f, int[] predF, int[] predB, int x, int i, int j) {
		if (f != null && this.coordinates != null) { 
			if (i == -1) {
				drawPath(f, predF, x);			
				drawPath(f, predB, x);
				drawSpecialPoint(f, x);
			}
			else {
				f.addSegment(this.coordinates[i][0], this.coordinates[i][1], this.coordinates[j][0], this.coordinates[j][1], 10, Color.BLUE);
				drawPath(f, predF, i);
				drawPath(f, predB, j);
				drawSpecialPoint(f, i);
				drawSpecialPoint(f, j);
			}
		}
	}
}