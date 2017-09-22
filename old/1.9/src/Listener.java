/*
 *  append t.w.append()
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Listener implements ActionListener{
	String u1 = null;
	String u2 = null;
	String tmp = null;
	String tmp2 = null;
	String helpmsg = "This is Help";
	String aboutmsg = "This is About";
	Tree t = null;
	Two w;
	One o;

	boolean n = false;			//submit
	boolean b1 = false;			//Operations
	boolean b2 = false;
	boolean b3 = false;
	boolean v = false;			//valid
	boolean m = true;			//enter
	boolean has = false;		//creating frames
	
	public Listener(Tree x) {
		t = x;
		
	    try{
	    	BufferedReader br = new BufferedReader(new FileReader("D:\\Academic\\Project\\help.txt"));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        helpmsg = sb.toString();
	        br.close();
	        
	        br = new BufferedReader(new FileReader("D:\\Academic\\Project\\about.txt"));
	        sb = new StringBuilder();
	        line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        aboutmsg = sb.toString();
	        br.close();
	    }catch(FileNotFoundException r){
			prn("Catch Buffer " + helpmsg);
			Test.error(r);
		}catch(IOException r){
			prn("Catch Buffer " + helpmsg);
			Test.error(r);
		}catch(Exception r){
			prn("Catch Buffer " + helpmsg);
			Test.error(r);
		}finally{
		}
	}
	
	public void pr(String s){		//w/o \n
		System.out.print(s);
	}
	public void prn(String s){
		System.out.println(s);
	}
	
	public void create(){
		if(!has){
//			prn("Create");
			o = new One(t);
			w = new Two(t);
			has = true;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String x = ae.getActionCommand();
		create();
		
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
				w.clear();
				w.setVisible(false);
				w.dispose();
				
			}catch(Exception r){
				prn("Catch Listener1 " + x);
				Test.error(r);
			}
		}
		/*
		 * truth table:
		 * b2b1b3
		 * 000	--	Delete: boolean
		 * 010	--	Path: boolean
		 * 100	--  Name: String
		 * 110	--	Delete - Recursively: boolean 
		 * 111 	-- Insert - no rest: boolean
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
				
				o.clear();
				o.setVisible(false);
				o.dispose();
			}catch(Exception r){
				prn("Catch Listener2 " + x);
				Test.error(r);
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
			t.window.append(t.print());
		}
		else if(x.equals("Print (BFT)")){
			b1 = false;b2 = false;b3 = false; v = false;
			t.window.append(t.printBFT());
		}
		else if(x.equals("Count")){
			b1 = false;b2 = false;b3 = false; v = false;
			t.window.append("The current directory begins @ " + t.root.path + "\nand has " + t.count());
		}
		else if(x.equals("Help (Press F1)")){
			JOptionPane.showMessageDialog(null, helpmsg, "Help", JOptionPane.INFORMATION_MESSAGE);
			prn("Help");
		}
		else if(x.equals("About (Press F2)")){
			JOptionPane.showMessageDialog(null, aboutmsg, "Help", JOptionPane.INFORMATION_MESSAGE);
			prn("Help");
		}
		else if(x.equals("Exit (Press F11)")){
			prn("Exit");
			System.exit(0);
		}
		try {
			t.scan();
			t.scan();
		}catch (Exception e) {
			prn("Event" + e.getMessage());
			Test.error(e);
		}
	}
}
