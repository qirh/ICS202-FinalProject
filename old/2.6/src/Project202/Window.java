/*
 * 
 * This is the main GUI
 */

package Project202;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.AWTEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.AWTEventListener;		//for keybindings
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Window extends JFrame implements AWTEventListener{

	private JPanel contentPane;
	private JTextArea textField;
	private JMenuItem exit,	help,	about;
	private JMenuBar bar;
	private JScrollPane pn;
	private JButton btnInsert,	btnInsert2,	btnDelete,	btndeleteRec,	btnSearch,
					button,		btnSort,	btnSort2,	btnPrint,		btnPrintBFT,
					btnCount,	btnReset,	set,		btnInsertFile,  btnPrintPre, 
					btnPrintPost;	
	
	private Tree t = null;
	One o;
	Two w;
	Three th;
	private boolean has = false;		//creating frames
	protected static Listener l;
	
	public Window(Tree x) {
		
		setLook();			//set look and feel
		t = x;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 930, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		l = new Listener(t);
		
		this.getToolkit().addAWTEventListener(this, AWTEvent.KEY_EVENT_MASK);		//key binding
		
		btnInsertFile = new JButton("Insert File");		//Window Three
		btnInsertFile.setBounds(5, 15, 180, 25);
		contentPane.add(btnInsertFile);
		btnInsertFile.addActionListener(l);
		btnInsertFile.setBackground(Color.lightGray);
		btnInsertFile.setForeground(Color.DARK_GRAY);
		
		btnInsert = new JButton("Insert");			//Window One
		btnInsert.setBounds(5, 50, 180, 25);
		contentPane.add(btnInsert);
		btnInsert.addActionListener(l);
		btnInsert.setBackground(Color.lightGray);
		btnInsertFile.setForeground(Color.black);
		
		btnInsert2 = new JButton("Insert - No Restrictions");
		btnInsert2.setBounds(5, 85, 180, 25);
		contentPane.add(btnInsert2);
		btnInsert2.addActionListener(l);
		btnInsert2.setBackground(Color.DARK_GRAY);
		btnInsert2.setForeground(Color.white);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(5, 120, 180, 25);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(l);
		btnDelete.setBackground(Color.DARK_GRAY);
		btnDelete.setForeground(Color.white);
		
		btndeleteRec = new JButton("Delete - Recursively");
		btndeleteRec.setBounds(5, 155, 180, 25);
		contentPane.add(btndeleteRec);
		btndeleteRec.addActionListener(l);
		btndeleteRec.setBackground(Color.DARK_GRAY);
		btndeleteRec.setForeground(Color.white);
		
		btnSearch = new JButton("Search by Path");
		btnSearch.setBounds(5, 190, 180, 25);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(l);
		btnSearch.setBackground(Color.DARK_GRAY);
		btnSearch.setForeground(Color.white);
		
		button = new JButton("Search by Name");
		button.setBounds(5, 225, 180, 25);
		contentPane.add(button);
		button.addActionListener(l);
		button.setBackground(Color.DARK_GRAY);
		button.setForeground(Color.white);
		
		btnSort = new JButton("Sort");
		btnSort.setBounds(5, 260, 180, 25);
		contentPane.add(btnSort);
		btnSort.addActionListener(l);
		
		btnSort2 = new JButton("Sort - Flipped");
		btnSort2.setBounds(5, 295, 180, 25);
		contentPane.add(btnSort2);
		btnSort2.addActionListener(l);
		
		btnPrint = new JButton("Print (in One Line)");
		btnPrint.setBounds(5, 330, 180, 25);
		contentPane.add(btnPrint);
		btnPrint.addActionListener(l);
		
		btnPrintPre = new JButton("Print (Pre Order)");
		btnPrintPre.setBounds(5, 365, 180, 25);
		contentPane.add(btnPrintPre);
		btnPrintPre.addActionListener(l);
		
		btnPrintPost = new JButton("Print (Post Order)");
		btnPrintPost.setBounds(5, 400, 180, 25);
		contentPane.add(btnPrintPost);
		btnPrintPost.addActionListener(l);
		
		btnPrintBFT = new JButton("Print (BFT)");
		btnPrintBFT.setBounds(5, 435, 180, 25);
		contentPane.add(btnPrintBFT);
		btnPrintBFT.addActionListener(l);
		
		btnCount = new JButton("Count");
		btnCount.setBounds(5, 470, 180, 25);
		contentPane.add(btnCount);
		btnCount.addActionListener(l);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(5, 505, 180, 25);
		contentPane.add(btnReset);
		btnReset.addActionListener(l);
		
		set = new JButton("Set");
		set.setBounds(5, 540, 180, 25);
		contentPane.add(set);
		set.addActionListener(l);

		this.setBackground(Color.lightGray);
		
		textField = new JTextArea("*The current directory begins @ " + t.root.path + "\nand has " + t.count()+"\n");
		textField.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textField.setBackground(Color.gray);
		textField.setForeground(Color.WHITE);
		textField.setColumns(10);
		textField.setLineWrap(true);
		textField.setEditable(false);

		pn = new JScrollPane(textField);
		pn.setBounds(200, 10, 710, 570);
		pn.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pn.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(pn);
		
		bar = new JMenuBar();
		help = new JMenuItem("Help (Press F1)");
		help.addActionListener(l);
		bar.add(help);
		about = new JMenuItem("About (Press F2)");
		about.addActionListener(l);
		bar.add(about);
		exit = new JMenuItem("Exit (Press F11)");
		exit.addActionListener(l);bar.add(exit);
		
		setJMenuBar(bar);
		setTitle("202 Project");
		this.setIconImage(Test.image.getImage());
		create();
		setVisible(true);
	}
	private void create(){
		if(!has){
			th = new Three();
			o = new One();
			w = new Two();
			has = true;
		}
	}
	protected void set(String i){
		textField.setText(i);
	}
	protected void append(String i){
		textField.append("*"+i+"\n");
	}
	protected void appendPlain(String i){
		textField.append(i+"\n");
	}
	private void setLook(){
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch (UnsupportedLookAndFeelException e) {
            setLook2();
        }
        catch (ClassNotFoundException e) {
        	setLook2();
        }catch(Exception ex){
            	Tree.error(ex);
        }
	}
	private void setLook2(){
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
        }catch(Exception ex){
            	Tree.error(ex);
        }
	}
	protected void clickReset(){
		btnReset.doClick();
	}
	@Override
	public void eventDispatched(AWTEvent event) {
		if(event instanceof KeyEvent){
			KeyEvent key = (KeyEvent)event;
			int k = key.getKeyCode();
			if(key.getID()==KeyEvent.KEY_PRESSED){
				if(k == 112){
					help.doClick();
				}
				if(k == 113){
					about.doClick();
				}
				if(k == 122){
					exit.doClick();
				}
//				key.consume();
	      }
	    }
	  }
}
