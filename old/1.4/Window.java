import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.UIManager;

public class Window extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField;
	String u1 = null;
	String u2 = null;
	Tree t = null;
	
	public Window(Tree t) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(5, 15, 97, 25);
		contentPane.add(btnInsert);
		btnInsert.addActionListener(this);
		
		JButton btnInsert2 = new JButton("Insert - No Restrictions");
		btnInsert2.setBounds(5, 50, 190, 25);
		contentPane.add(btnInsert2);
		btnInsert2.addActionListener(this);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(5, 85, 97, 25);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(this);
		
		JButton btnSearch = new JButton("Search by Path");
		btnSearch.setBounds(5, 120, 190, 25);
		contentPane.add(btnSearch);
		btnSearch.addActionListener(this);
		
		JButton btnSort = new JButton("Sort");
		btnSort.setBounds(5, 190, 97, 25);
		contentPane.add(btnSort);
		btnSort.addActionListener(this);
		
		JButton button = new JButton("Search by Name");
		button.setBounds(5, 155, 190, 25);
		contentPane.add(button);
		button.addActionListener(this);
		
		textField = new JTextField();
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
	@Override
	public void actionPerformed(ActionEvent ae){
		String x = ae.getActionCommand();
		boolean n = false;
		if(x.equals("Insert")){
			u1 = JOptionPane.showInputDialog("Please Insert Parents Path");
			u2 = JOptionPane.showInputDialog("Please Insert Childs Path");
			try{
				n = t.insert(u1, u2);
			}catch(Exception r){
				Test.error(r);
			}
		}
		if(x.equals("Insert - No Restrictions")){
			u1 = JOptionPane.showInputDialog("Please Insert Parents Path");
			u2 = JOptionPane.showInputDialog("Please Insert Childs Path");
			try{
				n = t.insertDirect(u1, u2);
			}catch(Exception r){
				Test.error(r);
			}
		}
	}
}
