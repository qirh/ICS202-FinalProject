import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class One extends JFrame{
	
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblTmp;
	private JLabel lblEnterPath;
	
	Tree t = null;

	public One(Tree x) {
		prn("Constructor -One");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEnterPath = new JLabel("Enter Path: ");
		lblEnterPath.setBounds(33, 56, 101, 16);
		contentPane.add(lblEnterPath);
		
		textField = new JTextField();
		textField.setBounds(38, 85, 359, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(156, 150, 97, 25);
		contentPane.add(btnEnter);
		btnEnter.addActionListener(Window.l);
		
		JLabel lblOpreation = new JLabel("Opreation: ");
		lblOpreation.setBounds(127, 13, 79, 16);
		contentPane.add(lblOpreation);
		
		lblTmp = new JLabel("tmp");
		lblTmp.setBounds(197, 13, 137, 16);
		contentPane.add(lblTmp);
		
		setVisible(false);
	}
	

	
	public void pr(String s){		//w/o \n
		System.out.print(s);
	}
	public void prn(String s){
		System.out.println(s);
	}
	
	protected void clear(){
		textField.setText("");
	}
	
	protected String get(){
//		System.out.println("GET1 " + textField.getText());
		return textField.getText();
	}
	protected void set(String s){
		lblTmp.setText(s);
	}
	protected void set2(String s){
		lblEnterPath.setText(s);
	}
}
