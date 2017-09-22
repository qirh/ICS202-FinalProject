import java.io.File;

public class Tree {
	
	public void pr(String s){		//w/o \n
		System.out.print(s);
	}
	public void prn(String s){
		System.out.println(s);
	}
	
	final static int max = 10;
	File f;
	NodeFolder root;
	
	public Tree(String x) {								//works good
		String y = x.substring(x.lastIndexOf("\\")+1);
		f = new File(x);
		root = new NodeFolder(y, x, max, f);
	}
	
	public void visit(Node p){					//works good
		if(p != null)
			pr(p.name + " ");
	}
	public void scan()throws Exception{
		String[] x = f.list();			//name of children
		Comparable[] n = f.listFiles(); //paths of children
		int i = 0, j = 0;
		Queue<Node> q = new Queue<Node>();
		File tmp;
		Node r;
		NodeFile s;
		NodeFolder p;
		q.enqueue(root);
		prn("While - start");
		
		while(! q.isEmpty()){
			
			r = q.dequeue();
			f = new File(r.path);
			x = f.list();
			n = f.listFiles();
			
			if(f.isDirectory()){	
//				p = new NodeFolder(r.name, r.path, max,  (Comparable)f);
				p = (NodeFolder) r;
				
				for(j=0; j<x.length; j++){
					tmp = new File(p.path+"\\"+x[j]);
					if(tmp.isFile()){
						p.children[j] = new NodeFile(x[j], p.path+"\\"+x[j], new File(p.path+"\\"+x[j]));
						prn(p.name +" "+j);
					}
					else if(tmp.isDirectory()){
						p.children[j] = new NodeFolder(x[j], p.path+"\\"+x[j], max, new File(p.path+"\\"+x[j]));
						prn(p.name +" "+j);
					}
					p.nbr++;
					q.enqueue(p.children[j]);
				}
			}
			else{
				s = (NodeFile) r;
			}
//			visit(r);
		}
		prn("While - end");
		
	}
	public void print(){
		f = new File(root.path);
		prn("\n"+root.name + " " + f.list().length);
		int x = 1;
		for(int i = 0; i<f.list().length; i++){
//			prn("For " + i);
//			prn(root.children[i].name);
			if(root.children[i].getClass() == root.getClass()){
				printFolder(x, (NodeFolder)root.children[i]);
			}
			else{
				printFile(x, (NodeFile) root.children[i]);
			}
		}
		
	}
	public void printFile(int x, NodeFile p){
		while(x>0){
			pr(" ");
			x--;
		}
		prn("REC " + p.name);
	}
	public void printFolder(int x, NodeFolder p){
		
	}
	public String search(Node x){
		
		return " ";
	}

}
