package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controlerFK.KontrolerFK;
import domen.ClanSindikata;
import domen.KontrolerFKKonstanta;
import domen.KontrolerPLKonstanta;
import domen.TransferKlasa;
import view.KorisnikForma.MonCellRenderer;

public class AdminVratiClana extends JFrame {

	private JPanel contentPane;
	private JTextField tfPretraga;
	private JTable table;
	private DefaultTableModel dtm = new DefaultTableModel();
	private String pB;
	private String ime;
	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
	
	
	
	public AdminVratiClana() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 626, 678);
		this.setTitle("Vrati clana");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		scrollPane.setBounds(8, 89, 594, 539);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		table.setRowHeight(50);
		
	
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC , 15));
		table.setFont(new Font("Tahoma", Font.BOLD, 15));  // ovdeeeeeee
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int red = table.getSelectedRow();
		        pB = (String) table.getModel().getValueAt(red, 0);
		        ime = (String) table.getModel().getValueAt(red, 1);
			}
		});
		scrollPane.setViewportView(table);
		Object[]kolone = {"PB","Ime i prezime", "Član od", "*"};
	
		 table.setForeground(Color.black);
		 table.setBackground(Color.lightGray);

		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(370);
		table.getColumnModel().getColumn(2).setPreferredWidth(114);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.setDefaultRenderer(Object.class, new MonCellRenderer());
		

	    
		
	    
		
		postaviPodatke();
		//sindikat
		
		JButton btnTraži = new JButton("Tra\u017Ei !");
		btnTraži.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTraži.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm.setRowCount(0);
				TransferKlasa transferKlasa = new TransferKlasa();
				transferKlasa.setKontrolerFk(KontrolerFKKonstanta.CLANSINDIKATA);
				transferKlasa.setKontrolerPl(KontrolerPLKonstanta.GET);
				KontrolerFK.getInstanca().execute(transferKlasa);
				List<ClanSindikata>list = (List<ClanSindikata>) transferKlasa.getResponse();
				String find = tfPretraga.getText();
				
				
				for (ClanSindikata clanS : list) {
					if(clanS.getIme().toLowerCase().contains(find.toLowerCase()) || find.equals(String.valueOf(clanS.getPb()))) {
						Object[]red = {clanS.getPb(),clanS.getIme(),clanS.getDatumOtvaranja(),clanS.getAktivan()};
						dtm.addRow(red);
						
					}
				}
			}
		});
		btnTraži.setBounds(182, 52, 90, 26);
		contentPane.add(btnTraži);
		
		tfPretraga = new JTextField();
		tfPretraga.setBounds(8, 52, 162, 26);
		contentPane.add(tfPretraga);
		tfPretraga.setColumns(10);

	
		
		
		JLabel lblNewLabel = new JLabel("Pretraga");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel.setBounds(57, 37, 46, 14);
		contentPane.add(lblNewLabel);
		
		
		
		
		
		
		
		JButton btnOsveziListu = new JButton(new ImageIcon(getClass().getResource("/slike/refresuj.png")));
		btnOsveziListu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.clearSelection();
				postaviPodatke();
			}
		});
		btnOsveziListu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOsveziListu.setBounds(542, 11, 60, 60);
		contentPane.add(btnOsveziListu);
		
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Osve\u017Ei listu");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1_1.setBounds(542, 71, 72, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnVratilana = new JButton("Vrati člana");
		btnVratilana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(contentPane, "Da li ste sigurni da želite da vratite člana? (Član će prekinuti da bude crven u listi) ");
				
				if(input == 0 ) {
					ClanSindikata clan = new ClanSindikata(pB);
					TransferKlasa transferKlasa = new TransferKlasa();
				    transferKlasa.setRequest(clan);
				    transferKlasa.setKontrolerFk(KontrolerFKKonstanta.CLANSINDIKATA);
				    transferKlasa.setKontrolerPl(KontrolerPLKonstanta.VRATI_CLANA);
				    try {
				    	KontrolerFK.getInstanca().execute(transferKlasa);
				    	JOptionPane.showMessageDialog(contentPane, "Uspešno ste vratili člana");
				    	postaviPodatke();
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(contentPane, "Dogodila se greška !");
					}
				    
						
					} 
			}
		});
		btnVratilana.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnVratilana.setBounds(373, 37, 128, 41);
		contentPane.add(btnVratilana);
	}


	private void postaviPodatke() {
		dtm.setRowCount(0);
		TransferKlasa transferKlasa = new TransferKlasa();
		transferKlasa.setKontrolerFk(KontrolerFKKonstanta.CLANSINDIKATA);
		transferKlasa.setKontrolerPl(KontrolerPLKonstanta.GET);
		KontrolerFK.getInstanca().execute(transferKlasa);
		
	List<ClanSindikata>list = (List<ClanSindikata>) transferKlasa.getResponse();
		for(ClanSindikata clanSindikata : list ) {
			
		
			
			Object[]red = {clanSindikata.getPb(),clanSindikata.getIme(),clanSindikata.getDatumOtvaranja(),clanSindikata.getAktivan()};
			dtm.addRow(red);
			
		}
//		table.setDefaultRenderer(Object.class, new MonCellRenderer());
		
	}
	
	 public class MonCellRenderer extends DefaultTableCellRenderer {

	        public Component getTableCellRendererComponent(JTable table, Object value,
	                        boolean isSelected, boolean hasFocus, int row, int column) {
	            super.getTableCellRendererComponent(table, value,
	                            isSelected, hasFocus, row, column);
	            int modelRow = table.convertRowIndexToModel(row);
				String type = (String)table.getModel().getValueAt(modelRow, 3);

	            if (isSelected) {
	            	setBackground(Color.yellow);
	            }else if("NE".equals(type)) {
		                setBackground(Color.RED);
	            	
	            }

	            else {
	                setBackground(Color.lightGray);
	            }
	            return this;
			 
		}
	}
}


