package lab6;

public class FenwickTree {
	int value, leftSize;
	FenwickTree left, right;
	
	FenwickTree(int value){
		this.value = value;
	}
	
	FenwickTree(int leftSize, FenwickTree left, FenwickTree right){
		this.leftSize = leftSize;
		this.left = left;
		this.right = right;
		this.value = this.left.value + this.right.value;
	}
	
	public String toString(){
		StringBuilder string = new StringBuilder();
		string.append("[" + this.value + ", " + this.leftSize);
		if(this.left == null){
			if(this.right == null)
				string.append("]");
			else
				string.append(", " + this.right + "]");
		}else{
			string.append(", " + this.left);
			if(this.right == null)
				string.append("]");
			else
				string.append(", " + this.right + "]");
		}
		return string.toString();
	}
	
	public static FenwickTree allZeros(int n){
		if (n==0) return null;
	    if (n==1) return new FenwickTree(0);
	    
	    int m = n/2;
	    return new FenwickTree(n-m, allZeros(n-m), allZeros(m));
	}

    public void increment(int i, int delta, FenwickTree t){

    }

    public int prefixSum(int i, FenwickTree t){
        return 0;
    }
    
    public int size(){
    	if(this.right == null){
    		return 1;
    	}
    	int a = this.leftSize;
    	while(this.right.leftSize != 0){
    		this.leftSize = this.right.leftSize;
    		this.left = this.right.left;
    		this.right = this.right.right;
    		a = a + this.leftSize;
    	}
    	return (a+1);
    }
    
    public void increment(int i, int delta){
    	int tamanho = this.size();
    	
    	this.value = this.value + delta;
    	
    	while(this.leftSize!=0){
    		
	    	if(i<(this.leftSize)){//esquerda
	    		this.leftSize = this.left.leftSize;	
	    		
	    		if(this.left.right != null)
	    			this.right = this.left.right;
	    		if(this.left.left != null)
	    			this.left = this.left.left;
	    		
	    		this.value = this.value + delta;
	    	}else{//direita
	    		i = i - this.leftSize;
	    		this.leftSize = this.right.leftSize;
	    		if(this.right.left != null)
	    			this.left = this.right.left;
	    		if(this.right.right != null)
	    			this.right = this.right.right;
	    		this.value = this.value + delta;
	    		
	    	}
	    }
    	this.value = this.value + delta;
    }
    
    public int prefixSum(int upto){
    	int a=0;
    	if(upto == 0){
    		return a;
    	} else {
        	a = this.value;
        	while(this.right.leftSize != 0){
        		this.leftSize = this.right.leftSize;
        		this.left = this.right.left;
        		this.right = this.right.right;
        		a = a + this.value;
        		
        	}
    		return a;
    	}
    }
    
    public static void main(String[] args){
    	// teste de correcao
    	System.out.println("Verificacao de correcao da funcao...");
    	FenwickTree T = new FenwickTree(3, new FenwickTree(1, new FenwickTree(4),
    	new FenwickTree(1, new FenwickTree(2), new FenwickTree(5))),
    	new FenwickTree(1, new FenwickTree(3),
    	new FenwickTree(1, new FenwickTree(6), new FenwickTree(1))));
    	System.out.println("Arvore this : " + T);
    	System.out.println("Soma das primeiras folhas : ");
    	for(int upto = 0; upto <= 6; upto++)
    	System.out.println("prefixSum(" + upto + ") : " + T.prefixSum(upto));
    	}
}
