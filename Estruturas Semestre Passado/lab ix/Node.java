
@SuppressWarnings("rawtypes")
// No de um grafo
class Node implements Comparable {

  final int id; 
  final int val;

  
  Node(int i, int v){
    id = i;
    val = v;
  }


  public int compareTo(Object o1) {
    Node n = (Node) o1;
    if (this.val == n.val) return (this.id - n.id);
    return this.val - n.val;
  }
}
