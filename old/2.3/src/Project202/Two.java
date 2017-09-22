/*
 * 
 * This frame will only be called by the insert button, it has no other functions :(
 */

package Project202;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Two extends JFrame{

	private JPanel contentPane;
	private JTextField textField;		private JTextField textField_1;
	private JButton btnSubmit;
	private JLabel lblInsertChildsPath;	private JLabel lblInsertParentsPath;
	
	public Two() {
		Tree.prn("Constructor -Two");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 185+120+40, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblInsertParentsPath = new JLabel("Enter Parents Path: ");
		lblInsertParentsPath.setBounds(30, 20, 120, 25);
		contentPane.add(lblInsertParentsPath);
		
		lblInsertChildsPath = new JLabel("Enter Child's path: ");
		lblInsertChildsPath.setBounds(30, 110, 120, 25);
		contentPane.add(lblInsertChildsPath);
		
		textField = new JTextField("");
		textField.setBounds(15, 60, 290, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField("");
		textField_1.setBounds(15, 150, 290, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(110, 200, 100, 25);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(Window.l);
		
		setTitle("Window #2");
		this.setIconImage(Test.image.getImage());
		
		setVisible(false);
	}
	protected void clear(){
		textField.setText("");
		textField_1.setText("");
	}
	protected String get1(){
		return textField.getText();
	}
	protected String get2(){
		return textField_1.getText();
	}
}
