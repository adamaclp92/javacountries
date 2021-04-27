package progtech_beadando;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.*;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Inic extends JFrame {

	private static final Connection con = new DB().getCon();
	private ResultSet rs;
	
	private JPanel contentPane;
	private JTable country;
	private JTextField textNationality;
	private JTextField textCountry;
	


	public Inic() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane countries = new JScrollPane();
		countries.setBounds(21, 142, 382, 108);
		contentPane.add(countries);
		
		country = new JTable();
		country.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = country.columnAtPoint(e.getPoint());
				int row = country.rowAtPoint(e.getPoint());
				updateCountryWindow(row);
			}
		});
		countries.setViewportView(country);
		country.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Id", "Fels\u00E9gjel", "Orsz\u00E1g"
			}
		));
		
		JButton btnNewCountry = new JButton("\u00DAj orsz\u00E1g");
		btnNewCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCountryWindow();
			}
		});
		btnNewCountry.setBounds(10, 43, 89, 23);
		contentPane.add(btnNewCountry);
		
		JButton btnSearch = new JButton("Keres\u00E9s");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				list(country, "SELECT * FROM countries WHERE nationality_mark LIKE "
						+ "'%"+ textNationality.getText() +"%' "
						+ "AND country LIKE '%"+ textCountry.getText() +"%'");
			}
		});
		btnSearch.setBounds(208, 89, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnRefresh = new JButton("Friss\u00EDt\u00E9s");
		btnRefresh.setBounds(314, 89, 89, 23);
		contentPane.add(btnRefresh);
		
		JLabel lblNewLabel = new JLabel("Orsz\u00E1g karbantart\u00F3");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(129, 11, 182, 23);
		contentPane.add(lblNewLabel);
		
		textNationality = new JTextField();
		textNationality.setBounds(21, 90, 65, 22);
		contentPane.add(textNationality);
		textNationality.setColumns(10);
		
		textCountry = new JTextField();
		textCountry.setBounds(96, 90, 86, 20);
		contentPane.add(textCountry);
		textCountry.setColumns(10);
		
		repaint();
		validate();
		refresh();
	}
	
	public void refresh() {
			list(country, "SELECT * FROM countries");
	}
	
	public void list(JTable table, String query) {
		try {
			Statement stm = con.createStatement();
			rs = stm.executeQuery(query);
		//sorok törlése
		int rows_count = ((DefaultTableModel)table.getModel()).getRowCount();
		for(int i = rows_count-1; i>=0; i--) {
			((DefaultTableModel)table.getModel()).removeRow(i);
		}
		
		//aktuális sorok megjelenítése
		while(rs.next()) {
			((DefaultTableModel)table.getModel())
			.addRow(new Object[] {
					rs.getString(1),
					rs.getString(2),
					rs.getString(3)
			});
			
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addCountryWindow() {
		new AddCountryWindow(this);
	}
	
	public void updateCountryWindow(int row) {
		new UpdateCountryWindow(this, Integer.parseInt((String)((DefaultTableModel)country.getModel()).getValueAt(row, 0)));
	}

}

