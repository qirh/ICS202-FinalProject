/*
 * 
 * KeyListener Focus
 * scroll bar
 * menubar
 * 
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import java.awt.AWTEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Window extends JFrame implements AWTEventListener{//, Runnable{

	private JPanel contentPane;
	private JTextArea textField;
	JMenu exit;
	JMenu help;
	JMenuBar bar;
	Tree t = null;
	public static Listener l;
	
	public void pr(String s){		//w/o \n
		System.out.print(s);
	}
	public void prn(String s){
		System.out.println(s);
	}
	
	public Window(Tree t) {
		
		try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception ex){
        	prn("Set Look And feel");
            ex.printStackTrace();
        }
		
		prn("Constructor -Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		l = new Listener(t);
		
//		this.addKeyListener(l);
		this.setFocusable(true);
//		contentPane.addKeyListener(l);
		contentPane.setFocusable(true);
		
		this.getToolkit().addAWTEventListener(this, AWTEvent.KEY_EVENT_MASK);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(5, 15, 100, 25);
		contentPane.add(btnInsert);
		btnInsert.addActionListener(l);
		
		JButton btnInsert2 = new JButton("Insert - No Restrictions");
		btnInsert2.setBounds(5, 50, 180, 25);
		contentPane.add(btnInsert2);
		btnInsert2.addActionListener(l);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(5, 85, 100, 25);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(l);
		
		JButton btnSearch = new JButton("Search by Path");
		btnSearch.setBounds(5, 120, 180, 25);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(l);
		
		JButton btnSort = new JButton("Sort");
		btnSort.setBounds(5, 190, 100, 25);
		contentPane.add(btnSort);
		btnSort.addActionListener(l);
		
		JButton btnSort2 = new JButton("Sort - Flipped");
		btnSort2.setBounds(5, 225, 180, 25);
		contentPane.add(btnSort2);
		btnSort2.addActionListener(l);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(5, 260, 100, 25);
		contentPane.add(btnPrint);
		btnPrint.addActionListener(l);
		
		JButton button = new JButton("Search by Name");
		button.setBounds(5, 155, 180, 25);
		contentPane.add(button);
		button.addActionListener(l);
		
		textField = new JTextArea("*Welcome ..\n*The current directory begins @ " + t.root.path + "\nand has " + t.count()+"\n");
		textField.setFont(new Font("sansserif", Font.BOLD, 16));
		textField.setForeground(Color.BLUE);
		textField.setBounds(200, 10, 580, 440);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setLineWrap(true);
		textField.setEditable(false);
		

		bar = new JMenuBar();
		help = new JMenu("Help");
		exit = new JMenu("Exit");
		bar.add(help);
		bar.add(exit);
		
		help.addActionListener(l);
		exit.addActionListener(l);
		
		setJMenuBar(bar);
		
		
		this.t = t;
		/*
		Thread thr  = new Thread(this);
		thr.start();
		*/
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
	}/*
	@Override
	public void run() {
		while(true){
			prn("Focus");
//			this.addKeyListener(l);
			this.setFocusable(true);
//			contentPane.addKeyListener(l);
			contentPane.setFocusable(true);
			doNothing();
		}
		
	}
	public void doNothing(){
		try{
			Thread.sleep(1000);
		}
		catch(Exception g){
			prn("Thread Error " + g.getMessage());
		}
	}*/
	@Override
	public void eventDispatched(AWTEvent event) {
		
		if(event instanceof KeyEvent){
			KeyEvent key = (KeyEvent)event;
			int k = key.getKeyCode();
			if(key.getID()==KeyEvent.KEY_PRESSED){ //Handle key presses
				if(k == 112)
					System.out.println(key.getKeyChar() + " " + k);
				key.consume();
	      }
	    }
	  }
}
