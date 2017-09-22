/*
 * 
 * Tester class
 */

package Project202;

import javax.swing.ImageIcon;

public class Test {
	
	protected static String about = "D:\\Academic\\Project\\about.txt";
	protected static String help = "D:\\Academic\\Project\\help.txt";
	protected static ImageIcon image = new ImageIcon("D:\\Academic\\Project\\Logo Green HD.png");
	
	public static void main(String[] args){
		try{
			Tree t = new Tree("C:\\Users\\sal7\\Desktop\\test");
			
//			Tree.prn(""+t.insertFile("C:\\Users\\sal7\\Desktop\\test\\jjj\\1.txt"));
		}
		catch(Exception m){
			Tree.prn("Catch " + m.getMessage());
			Tree.error(m);
		}
	}
}
