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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class AddCountryWindow extends JFrame {

	private static final Connection con = new DB().getCon();
	
	private JPanel contentPane;
	private Inic mainWindow;
	private JTextField textNationality;
	private JTextField textCountry;

	public AddCountryWindow(Inic mainWindow) {
		this.mainWindow = mainWindow;
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 250, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u00DAj orsz\u00E1g felv\u00E9tele");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(10, 11, 191, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fels\u00E9gjel:");
		lblNewLabel_1.setBounds(10, 67, 68, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Orsz\u00E1g:");
		lblNewLabel_2.setBounds(10, 110, 68, 14);
		contentPane.add(lblNewLabel_2);
		
		textNationality = new JTextField();
		textNationality.setBounds(88, 64, 102, 20);
		contentPane.add(textNationality);
		textNationality.setColumns(10);
		
		textCountry = new JTextField();
		textCountry.setBounds(88, 107, 102, 20);
		contentPane.add(textCountry);
		textCountry.setColumns(10);
		
		
		
		JButton btnAddSubmit = new JButton("R\u00F6gz\u00EDt");
		btnAddSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNationality.getText().isEmpty() || textCountry.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "A felségjel és/vagy ország mezõ nem lehet üres!");
				}else {
					try {
						PreparedStatement ps = con.prepareStatement("INSERT INTO countries VALUES (?,?,?)");
						ps.setString(1, null);
						ps.setString(2, textNationality.getText());
						ps.setString(3, textCountry.getText());
						ps.execute();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					
					mainWindow.refresh();
					dispose();
				}
			}
		});
		
		btnAddSubmit.setBounds(67, 161, 89, 23);
		contentPane.add(btnAddSubmit);
	}
}
