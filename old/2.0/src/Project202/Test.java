/*
 * 
 * Tester class
 */

package Project202;

public class Test {
	
	public static void main(String[] args){
		try{
			Tree t = new Tree("C:\\Users\\sal7\\Desktop\\test1");
		}
		catch(Exception m){
			Tree.prn("Catch " + m.getMessage());
			Tree.error(m);
		}
	}
}
