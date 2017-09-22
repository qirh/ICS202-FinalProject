/*
 * 
 * This frame will be called by the buttons that require one input such as the Search buttons
 */

package Project202;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class One extends JFrame{
	
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblTmp,	lblEnterPath,	lblOpreation;
	private JButton btnEnter;

	public One() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEnterPath = new JLabel("Enter Path: ");
		lblEnterPath.setBounds(30, 55, 200, 20);
		contentPane.add(lblEnterPath);
		
		textField = new JTextField();
		textField.setBounds(30, 85, 360, 35);
		contentPane.add(textField);
		
		textField.setColumns(10);
		
		btnEnter = new JButton("Enter");
		btnEnter.setBounds(156, 150, 97, 25);
		contentPane.add(btnEnter);
		btnEnter.setActionCommand("E1");
		btnEnter.addActionListener(Window.l);
		
		lblOpreation = new JLabel("Opreation:");
		lblOpreation.setBounds(105, 15, 80, 20);
		contentPane.add(lblOpreation);
		
		lblTmp = new JLabel("tmp");
		lblTmp.setBounds(170, 15, 140, 20);
		contentPane.add(lblTmp);

		setTitle("tmp");
		this.setIconImage(Test.image2.getImage());
		
		setVisible(false);
	}
	protected void clear(){
		textField.setText("");
	}
	protected String get(){
		return textField.getText();
	}
	protected void set(String s){
		lblTmp.setText(s);
	}
	protected void set2(String s){
		lblEnterPath.setText(s);
	}
}
