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
import java.io.FileReader;

public class Listener implements ActionListener{
	String u1 = null,	u2 = null,	tmp = null,		tmp2 = null,	helpmsg = "This is Help",	aboutmsg = "This is About";
	Tree t = null;
	One o;
	Two w;
	Three th;
	
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
			Tree.error(r);
		}
	}
	private void closeOne(){
		o.clear();	o.setVisible(false);	o.dispose();
	}
	private void closeTwo(){
		w.clear();	w.setVisible(false);	w.dispose();
	}
	private void closeThree(){
		th.clear();	th.setVisible(false);	th.dispose();
	}
	private void create(){
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

		create();
		String x = ae.getActionCommand();
		
		if(t.change() && !x.equals("Exit (Press F11)")){
			b1 = false;b2 = false;b3 = false; v = false;
			closeOne();
			closeTwo();
			closeThree();
			t.callScan();
			new JOptionPane().showMessageDialog(null, "Directory was changed outside of this program's environment, program will reset", "Error", JOptionPane.INFORMATION_MESSAGE);
			t.window.set("*Reset\n" + "*The current directory begins @ " + t.root.path + "\nand has " + t.count()+"\n");
			return;
		}
		
		
		if(new File(t.root.path) == null){
			new JOptionPane().showMessageDialog(null, "You have Exceeded the limit of " + Tree.MAX + " Objects in a directory\n Program will exit", "Error", JOptionPane.INFORMATION_MESSAGE);
			Tree.error(new NullPointerException());
		}
		
		if(x.equals("Insert")){
			closeOne();
			closeThree();
			w.setTitle("Insert");
			w.setVisible(true);
		}
		else if(x.equals("E2")){
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
				w.clear();	w.setVisible(false);	w.dispose();
				
			}catch(Exception r){
				Tree.error(r);
			}
		}
		/*
		 * truth table:
		 * b3b2b1
		 * 000	--	Delete: boolean
		 * 001	--	Path: boolean
		 * 010	--  Name: String
		 * 011	--	Delete - Recursively: boolean 
		 * 100 	--  Set: boolean
		 * 111 	--  Insert - no rest: boolean
		 */
		else if(x.equals("Delete")){
			b1 = false;	b2 = false;
			b3 = false; v = true;
			o.setTitle("Delete");
			o.set("Delete");
			o.set2("Enter Path: ");
			closeTwo();
			closeThree();
			o.setVisible(true);
		}
		else if(x.equals("Search by Path")){
			b1 = true;	b2 = false;
			b3 = false; v = true;
			o.setTitle("Search by Path");
			o.set("Search by Path");
			o.set2("Enter Path: ");
			closeTwo();
			closeThree();
			o.setVisible(true);
		}
		else if(x.equals("Search by Name")){
			b1 = false;	b2 = true;
			b3 = false; v = true;
			o.setTitle("Search by Name");
			o.set("Search by Name");
			o.set2("Enter Name of File or Directory: ");
			closeTwo();
			closeThree();
			o.setVisible(true);
		}
		else if(x.equals("Delete - Recursively")){
			b1 = true;	b2 = true;
			b3 = false; v = true;
			o.setTitle("Delete - Recursively");
			o.set("Delete - Recursively");
			o.set2("Enter Path: ");
			closeTwo();
			closeThree();
			o.setVisible(true);
		}
		else if(x.equals("Set")){
			b1 = false;	b2 = false;
			b3 = true;	v = true;
			o.setTitle("Set a New Path");
			o.set("Set New Path: ");
			o.set2("Enter Path: ");
			closeTwo();
			closeThree();
			o.setVisible(true);
		}
		else if(x.equals("Set")){
			b1 = false;	b2 = false;
			b3 = true;	v = true;
			o.setTitle("Set a New Path");
			o.set("Set New Path: ");
			o.set2("Enter Path: ");
			closeTwo();
			closeThree();
			o.setVisible(true);
		}
		else if(x.equals("Insert - No Restrictions")){
			b1 = true;	b2 = true;
			b3 = true;	v = true;
			o.setTitle("Insert - No Restrictions");
			o.set("Insert - No Restrictions");
			o.set2("Enter Path: ");
			closeTwo();
			closeThree();
			o.setVisible(true);
		}
		else if(x.equals("E1")){
			try{
				tmp = o.get();
				if(!b3 && !b2 && !b1 && v){		//delete
					m = t.delete(tmp);
					if(m)
						t.window.append("Delete Succeful @ " + tmp);
					else
						t.window.append("Delete failed @ " + tmp);
				}
				else if(!b3 && !b2 && b1 && v){	//Search - path
					String mt = t.searchPath(tmp);
					if(mt != null)
						t.window.append("Found " + mt);
					else
						t.window.append("Have Not Found " + tmp);
				}
				else if(!b3 && b2 && !b1 && v){	//Search - name
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
				else if(b3 && !b2 && v && !b1){		//set	
					m = t.setPath(tmp);
					if(m)
						t.window.clickReset();
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
				Tree.error(r);
			}
		}
		else if(x.equals("Sort")){
			b1 = false;b2 = false;	b3 = false; v = false;
			t.sort();
			closeOne();
			closeTwo();
			closeThree();
			t.window.append("The Tree Has been sorted in alphatecal order");
		}
		else if(x.equals("Sort - Flipped")){
			b1 = false;b2 = false;b3 = false; v = false;
			t.sortFlip();
			closeOne();
			closeTwo();
			closeThree();
			t.window.append("The Tree Has been sorted in reverse alphatecal order");
		}
		else if(x.equals("Print (in One Line)")){
			b1 = false;b2 = false;b3 = false; v = false;
			closeOne();
			closeTwo();
			closeThree();
			t.window.append("Print (In One Line)");
			t.window.appendPlain(t.print());
		}
		else if(x.equals("Print (BFT)")){
			b1 = false;b2 = false;b3 = false; v = false;
			closeOne();
			closeTwo();
			closeThree();
			t.window.append("Print (Breadth First Traversal):");
			t.window.appendPlain(t.printBFT());
		}
		else if(x.equals("Print (Pre Order)")){
			b1 = false;b2 = false;b3 = false; v = false;
			closeOne();
			closeTwo();
			closeThree();
			t.window.append("Print (PreOrder):");
			t.window.appendPlain(t.printPre());
		}
		else if(x.equals("Print (Post Order)")){
			b1 = false;b2 = false;b3 = false; v = false;
			closeOne();
			closeTwo();
			closeThree();
			t.window.append("Print (Post Order):");
			t.window.appendPlain(t.printPost());
		}
		else if(x.equals("Count")){
			b1 = false;b2 = false;b3 = false; v = false;
			closeOne();
			closeTwo();
			closeThree();
			t.window.append("The current directory begins @ " + t.root.path + "\nand has " + t.count());
		}
		else if(x.equals("Reset")){
			b1 = false;b2 = false;b3 = false; v = false;
			closeOne();
			closeTwo();
			closeThree();
			
			t.callScan();
			t.window.set("*Reset\n" + "*The current directory begins @ " + t.root.path + "\nand has " + t.count()+"\n");
		}
		else if(x.equals("Insert File")){
			b1 = false;b2 = false;b3 = false; v = false;
			closeOne();
			closeTwo();
			th.setVisible(true);
			th.setTitle("Insert File");
		}
		else if(x.equals("E3")){
			b1 = false;b2 = false;b3 = false; v = false;
			String hj = th.get1();
			String jk = th.get2();
			int k = 0;
			try{
				k = t.insertFile(hj, jk);
				t.callScan();
			}catch (Exception e) {
				Tree.error(e);
			}
			if(k == 2)
				t.window.append("File Insertion Succeful @ " + hj);
			else if(k == 1)
				t.window.append("File Appended Succefully @ " + hj);
			else
				t.window.append("File Insertion Failed @ " + hj);
			closeOne();
			closeTwo();
			closeThree();
		}
		else if(x.equals("Help (Press F1)")){
			JOptionPane.showMessageDialog(null, helpmsg, "Help", JOptionPane.INFORMATION_MESSAGE);
			
		}
		else if(x.equals("About (Press F2)")){
			JOptionPane.showMessageDialog(null, aboutmsg, "Help", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(x.equals("Exit (Press F11)")){
			System.exit(0);
		}
	}
}
