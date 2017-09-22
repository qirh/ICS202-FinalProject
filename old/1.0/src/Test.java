import java.io.File;

public class Test {

	public static void main(String[] args) throws Exception{
		File f = new File("C:\\Users\\sal7\\Desktop\\test1");
		
		Tree t = new Tree("C:\\Users\\sal7\\Desktop\\test1");
//		System.out.println(t.root.name + " " + t.root.path + t.root.isNull);
//		t.visit(t.root);
		t.scan();
		
		t.print();
	}

}
