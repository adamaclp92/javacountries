package progtech_beadando;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField passwordField;
	
	private static final Connection con = new DB().getCon();
	private ResultSet rs;
	private Statement stm;

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
		
		JButton btnLogin = new JButton("Bejelentkez\u00E9s");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					char[] pw = passwordField.getPassword();
					String pwConcat = "";
					for(char c : pw) {
						pwConcat += c;
					}
					
					stm = con.createStatement();
					rs = stm.executeQuery("SELECT username FROM users WHERE username=" + "'" + textUsername.getText() + "' AND password=" + "'" + pwConcat + "'");

					if(rs.next()) {
						new Inic();
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Hibás felhasználónév vagy jelszó!");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnLogin.setBounds(133, 152, 128, 29);
		contentPane.add(btnLogin);
		
		repaint();
		validate();
	}
	
}
