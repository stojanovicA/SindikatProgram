package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import controlerFK.KontrolerFK;
import domen.ClanSindikata;
import domen.KontrolerFKKonstanta;
import domen.KontrolerPLKonstanta;
import domen.TransferKlasa;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class KorisnikForma extends JFrame  {

	private JPanel contentPane;
	private JTextField tfPretraga;
	private JTable table;
	private DefaultTableModel dtm = new DefaultTableModel();
	private String pB;
	private String ime;
	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
	private static URL logoPath;
	
	
	public KorisnikForma() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 807);
		this.setTitle("Sindikat IGB");
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		scrollPane.setBounds(8, 206, 594, 499);
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
		btnTraži.setBounds(182, 167, 90, 26);
		contentPane.add(btnTraži);
		
		tfPretraga = new JTextField();
		tfPretraga.setBounds(10, 167, 162, 26);
		contentPane.add(tfPretraga);
		tfPretraga.setColumns(10);

	
		
		JButton btnDodajClana = new JButton(new ImageIcon(getClass().getResource("/slike/add-contact-icon (1).jpg")));
		btnDodajClana.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDodajClana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DodajClanaForma dodajClana = new DodajClanaForma();
				dodajClana.setVisible(true);
			}
		});
		btnDodajClana.setBounds(307, 107, 90, 86);
		contentPane.add(btnDodajClana);
		
		JLabel lblNewLabel = new JLabel("Pretraga");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel.setBounds(73, 154, 46, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnUpisiPozajmicu = new JButton("<html>Izdaj nov\u010Danu<br /><center>naknadu</center></html>");
		btnUpisiPozajmicu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClanSindikata clan = new ClanSindikata(pB,ime);
				UpisiPozajmicuForma pozajmica = new UpisiPozajmicuForma(clan);
				pozajmica.setVisible(true);
//				JOptionPane.showMessageDialog(pozajmica, "lalalalalal");
			}
		});
		btnUpisiPozajmicu.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpisiPozajmicu.setBounds(207, 710, 190, 55);
		contentPane.add(btnUpisiPozajmicu);
		
		JButton btnIzdajPotvrdu = new JButton("Izdaj potvrdu");
		btnIzdajPotvrdu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClanSindikata clan = new ClanSindikata(pB,ime);
				IzdajPotvrduForma izdajPotvrduForma = new IzdajPotvrduForma(clan);
				izdajPotvrduForma.setVisible(true);
				
			}
		});
		btnIzdajPotvrdu.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnIzdajPotvrdu.setBounds(7, 710, 190, 55);
		contentPane.add(btnIzdajPotvrdu);
		
		
		JButton btnIstorija = new JButton("Vidi istoriju \u010Dlana");
		btnIstorija.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnIstorija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClanSindikata clan = new ClanSindikata(pB,ime);
			
				IstorijaClana istorijaClana = new IstorijaClana(clan);
				istorijaClana.setVisible(true);
				
				
			}
		});
		btnIstorija.setBounds(412, 710, 190, 55);
		contentPane.add(btnIstorija);
		
		JButton btnOsveziListu = new JButton(new ImageIcon(getClass().getResource("/slike/refresuj.png")));
		btnOsveziListu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.clearSelection();
				postaviPodatke();
			}
		});
		btnOsveziListu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnOsveziListu.setBounds(542, 133, 60, 60);
		contentPane.add(btnOsveziListu);
		
		JLabel lblNewLabel_1 = new JLabel("Dodaj \u010Dlana");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1.setBounds(323, 192, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Osve\u017Ei listu");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1_1.setBounds(545, 192, 72, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblLogoIGB = new JLabel(new ImageIcon(getClass().getResource("/slike/logoIgbGlavni.png")));
		lblLogoIGB.setBounds(0, 0, 255, 87);
		contentPane.add(lblLogoIGB);
		
		JLabel lblLogoSindikat = new JLabel(new ImageIcon(getClass().getResource("/slike/sindikatLogo.jpg")));
		lblLogoSindikat.setBounds(455, 0, 52, 52);
		contentPane.add(lblLogoSindikat);
		
		JLabel lblSindText =  new JLabel(new ImageIcon(getClass().getResource("/slike/sindikatText.jpg")));
		lblSindText.setBounds(514, 0, 94, 52);
		contentPane.add(lblSindText);
		
		JButton btnDodajClana_1 = new JButton(new ImageIcon(getClass().getResource("/slike/izbaciClana.jpg")));
		btnDodajClana_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int input = JOptionPane.showConfirmDialog(contentPane, "Da li ste sigurni da želite da izbacite člana? (Član će pocrveneti u listi)  ");
				
				if(input == 0 ) {
					ClanSindikata clan = new ClanSindikata(pB);
					TransferKlasa transferKlasa = new TransferKlasa();
				    transferKlasa.setRequest(clan);
				    transferKlasa.setKontrolerFk(KontrolerFKKonstanta.CLANSINDIKATA);
				    transferKlasa.setKontrolerPl(KontrolerPLKonstanta.UPDATE);
				    try {
				    	KontrolerFK.getInstanca().execute(transferKlasa);
				    	JOptionPane.showMessageDialog(contentPane, "Uspešno ste izmenili člana");
				    	postaviPodatke();
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(contentPane, "Dogodila se greška !");
					}
				    
						
					} 
				
				
			}
		});
		btnDodajClana_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDodajClana_1.setBounds(417, 107, 90, 86);
		contentPane.add(btnDodajClana_1);
		
		JLabel lblNewLabel_1_2 = new JLabel(" Izbaci \u010Dlana");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel_1_2.setBounds(430, 192, 80, 14);
		contentPane.add(lblNewLabel_1_2);
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


