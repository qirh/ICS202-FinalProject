import java.io.File;

class GreaterThanMaxException extends Exception{
	public GreaterThanMaxException() { super(); }
	public GreaterThanMaxException(String message) { super(message); }
	public GreaterThanMaxException(String message, Throwable cause) { super(message, cause); }
	public GreaterThanMaxException(Throwable cause) { super(cause); }
}


public class Tree {
	
	final static int MAX = 1000;
	File f;
	NodeFolder root;
	
	public void pr(String s){		//w/o \n
		System.out.print(s);
	}
	public void prn(String s){
		System.out.println(s);
	}
	
	public Tree(String x) {								//works good
		String y = x.substring(x.lastIndexOf("\\")+1);
		f = new File(x);
		root = new NodeFolder(y, x, MAX, f);
	}
	
	public void visit(Node p){					
		if(p != null)
			prn(p.name + " --> " + p.path);
	}
	public void scan()throws GreaterThanMaxException{
		String[] x = f.list();			//name of children
		int j = 0;
		Queue<Node> q = new Queue<Node>();
		File tmp;
		Node r;
		NodeFile s;
		NodeFolder p;
		q.enqueue(root);
		prn("Scan - start");
		
		while(! q.isEmpty()){
			
			r = q.dequeue();
			f = new File(r.path);
			x = f.list();
			
			if(x != null)
				if(x.length >= MAX)
					throw new GreaterThanMaxException("Directory has greater than max");
			
			if(f.isDirectory()){	
//				p = new NodeFolder(r.name, r.path, max,  (Comparable)f);
				p = (NodeFolder) r;
				
				for(j=0; j<x.length; j++){
					tmp = new File(p.path+"\\"+x[j]);
					if(tmp.isFile()){
						p.children[j] = new NodeFile(x[j], p.path+"\\"+x[j], new File(p.path+"\\"+x[j]));
						prn(p.name +"--> "+p.children[j].name);
					}
					else if(tmp.isDirectory()){
						p.children[j] = new NodeFolder(x[j], p.path+"\\"+x[j], MAX, new File(p.path+"\\"+x[j]));
						prn(p.name +">>> "+p.children[j].name);
					}
					p.nbr++;
					q.enqueue(p.children[j]);
				}
			}
			else{
				s = (NodeFile) r;
			}
		}
		prn("scan - end\n\n");
		
	}
	public void print(){
		String[] x = f.list();			//name of children
		Comparable[] n = f.listFiles(); //paths of children
		int i = 0, j = 0;
		Queue<Node> q = new Queue<Node>();
		File tmp;
		Node r;
		NodeFile s;
		NodeFolder p;
		q.enqueue(root);
		prn("Print - start\n");
		int m = 1, y=m;
		
		while(! q.isEmpty()){
			
			r = q.dequeue();
			f = new File(r.path);
			x = f.list();
			n = f.listFiles();
			
			if(f.isDirectory()){	
				p = (NodeFolder) r;
				prn(p.name);
				for(j=0; j<x.length; j++){
					tmp = new File(p.path+"\\"+x[j]);
					
					while(m>0){
						pr(" ");
						m--;
					}
					
					if(tmp.isFile()){
						p.children[j] = new NodeFile(x[j], p.path+"\\"+x[j], new File(p.path+"\\"+x[j]));
						prn(p.children[j].name);
					}
					else if(tmp.isDirectory()){
						p.children[j] = new NodeFolder(x[j], p.path+"\\"+x[j], MAX, new File(p.path+"\\"+x[j]));
						prn(p.children[j].name);
					}
					p.nbr++;
					q.enqueue(p.children[j]);
					m=y;
				}
				
			}
			else{
				s = (NodeFile) r;
			}
			y++;
		}
		prn("\nPrint - end");
		
	}
	public String searchPath(String h){
		String search = h; 
		boolean found = false;
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		q.enqueue(root);
		
		prn("SearchPath - start");
		prn(search);
		
		while(! q.isEmpty()){
			r = q.dequeue();
			f = new File(r.path);
			if(f.exists()){
				if(f.isDirectory()){
					p = (NodeFolder) r;
					for(Node nd : p.children)
						if(nd != null)
							q.enqueue(nd);
				}
				if(r.path.equals(search)){
					found = true;
					search = r.path;
				}
			}
		}
		prn("SearchPath - end");
		if(found){
			prn("Found  " + search+"\n");
			return search;
		}
		prn("Have Not Found " + search+"\n");
		return null;
	}
	
	public String searchName(String h){
		String search = h;		//name with .type
		
		boolean found = false;
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		q.enqueue(root);
		
		prn("SearchName - start");
		prn(search);
		
		while(! q.isEmpty()){
			r = q.dequeue();
			f = new File(r.path);
			if(f.exists()){
				if(f.isDirectory()){
					p = (NodeFolder) r;
					for(Node nd : p.children)
						if(nd != null)
							q.enqueue(nd);
					
				}
				if(r.name.equals(search)){
					found = true;
					search = r.path;
				}
			}
		}
		prn("SearchName - end");
		if(found){
			prn("Found  " + search+"\n");
			return search;
		}
		prn("Have Not Found " + search+"\n");
		return null;
	}
	
	public String searchNameW(String h){				//best fun()
		String search = h;		//name w/o .type
		
		boolean found = false;
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		q.enqueue(root);
		
		prn("SearchName - start");
		prn(search);
		
		while(! q.isEmpty()){
			r = q.dequeue();
			f = new File(r.path);
			if(f.exists()){
				if(f.isDirectory()){
					p = (NodeFolder) r;
					for(Node nd : p.children)
						if(nd != null)
							q.enqueue(nd);
					
				}
				if(r.name.contains("."))
					if(r.name.substring(0, r.name.lastIndexOf('.')).equals(search)){
						found = true;
						search = r.path;
					}
				
				if(r.name.equals(search)){
					found = true;
					search = r.path;
				}
			}
		}
		prn("SearchName - end");
		if(found){
			prn("Found  " + search+"\n");
			return search;
		}
		prn("Have Not Found " + search+"\n");
		return null;
	}
	
	public boolean insertDirect(String p, String c){		//no restrictions
		prn("inserted " + c);
		return new File(c).mkdirs();
	}
	
	public boolean insert(String p, String c){
		if(searchPath(p) == null){
			prn("1");
			return false;
		}
		else if(!c.contains(p)){
			prn("2");
			return false;
		}
		else if(searchPath(c) != null){
			prn("3");
			return false;
		}
		prn("inserted " + c);
		return new File(c).mkdir();
	}
	
	public boolean delete(String h){	//recieves a path
		if(searchName(h) == null && searchPath(h) == null)
			return false;
		prn("deleted " + h);
		return new File(h).delete();
	}

}
