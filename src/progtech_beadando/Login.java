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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

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
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(51, 204, 102));
		panel_1.setBounds(0, 0, 215, 261);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/images/User account _ Login free icon 1.png")));
		lblNewLabel_3.setBounds(36, 0, 179, 261);
		panel_1.add(lblNewLabel_3);
		

		panel_1.repaint();
		panel_1.validate();
		
		Panel panel = new Panel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(210, 0, 224, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel_1 = new JLabel("FELHASZN\u00C1L\u00D3N\u00C9V:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(23, 57, 120, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Bejelentkez\u00E9s");
		lblNewLabel.setBounds(60, 11, 105, 19);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblNewLabel_2 = new JLabel("JELSZ\u00D3:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(23, 111, 70, 25);
		panel.add(lblNewLabel_2);
		
		textUsername = new JTextField();
		textUsername.setForeground(new Color(255, 255, 255));
		textUsername.setBackground(new Color(0, 0, 128));
		textUsername.setBounds(23, 82, 154, 20);
		panel.add(textUsername);
		textUsername.setColumns(10);
		textUsername.setCaretColor(Color.WHITE);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setBackground(new Color(0, 0, 128));
		passwordField.setBounds(23, 141, 154, 20);
		panel.add(passwordField);
		passwordField.setCaretColor(Color.WHITE);
		
		
		//login button getPassword char tömböt ad vissza, azon ciklussal végigmegyünk, és a pwConcat változóba fûzi össze a karaktereket. Utána erre fut le a select. Ha van elem, akkor Inic frame, ha nincs, akkor nincs a users táblába ilyen rekord
		JButton btnLogin = new JButton("Bejelentkez\u00E9s");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(51, 204, 102));
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnLogin.setBounds(49, 195, 128, 29);
		panel.add(btnLogin);
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
		
		panel.repaint();
		panel.validate();
		repaint();
		validate();
	}
}
