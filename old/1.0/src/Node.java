
public class Node{
	String name;
	String path;
	Comparable node;
	boolean isNull = true;
	
	public Node(String x, String y){
		name = x;
		path = y;
		isNull = false;
	}
	public Node(){
		isNull = true;
	}
//	public Node copy(Node x){
//		return new Node(x.name, x.path);
//	}

}

class NodeFile extends Node{
	
	public NodeFile(String x, String y, Comparable f){
		super(x,y);
		node = f;
	}
}


class NodeFolder extends Node{
	Node[] children;
	int nbr = 0;
	
	public NodeFolder(String x, String y, int m, Comparable f){
		super(x,y);
		children = new Node[m];
		node = f;
//		init(m);
	}
	public NodeFolder(String x, String y, int m, int n, Comparable f){
		super(x,y);
		children = new Node[m];
		node = f;
		nbr = n;
//		init(m);
	}
//	public void init(int m){
//		for(int i = 0; i<m; i++){
//			children[i] = new Node();
//		}
//	}
}
