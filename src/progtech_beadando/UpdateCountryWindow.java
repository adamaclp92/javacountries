package progtech_beadando;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateCountryWindow extends JFrame {

	private static final Connection con = new DB().getCon();
	private Statement stm;
	
	private JPanel contentPane;
	private JFrame mainWindow;
	private JTextField textNationality;
	private JTextField textCountry;

	public UpdateCountryWindow(Inic mainWindow, int id) {
		this.mainWindow = mainWindow;
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 250, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Orsz\u00E1g m\u00F3dos\u00EDt\u00E1sa");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(35, 11, 242, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fels\u00E9gjel:");
		lblNewLabel_1.setBounds(10, 57, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Orsz\u00E1g:");
		lblNewLabel_2.setBounds(10, 99, 67, 14);
		contentPane.add(lblNewLabel_2);
		
		
		//Update button: valamelyik text üres, akkor üzenet, különben pedig update és mainwindow tábla refresh
		JButton btnUpdateCountry = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnUpdateCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNationality.getText().isEmpty() || textCountry.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "A felségjel és/vagy ország mezõ nem lehet üres!");
				}else {
					PreparedStatement ps;
					try {
						ps = con.prepareStatement("UPDATE countries SET nationality_mark=?, country=? WHERE id=?");
						ps.setString(1, textNationality.getText());
						ps.setString(2, textCountry.getText());
						ps.setString(3, String.valueOf(id));
						ps.execute();
						mainWindow.refresh();
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnUpdateCountry.setBounds(10, 151, 103, 23);
		contentPane.add(btnUpdateCountry);
		
		textNationality = new JTextField();
		textNationality.setBounds(102, 54, 86, 20);
		contentPane.add(textNationality);
		textNationality.setColumns(10);
		
		textCountry = new JTextField();
		textCountry.setBounds(102, 96, 86, 20);
		contentPane.add(textCountry);
		
		//A két text-be betöltõdik az az ország, amelyikre a táblázatban rá lett kattintva
		try {
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM countries WHERE id="+id);
			rs.next();
			textNationality.setText(rs.getString(2));
			textCountry.setText(rs.getString(3));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//Törlés gomb: csak egy megerõsítés kérés, 0 az igen
		JButton btnDelete = new JButton("T\u00F6rl\u00E9s");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "Biztos, hogy törölni akarod az elemet?");
				
				if(input == 0) {
					try {
						stm = con.createStatement();
						stm.executeUpdate("DELETE FROM countries WHERE id = "+id);
						mainWindow.refresh();
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnDelete.setBounds(135, 151, 89, 23);
		contentPane.add(btnDelete);
		
		
	}

}
