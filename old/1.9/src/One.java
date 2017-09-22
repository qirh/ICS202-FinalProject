import javax.swing.ImageIcon;
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
		lblEnterPath.setBounds(30, 55, 200, 20);
		contentPane.add(lblEnterPath);
		
		textField = new JTextField();
		textField.setBounds(30, 85, 360, 25);
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
		
		setTitle("Window #1");
		this.setIconImage(new ImageIcon("C:\\Users\\sal7\\Desktop\\Logo Green HD.png").getImage());
		
		setVisible(false);
	}
	
	public void prn(String s){
		System.out.println(s);
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
