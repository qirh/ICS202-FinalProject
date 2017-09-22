/*
 * 
 * This is The Main tree class and an exception to to handle the case when MAX is exceeded.
 */

package Project202;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
class GreaterThanMaxException extends Exception{
	public GreaterThanMaxException() { super(); }
	public GreaterThanMaxException(String message) { super(message); }
	public GreaterThanMaxException(String message, Throwable cause) { super(message, cause); }
	public GreaterThanMaxException(Throwable cause) { super(cause); }
}
public class Tree {
	
	protected final static int MAX = 10;
	File f;
	NodeFolder root;
	Window window;
	
	public Tree(String x)throws GreaterThanMaxException, FileNotFoundException{							
		prn("Constructor -Tree");
		String y = x.substring(x.lastIndexOf("\\")+1);
		f = new File(x);
		root = new NodeFolder(y, x, MAX, f);
		if(!f.exists())
			throw new FileNotFoundException("File does not exist");
		scan();
		window = new Window(this);
	}
	public static void prn(String s){
		System.out.println(s);
	}
	
	protected void scan()throws GreaterThanMaxException{
		String[] x = f.list();			//names of children
		int j = 0;
		Queue<Node> q = new Queue<Node>();
		File tmp;
		Node r;
		NodeFolder p;
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
		}
		count();
	}
	
	protected String print(){
		Node f;
		NodeFolder r = root;
		Queue<Node> q = new Queue<Node>();
		String x = new String();
		
		q.enqueue(r);
		
		while(! q.isEmpty()){
			f = q.dequeue();
			
			if(new File(f.path).isFile()){
				x+=f.name + " ";
			}
			else if(new File(f.path).isDirectory()){
				r = (NodeFolder) f;
				x+=r.name + " ";
				
				for(int i = 0; i<new File(f.path).list().length; i++){
					q.enqueue(r.children[i]);
				}
				
			}
		}
		
		return x;
	}
	protected String printBFT(){
		Node f;
		NodeFolder r = root;
		Queue<Node> q = new Queue<Node>();
		String x = new String();
		q.enqueue(r);
		
		int scap = 0;
		int tmp = 0;
		String orig = root.path;
		char[] ch = new char[1000];
		ch = orig.toCharArray();
		for(char v: ch)
			if(v == '\\')
				scap++;
		tmp = 0;
		
		while(! q.isEmpty()){
			f = q.dequeue();
			
			orig = f.path;
			ch = orig.toCharArray();
			tmp = scap;
			scap = 0;
			for(char v: ch){
				if(v == '\\')
					scap++;
			}
			if(scap > tmp){
				x+="\n";
			}
			
			if(new File(f.path).isFile()){
				x+=f.name + " ";
			}
			else if(new File(f.path).isDirectory()){
				r = (NodeFolder) f;
				
				x+=r.name + " ";
				for(int i = 0; i<new File(f.path).list().length; i++){
					q.enqueue(r.children[i]);
				}
			}
		}
		return x;
	}
	
	protected String count(){				//includes folder
		root.files = countFiles(root);
		root.folders = countFolders(root);
		return root.files + " files and " + root.folders +" folders." ;
	}
	private int countFiles(NodeFolder r){
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
					if(r.children[i] != null)
						q.enqueue(r.children[i]);
				}
			}
		}
		return d;
	}
	private int countFolders(NodeFolder r){
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
	
	protected String getParentPath(Node p){		//unused
		String x =  p.path.substring(0, p.path.lastIndexOf("\\"));
		if(x.contains(root.path)){
			return p.path.substring(0, p.path.lastIndexOf("\\"));
		}
		return null;
	}
	
	protected boolean searchPath(String h){			//works w & w/o .type
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
		if(found)
			return true;
		return false;
	}
	
	protected String searchName(String h){			
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
		if(found)
			return search;
		return null;
	}
	
	protected boolean insertDirect(String c)throws GreaterThanMaxException{		//no restrictions
		if(c.length() < 1 || c.charAt(1) != ':')
			return false;
		if(!c.contains(root.path))
			return false;
		boolean n = new File(c).mkdirs();
		scan();
		return n;
	}
	
	protected boolean insert(String p, String c)throws GreaterThanMaxException{		//restricted
		if(!searchPath(p)){				//parent does not exist
			return false;
		}
		else if(!c.contains(p)){		//child unrelated to parent
			return false;
		}
		else if(searchPath(c)){			//child exists
			return false;
		}
		boolean n = new File(c).mkdir();
		scan();
		return n;
	}
	
	protected boolean delete(String h)throws GreaterThanMaxException{
		if(searchName(h) == null && !searchPath(h))
			return false;
		boolean n = new File(h).delete();
		scan();
		return n;
	}
	
	protected boolean deleteRec(String s)throws GreaterThanMaxException{
		if(s.length() < 1 || s.charAt(1) != ':')
			return false;
		if(!s.contains(root.path))
			return false;
		
		File f = new File(s);
		if (f.isDirectory()) {
			for (File c : f.listFiles())
				deleteRec(c.getPath());
		}
		if (!f.delete()){
			scan();
			scan();
			scan();
			return false;
		}
		scan();
		return true;
	}
	
	protected void sort(){
		
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		q.enqueue(root);
		
		while(!q.isEmpty()){
			r = q.dequeue();
			if(new File(r.path).exists() && new File(r.path).isDirectory()){
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
	
	protected void sortFlip(){
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
	@SuppressWarnings("static-access")
	protected static void error(Exception e){		//Exception Handling
		prn("Test. error");
		if(e instanceof GreaterThanMaxException){
			 new JOptionPane().showMessageDialog(null, "You hav Exceeded the limit of " + Tree.MAX + "\n Program will exit", "Error", JOptionPane.INFORMATION_MESSAGE);
			prn("Greater Error, " + e.getMessage() + ". Program will Exit");
		}
		else
			prn("UnKnown error: " + e.getMessage());
		
		e.printStackTrace();
		System.exit(0);
	}
}
