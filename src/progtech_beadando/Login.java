package progtech_beadando;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField passwordField;

	public Login() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bejelentkez\u00E9s");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(149, 10, 202, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Felhaszn\u00E1l\u00F3n\u00E9v:");
		lblNewLabel_1.setBounds(24, 61, 105, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Jelsz\u00F3:");
		lblNewLabel_2.setBounds(24, 152, 70, 29);
		contentPane.add(lblNewLabel_2);
		
		textUsername = new JTextField();
		textUsername.setBounds(24, 96, 96, 19);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(24, 191, 96, 19);
		contentPane.add(passwordField);
		
		repaint();
		validate();
	}
}
