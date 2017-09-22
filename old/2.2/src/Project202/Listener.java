/*
 * 
 * This class handles the events from all 3 GUIs, it also handles certain key strokes
 */

package Project202;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Listener implements ActionListener{
	String u1 = null;	String u2 = null;					String tmp = null;
	String tmp2 = null;	String helpmsg = "This is Help";	String aboutmsg = "This is About";
	Tree t = null;
	Three th;
	Two w;
	One o;
	
	private boolean n = false;			//submit
	private boolean b1 = false;			//Operations
	private boolean b2 = false;
	private boolean b3 = false;
	private boolean v = false;			//valid
	private boolean m = true;			//enter
	private boolean has = false;		//creating frames
	
	public Listener(Tree x) {
		t = x;
		
	    try{
	    	BufferedReader br = new BufferedReader(new FileReader(Test.help));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        helpmsg = sb.toString();
	        br.close();
	        
	        br = new BufferedReader(new FileReader(Test.about));
	        sb = new StringBuilder();
	        line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        aboutmsg = sb.toString();
	        br.close();
		}catch(Exception r){
			Tree.prn("Catch Buffer " + helpmsg);
			Tree.error(r);
		}
	}
	public void create(){
		if(!has){
			th = new Three();
			o = new One();
			w = new Two();
			has = true;
		}
	}
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent ae) {
		String x = ae.getActionCommand();
		create();
		
		if(new File(t.root.path) == null){
			new JOptionPane().showMessageDialog(null, "You have Exceeded the limit of " + Tree.MAX + " Objects in a directory\n Program will exit", "Error", JOptionPane.INFORMATION_MESSAGE);
			Tree.error(new NullPointerException());
		}
		
		if(x.equals("Insert")){
			w.setVisible(true);
		}
		else if(x.equals("Submit")){
			try{
				u1 = w.get1();
				u2 = w.get2();
				if(u1 != null || u2 != null){
					n = t.insert(u1, u2);
					if(n)
						t.window.append("Insertion Successful @" + u2);
					else
						t.window.append("Insertion Failed @ " + u2);
				}
				else
					t.window.append("Insertion Failed");
				w.clear();	w.setVisible(false);	w.dispose();
				
			}catch(Exception r){
				Tree.prn("Catch Listener1 " + x);
				Tree.error(r);
			}
		}
		/*
		 * truth table:
		 * b2b1b3
		 * 000	--	Delete: boolean
		 * 010	--	Path: boolean
		 * 100	--  Name: String
		 * 110	--	Delete - Recursively: boolean 
		 * 111 	--  Insert - no rest: boolean
		 */
		else if(x.equals("Delete")){
			b1 = false;	b2 = false;
			b3 = false; v = true;
			o.set("Delete");
			o.set2("Enter Path: ");
			o.setVisible(true);
		}
		else if(x.equals("Search by Path")){
			b1 = true;	b2 = false;
			b3 = false; v = true;
			o.set("Search by Path");
			o.set2("Enter Path: ");
			o.setVisible(true);
		}
		else if(x.equals("Search by Name")){
			b1 = false;	b2 = true;
			b3 = false; v = true;
			o.set("Search by Name");
			o.set2("Enter Name of File or Directory: ");
			o.setVisible(true);
		}
		else if(x.equals("Delete - Recursively")){
			b1 = true;	b2 = true;
			b3 = false; v = true;
			o.set("Delete - Recursively");
			o.set2("Enter Path: ");
			o.setVisible(true);
		}
		else if(x.equals("Insert - No Restrictions")){
			b1 = true;	b2 = true;
			b3 = true;	v = true;
			o.set("Insert - No Restrictions");
			o.set2("Enter Path: ");
			o.setVisible(true);
		}
		else if(x.equals("Enter")){
			try{
				tmp = o.get();
				if(!b2 && !b1 && v){		//delete
					m = t.delete(tmp);
					if(m)
						t.window.append("Delete Succeful @ " + tmp);
					else
						t.window.append("Delete failed @ " + tmp);
				}
				else if(!b2 && b1 && v){	//Search - path
					m = t.searchPath(tmp);
					if(m)
						t.window.append("Found " + tmp);
					else
						t.window.append("Have Not Found " + tmp);
				}
				else if(b2 && !b1 && v){	//Search - name
					tmp2 = t.searchName(tmp);
					if(tmp2 == null)
						t.window.append("Have Not Found " + tmp);
					else
						t.window.append("Found " + tmp + " @ " + tmp2);
				}
				else if(b2 && b1 && v && !b3){		//delete -r	
					m = t.deleteRec(tmp);
					if(m)
						t.window.append("Recursive Deletion Succeful @ " + tmp);
					else
						t.window.append("Recursive Deletion failed @ " + tmp);
				}
				else if(b2 && b1 && v && b3){		//insert -no rest	
					m = t.insertDirect(tmp);
					if(m)
						t.window.append("Insert - No Restrictions Succeful @ " + tmp);
					else
						t.window.append("Insert - No Restrictions Failed @ " + tmp);
				}
				
				o.clear();	o.setVisible(false);	o.dispose();
				w.clear();	w.setVisible(false);	w.dispose();
			}catch(Exception r){
				Tree.prn("Catch Listener2 " + x);
				Tree.error(r);
			}
		}
		else if(x.equals("Sort")){
			b1 = false;b2 = false;	b3 = false; v = false;
			t.sort();
		}
		else if(x.equals("Sort - Flipped")){
			b1 = false;b2 = false;b3 = false; v = false;
			t.sortFlip();
		}
		else if(x.equals("Print (in One Line)")){
			b1 = false;b2 = false;b3 = false; v = false;
			t.window.append("Print (In One Line)");
			t.window.appendPlain(t.print());
		}
		else if(x.equals("Print (BFT)")){
			b1 = false;b2 = false;b3 = false; v = false;
			t.window.append("Print (Breadt First Traversal):");
			t.window.appendPlain(t.printBFT());
		}
		else if(x.equals("Count")){
			b1 = false;b2 = false;b3 = false; v = false;
			t.window.append("The current directory begins @ " + t.root.path + "\nand has " + t.count());
		}
		else if(x.equals("Reset")){
			b1 = false;b2 = false;b3 = false; v = false;
			
			try{
				t.scan();
			}catch (GreaterThanMaxException e) {
				Tree.prn("Reset" + e.getMessage());
				Tree.error(e);
			}
			t.window.set("*Reset\n" + t.window.u + t.count()+"\n");
			o.clear();	o.setVisible(false);	o.dispose();
			w.clear();	w.setVisible(false);	w.dispose();
			th.clear();	th.setVisible(false);	th.dispose();
		}
		else if(x.equals("Insert File")){
			b1 = false;b2 = false;b3 = false; v = false;
			th.setVisible(true);
		}
		else if(x.equals("Create")){
			b1 = false;b2 = false;b3 = false; v = false;
			String hj = th.get1();
			String jk = th.get2();
			boolean k = false;
			try{
				k = t.insertFile(hj, jk);
				t.scan();
			}catch (Exception e) {
				Tree.prn("Create");
				Tree.error(e);
			}
			if(k)
				t.window.append("File Insertion Succeful @ " + hj);
			else
				t.window.append("File Insertion Failed @ " + hj);
			th.clear();	th.setVisible(false);	th.dispose();
		}
		else if(x.equals("Help (Press F1)")){
			JOptionPane.showMessageDialog(null, helpmsg, "Help", JOptionPane.INFORMATION_MESSAGE);
			Tree.prn("Help");
		}
		else if(x.equals("About (Press F2)")){
			JOptionPane.showMessageDialog(null, aboutmsg, "Help", JOptionPane.INFORMATION_MESSAGE);
			Tree.prn("About");
		}
		else if(x.equals("Exit (Press F11)")){
			Tree.prn("Exit");
			System.exit(0);
		}
	}
}
