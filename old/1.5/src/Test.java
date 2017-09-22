import java.io.File;
import java.io.FileNotFoundException;

public class Test {
	
	public static void pr(String s){		//w/o \n
		System.out.print(s);
	}
	public static void prn(String s){
		System.out.println(s);
	}
	
	public static void main(String[] args){
		try{
			Tree t = new Tree("C:\\Users\\sal7\\Desktop\\test1");
			Window g = new Window(t);
//			Two g = new Two(t);
			
//			t.insert("C:\\Users\\sal7\\Desktop\\test1", 
//					"C:\\Users\\sal7\\Desktop\\test1\\tyu");
//			t.visit(t.root); prn("");
//			t.searchPath("C:\\Users\\sal7\\Desktop\\test1\\1.txt");
//			t.searchPath("C:\\Users\\sal7\\Desktop\\test1\\1");
//			t.print2();
//			t.insert("C:\\Users\\sal7\\Desktop\\test1", "C:\\Users\\sal7\\Desktop\\test1\\new");
//			t.sort();
//			t.print2();
		}
		catch(Exception m){
			prn("Catch " + m.getMessage());
			error(m);
		}
//		finally{
//			prn("BYEEEE");
//			System.exit(0);
//		}
	}
	public static void error(Exception e){
		if(e.getClass().getName().equals("GreaterThanMaxException"))
			prn("Error, " + e.getMessage() + ". Program will Exit");
		
		else if(e.getClass().getName().equals("FileNotFoundException"))
			prn("Error, " + e.getMessage() + ". Program will Exit");
		
		else if(e.getClass().getName().equals("Exception"))
			prn("Unknown Error, Message: " + e.getMessage() + ". Program will Exit");
		else{
			e.printStackTrace();
			prn("Unreachable " + e.getMessage());
		}
	}

}
