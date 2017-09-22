import java.io.File;

public class Test {
	
	public static void pr(String s){		//w/o \n
		System.out.print(s);
	}
	public static void prn(String s){
		System.out.println(s);
	}
	
	public static void main(String[] args){
		try{
//			File f = new File("C:\\Users\\sal7\\Desktop\\test1");
		
			Tree t = new Tree("D:\\Hard Downloads");	//"C:\\Users\\sal7\\Desktop\\test1");
			t.visit(t.root);
			t.scan();
			t.searchName("1.txt");
//			t.print();
//			t.searchPath("C:\\Users\\sal7\\Desktop\\test1\\test2\\null");
			t.searchNameW("Patch 1.4");
		
//			t.insert("C:\\Users\\sal7\\Desktop\\test1", "C:\\Users\\sal7\\Desktop\\test1\\hello");
//			t.delete("C:\\Users\\sal7\\Desktop\\test1\\delete");
		}
		catch(GreaterThanMaxException e){
			prn("Error, " + e.getMessage() + " Program will Exit");
		}
	}

}
