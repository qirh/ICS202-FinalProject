import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;

import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Window extends JFrame{

	private JPanel contentPane;
	private JTextArea textField;
	Tree t = null;
	public static Listener l;
	
	public Window(Tree t) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		l = new Listener(t);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(5, 15, 97, 25);
		contentPane.add(btnInsert);
		btnInsert.addActionListener(l);
		
		JButton btnInsert2 = new JButton("Insert - No Restrictions");
		btnInsert2.setBounds(5, 50, 190, 25);
		contentPane.add(btnInsert2);
		btnInsert2.addActionListener(l);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(5, 85, 97, 25);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(l);
		
		JButton btnSearch = new JButton("Search by Path");
		btnSearch.setBounds(5, 120, 190, 25);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(l);
		
		JButton btnSort = new JButton("Sort");
		btnSort.setBounds(5, 190, 97, 25);
		contentPane.add(btnSort);
		btnSort.addActionListener(l);
		
		JButton button = new JButton("Search by Name");
		button.setBounds(5, 155, 190, 25);
		contentPane.add(button);
		button.addActionListener(l);
		
		textField = new JTextArea("init");
		textField.setBounds(200, 10, 280, 228);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		this.t = t;
		
		try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
		
		setVisible(true);
	}
}
