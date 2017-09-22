/*
 * count button
 */

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
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Window extends JFrame implements AWTEventListener{

	private JPanel contentPane;
	private JTextArea textField;
	JMenuItem exit;
	JMenuItem help;
	JMenuItem about;
	JMenuBar bar;
	Tree t = null;
	public static Listener l;
	
	public void prn(String s){
		System.out.println(s);
	}
	
	public Window(Tree x) {
		
		try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception ex){
        	prn("Set Look And feel");
            ex.printStackTrace();
        }
		t = x;
		prn("Constructor -Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		l = new Listener(t);
		
		this.getToolkit().addAWTEventListener(this, AWTEvent.KEY_EVENT_MASK);		//key binding
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(5, 15, 180, 25);
		contentPane.add(btnInsert);
		btnInsert.addActionListener(l);
		
		JButton btnInsert2 = new JButton("Insert - No Restrictions");
		btnInsert2.setBounds(5, 50, 180, 25);
		contentPane.add(btnInsert2);
		btnInsert2.addActionListener(l);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(5, 85, 180, 25);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(l);
		
		JButton btndeleteRec = new JButton("Delete - Recursively");
		btndeleteRec.setBounds(5, 120, 180, 25);
		contentPane.add(btndeleteRec);
		btndeleteRec.addActionListener(l);
		
		JButton btnSearch = new JButton("Search by Path");
		btnSearch.setBounds(5, 155, 180, 25);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(l);
		
		JButton button = new JButton("Search by Name");
		button.setBounds(5, 190, 180, 25);
		contentPane.add(button);
		button.addActionListener(l);
		
		JButton btnSort = new JButton("Sort");
		btnSort.setBounds(5, 225, 180, 25);
		contentPane.add(btnSort);
		btnSort.addActionListener(l);
		
		JButton btnSort2 = new JButton("Sort - Flipped");
		btnSort2.setBounds(5, 260, 180, 25);
		contentPane.add(btnSort2);
		btnSort2.addActionListener(l);
		
		JButton btnPrint = new JButton("Print (in One Line)");
		btnPrint.setBounds(5, 295, 180, 25);
		contentPane.add(btnPrint);
		btnPrint.addActionListener(l);
		
		JButton btnPrintBFT = new JButton("Print (BFT)");
		btnPrintBFT.setBounds(5, 330, 180, 25);
		contentPane.add(btnPrintBFT);
		btnPrintBFT.addActionListener(l);
		
		JButton btnCount = new JButton("Count");
		btnCount.setBounds(5, 365, 180, 25);
		contentPane.add(btnCount);
		btnCount.addActionListener(l);

		this.setBackground(Color.lightGray);
		
		textField = new JTextArea("*The current directory begins @ " + t.root.path + "\nand has " + t.count()+"\n");
		textField.setFont(new Font("sansserif", Font.BOLD, 16));
		textField.setBackground(Color.gray);
		textField.setForeground(Color.WHITE);
		textField.setColumns(10);
		textField.setLineWrap(true);
		textField.setEditable(false);

		JScrollPane pn = new JScrollPane(textField);
		pn.setBounds(200, 10, 580, 440);
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
		this.setIconImage(new ImageIcon("C:\\Users\\sal7\\Desktop\\Logo Green HD.png").getImage());
		
		setVisible(true);
	}
	protected void set(String i){
		textField.setText(i);
	}
	protected void append(String i){
		textField.append("*"+i+"\n");
	}
	protected void appendPlain(String i){
		textField.append(i);
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
