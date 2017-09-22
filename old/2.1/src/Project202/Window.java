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
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

import java.awt.AWTEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.AWTEventListener;		//for keybindings
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Window extends JFrame implements AWTEventListener{

	private JPanel contentPane;
	private JTextArea textField;
	private JMenuItem exit;			private JMenuItem help;		private JMenuItem about;
	private JMenuBar bar;
	private JButton btnInsert;		private JButton btnInsert2;	private JButton btnDelete;
	private JButton btndeleteRec;	private JButton btnSearch;	private JButton button;
	private JButton btnSort;		private JButton btnSort2;	private JButton btnPrint;
	private JButton btnPrintBFT;	private JButton btnCount;	private JButton btnReset;
	private JScrollPane pn;
	
	String u;
	Tree t = null;
	public static Listener l;
	
	public Window(Tree x) {
		
		try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception ex){
        	Tree.prn("Set Look And feel");
        	Tree.error(ex);
        }
		t = x;
		Tree.prn("Constructor -Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		l = new Listener(t);
		
		this.getToolkit().addAWTEventListener(this, AWTEvent.KEY_EVENT_MASK);		//key binding
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(5, 15, 180, 25);
		btnInsert.setBackground(Color.lightGray);
		contentPane.add(btnInsert);
		btnInsert.addActionListener(l);
		
		btnInsert2 = new JButton("Insert - No Restrictions");
		btnInsert2.setBounds(5, 50, 180, 25);
		contentPane.add(btnInsert2);
		btnInsert2.addActionListener(l);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(5, 85, 180, 25);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(l);
		
		btndeleteRec = new JButton("Delete - Recursively");
		btndeleteRec.setBounds(5, 120, 180, 25);
		contentPane.add(btndeleteRec);
		btndeleteRec.addActionListener(l);
		
		btnSearch = new JButton("Search by Path");
		btnSearch.setBounds(5, 155, 180, 25);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(l);
		
		button = new JButton("Search by Name");
		button.setBounds(5, 190, 180, 25);
		contentPane.add(button);
		button.addActionListener(l);
		
		btnSort = new JButton("Sort");
		btnSort.setBounds(5, 225, 180, 25);
		contentPane.add(btnSort);
		btnSort.addActionListener(l);
		
		btnSort2 = new JButton("Sort - Flipped");
		btnSort2.setBounds(5, 260, 180, 25);
		contentPane.add(btnSort2);
		btnSort2.addActionListener(l);
		
		btnPrint = new JButton("Print (in One Line)");
		btnPrint.setBounds(5, 295, 180, 25);
		contentPane.add(btnPrint);
		btnPrint.addActionListener(l);
		
		btnPrintBFT = new JButton("Print (BFT)");
		btnPrintBFT.setBounds(5, 330, 180, 25);
		contentPane.add(btnPrintBFT);
		btnPrintBFT.addActionListener(l);
		
		btnCount = new JButton("Count");
		btnCount.setBounds(5, 365, 180, 25);
		contentPane.add(btnCount);
		btnCount.addActionListener(l);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(5, 400, 180, 25);
		contentPane.add(btnReset);
		btnReset.addActionListener(l);

		this.setBackground(Color.lightGray);
		u = "*The current directory begins @ " + t.root.path + "\nand has ";
		
		textField = new JTextArea(u + t.count()+"\n");
		textField.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textField.setBackground(Color.gray);
		textField.setForeground(Color.WHITE);
		textField.setColumns(10);
		textField.setLineWrap(true);
		textField.setEditable(false);

		pn = new JScrollPane(textField);
		pn.setBounds(200, 10, 680, 540);
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
		this.setIconImage(new ImageIcon("D:\\Academic\\Project\\Logo Green HD.png").getImage());
		
		setVisible(true);
	}
	protected void set(String i){
		textField.setText(i);
	}
	protected void append(String i){
		textField.append("*"+i+"\n");
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
