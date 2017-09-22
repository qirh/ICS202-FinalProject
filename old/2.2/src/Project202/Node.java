package Project202;

public class Node{
	String name;
	String path;
	Comparable node;			//needless
	boolean isNull = true;
	
	public Node(String x, String y){
		name = x;
		path = y;
		isNull = false;
	}
	public Node(String x){
		path = x;
		name = x.substring(x.lastIndexOf("\\")+1);
		isNull = false;
	}
	public Node(){
		isNull = true;
	}
}

class NodeFile extends Node{
	
	public NodeFile(String x, String y, Comparable f){
		super(x,y);
		node = f;
	}
}


class NodeFolder extends Node{
	Node[] children;
	int files = -1;
	int folders = -1;
	
	public NodeFolder(String x, String y, int m, Comparable f){
		super(x,y);
		children = new Node[m];
		node = f;
	}
}
