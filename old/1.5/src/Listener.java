import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Listener implements ActionListener{
	String u1 = "HERE";
	String u2 = null;
	Tree t = null;
	boolean has = false;
	Two w;
	
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
			w = new Two(t);
			has = true;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String x = ae.getActionCommand();
		create();
		boolean n = false;
		boolean rest = false;
		
		if(x.equals("Insert")){
			rest = true;
//			System.out.println("IN TWO");
			w.setVisible(true);
//			System.out.println("IN THREE");
		}
		
		else if(x.equals("Insert - No Restrictions")){
			rest = false;
//			System.out.println("IN TWO");
			w.setVisible(true);
//			System.out.println("IN THREE");
		}
		
		else if(x.equals("Submit")){
			try{
//				System.out.println("IN ONE");
				u1 = w.get1();
//				System.out.println("IN four");
				u2 = w.get2();
				if(rest)
					n = t.insert(u1, u2);
				else
					n = t.insertDirect(u1, u2);
				w.clear();
				w.setVisible(false);
				w.dispose();
				if(n)
					System.out.println("Success");
			}catch(Exception r){
				System.out.println("Catch Listener " + x);
				Test.error(r);
			}
		}
		
	}
}
