import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

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
		
	}/*
	public void print2(){
		for(Node nd: root.children)
			prn("Print2 " + nd.name);
	}*/
	public void print(Node p, int x){
//		prn("Debug: " + p.name + " "+ x);
		Stack<Node> st = new Stack<Node>();
		
		if(p.getClass().getName().equalsIgnoreCase("NodeFile")){
			int u = x;
			while(u>0){
				pr("-");
				u--;
			}
			pr(p.name);
			return;
		}
		else if(p.getClass().getName().equalsIgnoreCase("NodeFolder")){
			int u = x;
			while(u>0){
				pr("->");		//because folder
				u--;
			}
			prn(p.name);
//			if(p == root)
//				prn("");
			NodeFolder r = (NodeFolder) p;
			
			int y = new File(r.path).list().length;
			
			for(int j = y-1; j >= 0; j--){
//				prn("pushed " + r.children[j].name);
				st.push(r.children[j]);
			}
			for(int j = y-1; j >= 0; j--){
				Node ty = st.pop();
				print(ty, x+1);
				if(ty.getClass().getName().equals("NodeFile"))
						prn("");
			}
		}	
	}
	
	public void print(){
		print(root, 0);
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
	
	public void sort(){
		
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		q.enqueue(root);
		
		
		while(!q.isEmpty()){
			r = q.dequeue();
//			prn("R = " + r.name);
			if(new File(r.path).exists() && new File(r.path).isDirectory()){		//if folder
				int x = 0;
				if(new File(r.path).list() != null)
					x = new File(r.path).list().length;
//				prn("r = " + r.name +" " + new File(r.path).isDirectory());
				p = (NodeFolder) r;
				
				String[] array = new String[x];
				Node[] tmp = p.children.clone();
				
				for(int i = 0; i<x; i++){
					array[i] = p.children[i].name;
				}
				Arrays.sort(array);
				for(String mnm: array)
//					prn("mnm is " + mnm);
//				prn(p.name + " HERE --> " + array[0]);
				for(int i = 0; i<x; i++){
					for(Node nd : tmp)
						if(nd != null)
							if(nd.name .equals(array[i])){
								p.children[i] = nd;
//								prn("MC " + p.children[i].name);
							}
				}
				for(Node n: p.children)
					if(n != null)
						q.enqueue(n);
			}
		}
	}

}
