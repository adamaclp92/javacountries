package progtech_beadando;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;


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
		contentPane.setBackground(new Color(0, 204, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane countries = new JScrollPane();
		countries.setViewportBorder(new LineBorder(new Color(0, 204, 0), 1, true));
		countries.setBounds(21, 142, 382, 108);
		contentPane.add(countries);
		
		//a a táblában valamelyik adatra rákattintunk, akkor a column és a row változóba kerül letárolásra a sor és az oszlop
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
		
		//új ország gombra kattintva AddCountryWindow frame
		JButton btnNewCountry = new JButton("\u00DAj orsz\u00E1g hozz\u00E1ad\u00E1sa");
		btnNewCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCountryWindow();
			}
		});
		btnNewCountry.setBounds(239, 55, 164, 23);
		contentPane.add(btnNewCountry);
		
		//Tartalom törlése gomb, a két text szövegét kitörli
		JButton btnRefresh = new JButton("Tartalom t\u00F6rl\u00E9se");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			textNationality.setText("");
			textCountry.setText("");
			}
		});
		btnRefresh.setBounds(239, 108, 164, 23);
		contentPane.add(btnRefresh);
		
		JLabel lblNewLabel = new JLabel("Orsz\u00E1g karbantart\u00F3");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(194, 11, 182, 23);
		contentPane.add(lblNewLabel);
		
		textNationality = new JTextField();
		textNationality.setBackground(Color.WHITE);
		
		//nationality text-be írásnál szûrés
		textNationality.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				list(country, "SELECT * FROM countries WHERE nationality_mark LIKE "
						+ "'%"+ textNationality.getText() +"%' "
						+ "AND country LIKE '%"+ textCountry.getText() +"%'");	
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				list(country, "SELECT * FROM countries WHERE nationality_mark LIKE "
						+ "'%"+ textNationality.getText() +"%' "
						+ "AND country LIKE '%"+ textCountry.getText() +"%'");	
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				list(country, "SELECT * FROM countries WHERE nationality_mark LIKE "
						+ "'%"+ textNationality.getText() +"%' "
						+ "AND country LIKE '%"+ textCountry.getText() +"%'");	
			}});
		
		textNationality.setBounds(21, 109, 65, 22);
		contentPane.add(textNationality);
		textNationality.setColumns(10);
		
		textCountry = new JTextField();
		textCountry.setBackground(Color.WHITE);
		
		//country text-be való írásnál szûrés, insertnél figyeli, hogy a text hossza min 3 legyen
		textCountry.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(textCountry.getText().length() >= 3) {
				list(country, "SELECT * FROM countries WHERE nationality_mark LIKE "
						+ "'%"+ textNationality.getText() +"%' "
						+ "AND country LIKE '%"+ textCountry.getText() +"%'");	
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				list(country, "SELECT * FROM countries WHERE nationality_mark LIKE "
						+ "'%"+ textNationality.getText() +"%' "
						+ "AND country LIKE '%"+ textCountry.getText() +"%'");	
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if(textCountry.getText().length() >= 3) {
				list(country, "SELECT * FROM countries WHERE nationality_mark LIKE "
						+ "'%"+ textNationality.getText() +"%' "
						+ "AND country LIKE '%"+ textCountry.getText() +"%'");	
				}
				
			}});

		textCountry.setBounds(96, 109, 86, 22);
		contentPane.add(textCountry);
		textCountry.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Fels\u00E9gjel:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(21, 89, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Orsz\u00E1g:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(96, 89, 86, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Inic.class.getResource("/images/pngwing.com.png")));
		lblNewLabel_3.setBounds(-14, 11, 182, 67);
		contentPane.add(lblNewLabel_3);
		
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

