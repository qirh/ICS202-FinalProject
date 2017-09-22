import java.io.File;
import java.io.FileNotFoundException;
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
	int file = 0;
	int folder = 0;
	
	public Tree(String x)throws FileNotFoundException{								//works good
		String y = x.substring(x.lastIndexOf("\\")+1);
		f = new File(x);
		root = new NodeFolder(y, x, MAX, f);
		if(!f.exists())
			throw new FileNotFoundException("File does not exist");
	}
	
	public void pr(String s){		//w/o \n
		System.out.print(s);
	}
	public void prn(String s){
		System.out.println(s);
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
		count();
		prn("scan - end\n\n");
		
	}
	
	public void count(){
		count(root);
	}
	public void count(Node r){
		Queue<Node> q = new Queue<Node>();
		Node t;
		NodeFolder p;
		File f = new File(r.path);
		
		q.enqueue(r);
		
		while(!q.isEmpty()){
			t = q.dequeue();
			f = new File(t.path);
			
			if(f.isFile()){
				file++;
			}
			
			else if(f.isDirectory()){
				folder++;
				String[] a = f.list();
				int u = a.length;
				p = (NodeFolder) t;
				
				for(int i = 0; i<u; i++){
					q.enqueue(p.children[i]);
				}
			}
		}
		prn("Count, Files = " + file + " Folders = " + folder);
	}
	public void print(){
		print(root, 0);
	}
	
	public void print(Node p, int x){
		Stack<Node> st = new Stack<Node>();
		
		if(p.getClass().getName().equalsIgnoreCase("NodeFile")){
			int u = x;
			while(u>0){
				pr("  ");
				u--;
			}
			pr(p.name);
			return;
		}
		else if(p.getClass().getName().equalsIgnoreCase("NodeFolder")){
			int u = x;
			while(u>0){
				pr(" >");		//because folder
				u--;
			}
			prn(p.name);
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
	
	public void print2(){
		print2(root);
	}
	
	public void print2(Node r){
		Queue<Node> q = new Queue<Node>();
		Node t;
		NodeFolder p;
		File f = new File(r.path);
		
		q.enqueue(r);
		
		while(!q.isEmpty()){
			t = q.dequeue();
			f = new File(t.path);
			
			if(f.isFile()){
				pr(t.name + " ");
			}
			
			else if(f.isDirectory()){
				pr(">"+t.name+" ");
				String[] a = f.list();
				int u = a.length;
				p = (NodeFolder) t;
				
				for(int i = 0; i<u; i++){
					if(p.children[i] != null)
						q.enqueue(p.children[i]);
				}
			}
		}
	}
	
	public String searchPath(String h){
		String search = h; 
		boolean found = false;
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		q.enqueue(root);
		
//		prn("SearchPath - start");
//		prn(search);
		
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
//		prn("SearchPath - end");
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
		
//		prn("SearchName - start");
//		prn(search);
		
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
//		prn("SearchName - end");
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
	
	public boolean insert(String p, String c){		//restrictions
		if(searchPath(p) == null){		//parent does not exist
//			prn("1");
			return false;
		}
		else if(!c.contains(p)){		//child unrelated to parent
//			prn("2");
			return false;
		}
		else if(searchPath(c) != null){	//child exists
//			prn("3");
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
