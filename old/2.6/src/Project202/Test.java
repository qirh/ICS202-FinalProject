/*
 * 
 * Tester class
 */

package Project202;

import javax.swing.ImageIcon;

public class Test {
	
	protected final static String about = "D:\\Academic\\Project\\about.txt", 
								  help = "D:\\Academic\\Project\\help.txt", 
								  path = "C:\\Users\\sal7\\Desktop\\test",
								  content = "D:\\Academic\\Project\\content.txt";
	protected final static ImageIcon image = new ImageIcon("D:\\Academic\\Project\\icon.png"), 
									 image2 = new ImageIcon("D:\\Academic\\Project\\icon2.png");
	protected final static int MAX = 50;
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		try{
			Tree y = new Tree(path);
		}
		catch(Exception m){
			Tree.error(m);
		}
	}
}
