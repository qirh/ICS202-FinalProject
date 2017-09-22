import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class Window extends JFrame{

	private JPanel contentPane;
	private JTextArea textField;
	Tree t = null;
	public static Listener l;
	
	public void pr(String s){		//w/o \n
		System.out.print(s);
	}
	public void prn(String s){
		System.out.println(s);
	}
	
	public Window(Tree t) {
		prn("Constructor -Window");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		l = new Listener(t);
		
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
		
		textField = new JTextArea("  Welcome ..\n  The current directory begins @ " + t.root.path + "\n  and has " + t.count()+"\n");
		textField.setFont(new Font("sansserif", Font.BOLD, 16));
		textField.setForeground(Color.BLUE);
		textField.setBounds(200, 10, 580, 440);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setLineWrap(true);
		textField.setEditable(false);
		
		
		this.t = t;
		
		try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
		
		setVisible(true);
	}
	protected void set(String i){
		textField.setText(i);
	}
	protected void append(String i){
		textField.append(i);
	}
}
