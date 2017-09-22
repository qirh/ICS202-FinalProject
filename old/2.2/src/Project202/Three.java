package Project202;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Three extends JFrame {

	private JPanel contentPane;
	private JTextField textField;		private JTextArea textArea;
	private JLabel lblNewLabel1;		private JLabel lblNewLabel_1;
	private JButton btnCreate;
	
	public Three() {
		Tree.prn("Constructor -Three");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel1 = new JLabel("Enter Path of file (please include .type) :");
		lblNewLabel1.setBounds(10, 25, 370, 30);
		contentPane.add(lblNewLabel1);
		
		textField = new JTextField();
		textField.setBounds(12, 59, 408, 30);
		contentPane.add(textField);
		textField.setForeground(Color.gray);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Enter The Contents of the file:");
		lblNewLabel_1.setBounds(10, 95, 170, 30);
		contentPane.add(lblNewLabel_1);
		
		textArea = new JTextArea();
		textArea.setBounds(12, 128, 458, 126);
		textArea.setForeground(Color.gray);
		contentPane.add(textArea);
		
		btnCreate = new JButton("Create");
		btnCreate.setBounds(195, 267, 97, 25);
		contentPane.add(btnCreate);
		btnCreate.addActionListener(Window.l);
		
		setTitle("Window #3");
		this.setIconImage(Test.image.getImage());
		
		setVisible(false);
	}
	protected void clear(){
		textField.setText("");
		textArea.setText("");
	}
	protected String get1(){
		return textField.getText();
	}
	protected String get2(){
		return textArea.getText();
	}
}
