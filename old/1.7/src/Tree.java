/*
 * fix:
 * 	  print
 * 	  print2
 * 	  sort		--> reverse and same
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

@SuppressWarnings("serial")
class GreaterThanMaxException extends Exception{
	public GreaterThanMaxException() { super(); }
	public GreaterThanMaxException(String message) { super(message); }
	public GreaterThanMaxException(String message, Throwable cause) { super(message, cause); }
	public GreaterThanMaxException(Throwable cause) { super(cause); }
}


public class Tree {
	
	final static int MAX = 10;
	File f;
	NodeFolder root;
	Window w;
	
	public Tree(String x)throws Exception{								//works good
		prn("Constructor-TREE");
		String y = x.substring(x.lastIndexOf("\\")+1);
		f = new File(x);
		root = new NodeFolder(y, x, MAX, f);
		if(!f.exists())
			throw new FileNotFoundException("File does not exist");
		scan();
		w = new Window(this);
	}
	
	public void pr(String s){		//w/o \n
		System.out.print(s);
	}
	public void prn(String s){
		System.out.println(s);
	}
	
	public void scan()throws Exception{
		String[] x = f.list();			//name of children
		int j = 0;
		Queue<Node> q = new Queue<Node>();
		File tmp;
		Node r;
		NodeFile s;
		NodeFolder p, k;
		q.enqueue(root);
		
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
					}
					else if(tmp.isDirectory()){
						p.children[j] = new NodeFolder(x[j], p.path+"\\"+x[j], MAX, new File(p.path+"\\"+x[j]));
					}
					q.enqueue(p.children[j]);
				}
			}
			else{
				s = (NodeFile) r;
			}
		}
		count();
		
	}
	
	public String print(){		//BFT
		Node f;
		NodeFolder r = root;
		Queue<Node> q = new Queue<Node>();
		String x = new String();
		File tmp = new File(r.path);
		
		q.enqueue(r);
		
		while(! q.isEmpty()){
			f = q.dequeue();
			
			if(new File(f.path).isFile()){
				pr(f.name + " ");
				x+=f.name + " ";
			}
			else if(new File(f.path).isDirectory()){
				r = (NodeFolder) f;
				pr(r.name + " ");
				x+=r.name + " ";
				
				for(int i = 0; i<new File(f.path).list().length; i++){
					q.enqueue(r.children[i]);
				}
				
			}
		}
		
		return x+"\n";
	}
	
	public String count(){
		root.files = countFiles(root);
		root.folders = countFolders(root);
		return root.files + " files and " + root.folders +" folders." ;
	}
	public int countFiles(NodeFolder r){
		Queue<Node> q = new Queue<Node>();
		Node t;
		int d=0;
		File f = new File(r.path);
		
		q.enqueue(r);
		while(!q.isEmpty()){
			t = q.dequeue();
			f = new File(t.path);
			
			if(f.isFile()){
				d++;
			}
			else if(f.isDirectory()){
				String[] a = f.list();
				int u = a.length;
				r = (NodeFolder) t;
				
				for(int i = 0; i<u; i++){
					q.enqueue(r.children[i]);
				}
			}
		}
		return d;
	}
	public int countFolders(NodeFolder r){
		Queue<Node> q = new Queue<Node>();
		Node t;
		int d=0;
		File f = new File(r.path);
		
		q.enqueue(r);
		while(!q.isEmpty()){
			t = q.dequeue();
			f = new File(t.path);
			
			if(f.isFile()){
				continue;
			}
			else if(f.isDirectory()){
				
				d++;
				String[] a = f.list();
				int u = a.length;
				r = (NodeFolder) t;
				
				for(int i = 0; i<u; i++){
					q.enqueue(r.children[i]);
				}
			}
		}
		return d;
	}
	
	
	
	public String getParentPath(Node p){
		String x =  p.path.substring(0, p.path.lastIndexOf("\\"));
		if(x.contains(root.path)){
			return p.path.substring(0, p.path.lastIndexOf("\\"));
		}
		return null;
	}
	
	public boolean searchPath(String h){			//works w & w/o .type
		String search = h; 
		boolean found = false;
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		q.enqueue(root);
		
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
					if(r.path.substring(0, r.path.lastIndexOf('.')).equals(search)){
						found = true;
						search = r.path;
					}
				if(r.path.equals(search)){
					found = true;
					search = r.path;
				}
			}
		}
		if(found){
			prn("Found  " + search+"\n");
			return true;
		}
		return false;
	}
	
	public String searchName(String h){			
		String search = h;		//name w & w/o .type
		
		boolean found = false;
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		q.enqueue(root);
		
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
		if(found){
			prn("Found  " + search+"\n");
			return search;
		}
		prn("Have Not Found " + search+"\n");
		return null;
	}
	
	public boolean insertDirect(String p, String c)throws Exception{		//no restrictions
		prn("inserted " + c);
		boolean n = new File(c).mkdirs();
		scan();
		return n;
	}
	
	public boolean insert(String p, String c)throws Exception{		//restricted
		prn("Root = " + root.path + " P = " + p + " C =  " + c);
		if(!searchPath(p)){				//parent does not exist
			prn("1");
			return false;
		}
		else if(!c.contains(p)){		//child unrelated to parent
			prn("2");
			return false;
		}
		else if(searchPath(c)){			//child exists
			prn("3");
			return false;
		}
		prn("inserted " + c);
		boolean n = new File(c).mkdir();
		scan();
		return n;
	}
	
	public boolean delete(String h)throws Exception{	//recieves a path
		if(searchName(h) == null && !searchPath(h))
			return false;
		prn("deleted " + h);
		boolean n = new File(h).delete();
		scan();
		return n;
	}
	
	public void sort(){
		
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		q.enqueue(root);
		
		while(!q.isEmpty()){
			r = q.dequeue();
			if(new File(r.path).exists() && new File(r.path).isDirectory()){		//if folder
				int x = 0;
				if(new File(r.path).list() != null)
					x = new File(r.path).list().length;
				p = (NodeFolder) r;
				
				String[] array = new String[x];
				Node[] tmp = p.children.clone();
				
				for(int i = 0; i<x; i++){
					array[i] = p.children[i].name;
				}
				Arrays.sort(array);
				for(int i = 0; i<x; i++){
					for(Node nd : tmp)
						if(nd != null)
							if(nd.name .equals(array[i])){
								p.children[i] = nd;
							}
				}
				for(Node n: p.children)
					if(n != null)
						q.enqueue(n);
			}
		}
	}
	
	public void sortFlip(){
		
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		q.enqueue(root);
		
		while(!q.isEmpty()){
			r = q.dequeue();
			if(new File(r.path).exists() && new File(r.path).isDirectory()){		//if folder
				int x = 0;
				if(new File(r.path).list() != null)
					x = new File(r.path).list().length;
				p = (NodeFolder) r;
				
				String[] array = new String[x];
				Node[] tmp = p.children.clone();
				
				for(int i = 0; i<x; i++){
					array[i] = p.children[i].name;
				}
				Arrays.sort(array);
				
				for(int i = 0; i < array.length/2; i++){
				    String temp = array[i];
				    array[i] = array[array.length - i - 1];
				    array[array.length - i - 1] = temp;
				}
				for(int i = 0; i<x; i++){
					for(Node nd : tmp)
						if(nd != null)
							if(nd.name .equals(array[i])){
								p.children[i] = nd;
							}
				}
				for(Node n: p.children)
					if(n != null)
						q.enqueue(n);
			}
		}
	}
}
