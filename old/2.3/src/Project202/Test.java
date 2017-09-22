/*
 * 
 * Tester class
 */

package Project202;

import javax.swing.ImageIcon;

public class Test {
	
	protected final static String about = "D:\\Academic\\Project\\about.txt";
	protected final static String help = "D:\\Academic\\Project\\help.txt";
	protected final static ImageIcon image = new ImageIcon("D:\\Academic\\Project\\Logo Green HD.png");
	
	public static void main(String[] args){
		try{
			new Tree("C:\\Users\\sal7\\Desktop\\test");
		}
		catch(Exception m){
			Tree.prn("Catch " + m.getMessage());
			Tree.error(m);
		}
	}
}
