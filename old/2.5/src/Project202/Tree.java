/*
 * 
 * This is The Main tree class it has all the main methods and also handles all the exceptions
 */

package Project202;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
class GreaterThanMaxException extends Exception{
	public GreaterThanMaxException() { super(); }
	public GreaterThanMaxException(String message) { super(message); }
	public GreaterThanMaxException(String message, Throwable cause) { super(message, cause); }
	public GreaterThanMaxException(Throwable cause) { super(cause); }
}
@SuppressWarnings("serial")
class DirectoryDoesNotExist extends Exception{		//used For Root Only
	public DirectoryDoesNotExist() { super(); }
	public DirectoryDoesNotExist(String message) { super(message); }
	public DirectoryDoesNotExist(String message, Throwable cause) { super(message, cause); }
	public DirectoryDoesNotExist(Throwable cause) { super(cause); }
}

public class Tree {
	
	protected final static int MAX = Test.MAX;
	NodeFolder root, tmp;
	Window window;
	String hash;
	private boolean flip = false;
	
	public Tree(String x) throws IOException{							
		prn("Constructor -Tree");
		String y = x.substring(x.lastIndexOf("\\")+1);
		File f = new File(x);
		root = new NodeFolder(y, x, MAX);
		if(!f.exists())
			throw new FileNotFoundException("Folder does not exist");
		callScan();
		write();
		window = new Window(this);
	}
	protected boolean change(){
		try {
			write();
			return check();
		}catch (IOException e) {
			error(e);
		}
		return false;
	}
	private void write() throws IOException{
		if(flip){
//			prn("Calling flip");
			sortFlip();
		}
		else{
//			prn("Calling sort");
			sort();
		}
		String tmp = print();
		tmp = ""+tmp.hashCode();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Test.content, false)));
		out.println(tmp);
		out.close();
	}
	private boolean check() throws IOException{
		String n = null, m = null, k = null;
		BufferedReader br = new BufferedReader(new FileReader(Test.content));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        n = sb.toString();
        br.close();
        callScan();
        if(flip){
        	sort();
        	m = ""+print().hashCode();
        	sortFlip();
        	k = ""+print().hashCode();
        }
        else{
        	sortFlip();
        	k = ""+print().hashCode();
        	sort();
        	m = ""+print().hashCode();
        }
        k = k.trim();	m = m.trim();	n = n.trim();
//        prn(n + " " + !n.equals(m) + " " + m + " " + k +" " + !n.equals(k));
		return !n.equals(m) && !n.equals(k);
	}
	protected static void prn(String s){		//to minimise typing
		System.out.println(s);
	}
	protected void callScan(){
		try {
			scan();
		}catch(GreaterThanMaxException | DirectoryDoesNotExist | IOException e) {
			error(e);
		}
	}
	private void scan()throws GreaterThanMaxException, DirectoryDoesNotExist, IOException{
		File f = new File(root.path);
		String[] x = f.list();			//names of children
		int j = 0;
		Queue<Node> q = new Queue<Node>();
		File tmp;
		Node r;
		NodeFolder p;
		
		if(!f.exists())
			throw new DirectoryDoesNotExist();
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
						p.children[j] = new NodeFile(x[j], p.path+"\\"+x[j]);
					}
					else if(tmp.isDirectory()){
						p.children[j] = new NodeFolder(x[j], p.path+"\\"+x[j], MAX);
					}
					q.enqueue(p.children[j]);
				}
			}
		}
		write();
		count();
	}
	protected boolean setPath(String h) throws GreaterThanMaxException, DirectoryDoesNotExist{
		File f = new File(h);
		if(!f.exists() || ! f.isDirectory())
			return false;
		root = new NodeFolder(h, MAX);
		callScan();
		return true;
	}

	protected String print(){
		return print(root);
	}
	protected String print(NodeFolder r){		//in one line
		Node f;
		Queue<Node> q = new Queue<Node>();
		String x = new String(">");
		
		q.enqueue(r);
		
		while(! q.isEmpty()){
			f = q.dequeue();
			
			if(new File(f.path).isFile()){
				x+=f.name + " | ";
			}
			else if(new File(f.path).isDirectory()){
				r = (NodeFolder) f;
				x+=r.name + " | ";
				
				for(int i = 0; i<new File(f.path).list().length; i++){
					q.enqueue(r.children[i]);
				}
			}
		}
		return x.substring(0, x.lastIndexOf('|'))+"#";
	}
	protected String printBFT(){
		return printBFT(root);
	}
	protected String printBFT(NodeFolder r){		//level by level
		Node f;
		Queue<Node> q = new Queue<Node>();
		String x = new String(">");
		q.enqueue(r);
		
		int scap = 0;
		int tmp = 0;
		String orig = root.path;
		char[] ch = new char[1000];
		ch = orig.toCharArray();
		
		for(char v: ch)				//algorithm to decide when to create a new line
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
				x = x.substring(0, x.lastIndexOf(" |")) + " #\n>";		//remove last '|' before line break
			}
			
			if(new File(f.path).isFile()){
				x+=f.name + " | ";
			}
			else if(new File(f.path).isDirectory()){
				r = (NodeFolder) f;
				
				x+=r.name + " | ";
				for(int i = 0; i<new File(f.path).list().length; i++){
					q.enqueue(r.children[i]);
				}
			}
		}
		return x.substring(0, x.lastIndexOf(" |")) + " #";
	}
	protected String printPre(){
		return printPre(root);
	}
	protected String printPre(NodeFolder r){		//level by level
		Node f;
		Stack<Node> s = new Stack<Node>();
		String x = new String(">");
		s.push(r);
		
		while(! s.isEmpty()){
			f = s.pop();
			if(new File(f.path).isFile()){
				x+=f.name + " | ";
			}
			else if(new File(f.path).isDirectory()){
				r = (NodeFolder) f;
				
				x+=r.name + " | ";
				for(int i = 0; i<new File(f.path).list().length; i++){
					s.push(r.children[i]);
				}
			}
		}
		return x.substring(0, x.lastIndexOf(" |")) + " #";
	}
	protected String printPost(){
		return printPost(root);
	}
	protected String printPost(NodeFolder r){		//level by level
		Node f;
		Stack<Node> s = new Stack<Node>();
		String x = new String(">");
		s.push(r);
		boolean k = false;
		while(! s.isEmpty()){
			f = s.pop();
			k = false;
			if(new File(f.path).isFile()){
				if(!f.vis){
					f.vis = true;
					x+=f.name + " | ";
				}
			}
			else if(new File(f.path).isDirectory()){
				r = (NodeFolder) f;
				
				if(new File(r.path).listFiles().length > 0 && !r.vis){
					k = true;
				}
				if(k){
					r.vis = true;
					s.push(r);
				}
				else
					if(!r.pr){
						r.pr = true;
						x+=r.name + " | ";
					}
				for(int i = 0; i<new File(f.path).list().length; i++){
					s.push(r.children[i]);
				}
				
			}
		}
		return x.substring(0, x.lastIndexOf(" |")) + " #";
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
//					if(r.children[i] != nnnn)
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
	protected String getParentPath(Node p){			//never used
		String x =  p.path.substring(0, p.path.lastIndexOf("\\"));
		if(x.contains(root.path)){
			return p.path.substring(0, p.path.lastIndexOf("\\"));
		}
		return null;
	}
	protected String searchPath(String h){			//works w & w/o .type
		String search = h; 
		boolean found = false;
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		q.enqueue(root);
		
		while(!q.isEmpty() && !found){
			r = q.dequeue();
			File f = new File(r.path);
			if(f.exists()){
				if(f.isDirectory()){
					p = (NodeFolder) r;
					for(Node nd : p.children)
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
			return search;
		return null;
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
			File f = new File(r.path);
			if(f.exists()){
				if(f.isDirectory()){
					p = (NodeFolder) r;
					for(Node nd : p.children)
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
	protected boolean insert(String p, String c)throws GreaterThanMaxException, DirectoryDoesNotExist{		//restricted
		if(searchPath(p) == null){				//parent does not exist
			return false;
		}
		else if(!c.contains(p)){		//child unrelated to parent
			return false;
		}
		else if(searchPath(c) != null && new File(c).isDirectory()){			//child exists
			return false;
		}
		boolean n = new File(c).mkdir();
		callScan();
		return n;
	}
	protected boolean insertDirect(String c)throws GreaterThanMaxException, DirectoryDoesNotExist{		//no restrictions
		if(c.length() < 1 || c.charAt(1) != ':')
			return false;
		if(!c.contains(root.path))
			return false;
		boolean n = new File(c).mkdirs();
		callScan();
		return n;
	}
	/*
	 * if the file exists it will append it
	 */
	protected int insertFile(String h, String hj)throws GreaterThanMaxException, IOException, FileNotFoundException, DirectoryDoesNotExist{
		int x = 0;
		if(!h.contains("\\")){		//path is wrong (to avoid exceptions)
			return 0;
		}
		else if(searchPath(h.substring(0,h.lastIndexOf("\\"))) == null){		//direct parent does not exist
			return 0;
		}
		else if(searchPath(h) != null){		//direct parent does not exist
			x = 1;
		}
		File f = new File(h);
		if(!f.exists())
			f.createNewFile();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(h, true)));
		out.println(hj);
		out.close();
		callScan();
		if(x == 0)
			return 2;
		else
			return x;
	}
	protected boolean delete(String h)throws GreaterThanMaxException, DirectoryDoesNotExist{
		if(searchName(h) == null && searchPath(h) == null)
			return false;
		boolean n = new File(h).delete();
		callScan();
		return n;
	}
	protected boolean deleteRec(String s)throws GreaterThanMaxException, DirectoryDoesNotExist{
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
			callScan();
			return false;
		}
		callScan();
		return true;
	}
	protected void sort(){
		prn("call --> sort");
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		flip = false;
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
					q.enqueue(n);
			}
		}
	}
	protected void sortFlip(){
		prn("call --> flip");
		Queue<Node> q = new Queue<Node>();
		Node r;
		NodeFolder p;
		flip = true;
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
				
				for(int i = 0; i < array.length/2; i++){		//flip algorithm
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
					q.enqueue(n);
			}
		}
	}
	@SuppressWarnings("static-access")
	protected static void error(Exception e){		//Exception Handling
		prn("Test. error");
		if(e instanceof GreaterThanMaxException)
			 new JOptionPane().showMessageDialog(null, "You have Exceeded the limit of " + Tree.MAX + " Objects in a directory\n Program will exit", "Error", JOptionPane.INFORMATION_MESSAGE);
		
		else if(e instanceof DirectoryDoesNotExist)
			new JOptionPane().showMessageDialog(null, "The Main Directory Does Not Exist\n Program will exit", "Error", JOptionPane.INFORMATION_MESSAGE);
		else{
			new JOptionPane().showMessageDialog(null, "UnKnown error\n" + e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
		}
		e.printStackTrace();
		System.exit(0);
	}
	
}
