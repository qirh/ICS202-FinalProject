package tst;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(5, 15, 97, 25);
		contentPane.add(btnInsert);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(5, 50, 97, 25);
		contentPane.add(btnDelete);
		
		JButton btnSearch = new JButton("Search by Path");
		btnSearch.setBounds(5, 85, 140, 25);
		contentPane.add(btnSearch);
		
		JButton btnSort = new JButton("Sort");
		btnSort.setBounds(5, 155, 97, 25);
		contentPane.add(btnSort);
		
		textField = new JTextField();
		textField.setBounds(152, 13, 280, 228);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("Search by Name");
		button.setBounds(5, 120, 140, 25);
		contentPane.add(button);
	}
}
