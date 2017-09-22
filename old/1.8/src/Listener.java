/*
 * 	Set Icon
 * 	Set Wrapping for texts
 * 	setTitle
 * 	setfont
 *  seticon
 * 
 * 
 *  append t.w.append()
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

public class Listener implements ActionListener{//, KeyListener{
	String u1 = null;
	String u2 = null;
	String tmp = null;
	String tmp2 = null;
	Tree t = null;
	
	Two w;
	One o;
//	Window s;
	

	boolean n = false;			//insert success
	boolean rest = false;		//restrictions
	boolean b1 = false;			//rest of op's
	boolean b2 = false;
	boolean v = false;			//valid		---> needless
	boolean m = false;			//delete, path success
	boolean has = false;
	
	public Listener(Tree x) {
		t = x;
	}
	
	public void pr(String s){		//w/o \n
		System.out.print(s);
	}
	public void prn(String s){
		System.out.println(s);
	}
	
	public void create(){
		if(!has){
			prn("Create");
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
//			t.w.append("Insertion");
			rest = true;
			w.setVisible(true);
		}
		
		else if(x.equals("Insert - No Restrictions")){
			rest = false;
			w.setVisible(true);
		}
		
		else if(x.equals("Submit")){
			try{
				u1 = w.get1();
				u2 = w.get2();
				if(u1 != null || u2 != null){
					if(rest)
						n = t.insert(u1, u2);
					else
						n = t.insertDirect(u1, u2);
					if(n)
						t.w.append("Insertion Successful");
					else
						t.w.append("Insertion Failed");
				}
				else
					t.w.append("Insertion Failed");
				w.clear();
				w.setVisible(false);
				w.dispose();
				
			}catch(Exception r){
				System.out.println("Catch Listener1 " + x);
				Test.error(r);
			}
		}
		/*
		 * truth table:
		 * b2b1
		 * 00	--	Delete: boolean
		 * 01	--	Path: boolean
		 * 10	--  Name: String
		 */
		else if(x.equals("Delete")){
			b1 = false;
			b2 = false;
			v = true;
			o.set("Delete");
			o.setVisible(true);
		}
		else if(x.equals("Search by Path")){
			b1 = true;
			b2 = false;
			v = true;
			o.set("Search by Path");
			o.setVisible(true);
		}
		else if(x.equals("Search by Name")){
			b1 = false;
			b2 = true;
			v = true;
			o.set("Search by Name");
			o.set2("Enter Path: ");
			o.setVisible(true);
		}
		else if(x.equals("Sort")){
			b1 = false;
			b2 = false;
			v = false;
			t.sort();
		}
		else if(x.equals("Sort - Flipped")){
			b1 = false;
			b2 = false;
			v = false;
			t.sortFlip();
		}
		else if(x.equals("Print")){
			b1 = false;
			b2 = false;
			v = false;
			t.w.append(t.print());
		}
		else if(x.equals("Enter")){
			try{
				tmp = o.get();
				if(!b2 && !b1 && v){
					prn("Call");
					m = t.delete(tmp);
				}
				else if(!b2 && b1 && v){
					if(m)
						t.w.append("Found " + tmp);
					else
						t.w.append("Have Not Found " + tmp);
				}
				else if(b2 && !b1 && v)
					tmp2 = t.searchName(tmp);
				else if(b2 && b1 && v)
					t.sort();
				
				if(m)
					prn("success");
				
				o.clear();
				o.setVisible(false);
				o.dispose();
			}catch(Exception r){
				System.out.println("Catch Listener2 " + x);
				Test.error(r);
			}
		}
		
	}
	/*
	@Override
	public void keyPressed(KeyEvent e) {
		
		System.out.println("\n1 = " + e.getKeyChar());
		
		System.out.println("2 = " + e.getKeyLocation());
		
		System.out.println("3 = " + e.getKeyText(0));
		
		System.out.println("4 = " + e.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_F1 ) {
			System.out.println("567");
		}
		if(e.getKeyChar() == KeyEvent.VK_F1 ) {
			System.out.println("3");
		} 
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_F1 ) {
			System.out.println("4");
			} 
	}*/
}
