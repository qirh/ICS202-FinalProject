package Project202;

public class Node{
	String name;
	String path;
	boolean vis = false;
	
	public Node(String x, String y){
		name = x;
		path = y;
	}
	public Node(String x){
		path = x;
		name = x.substring(x.lastIndexOf("\\")+1);
	}
}

class NodeFile extends Node{
	
	public NodeFile(String x, String y){
		super(x,y);
	}
}


class NodeFolder extends Node{
	Node[] children;
	int files = -1;		//initlize
	int folders = -1;
	boolean pr = false;
	
	public NodeFolder(String name, String path, int m){
		super(name,path);
		children = new Node[m];
	}
	public NodeFolder(String path, int m){
		super(path);
		children = new Node[m];
	}
}
