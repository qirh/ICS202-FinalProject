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
		
			Tree t = new Tree("C:\\Users\\sal7\\Desktop\\test1");	//"C:\\Users\\sal7\\Desktop\\test1"
			t.visit(t.root);
			t.scan();
			t.searchName("1.txt");
			t.searchNameW("1.txt");
			
			t.print2();
			t.insert("C:\\Users\\sal7\\Desktop\\test1", "C:\\Users\\sal7\\Desktop\\test1\\new");
			t.sort();
			prn("\n\n --- Print 2 ---\n");
			t.print2();
		}
		catch(GreaterThanMaxException e){
			prn("Error, " + e.getMessage() + ". Program will Exit");
		}
		catch(FileNotFoundException e){
			prn("Error, " + e.getMessage() + ". Program will Exit");
		}
	}

}
