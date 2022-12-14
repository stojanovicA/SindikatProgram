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

import controlerFK.KontrolerFK;
import domen.ClanSindikata;
import domen.Firma;
import domen.KontrolerFKKonstanta;
import domen.KontrolerPLKonstanta;
import domen.TransferKlasa;
import view.IzdajPotvrduForma.MonCellRenderer;

import java.awt.ScrollPane;
import java.util.List;

import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaFirmiForma extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel dtm = new DefaultTableModel();
	private JTable table;
	private JTextField tfPretraga;
	private JButton btnPrekiniSaradnju;
	private JButton btnVratiSaradnju;
	private JButton btnDodajFirmu;
	private JButton btnIzmeniFirmu;
	private int pib;
	private JButton btnNewButton_1;
	private String imeFirmice;
	private int brojRatica;
	private int dozIznosic;
	private String napomena;
	

	
	public ListaFirmiForma() {
		setTitle("Lista firmi");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 734, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 91, 700, 505);
		contentPane.add(scrollPane);
		
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int red = table.getSelectedRow();
		        pib = (int) table.getModel().getValueAt(red, 0);
		        imeFirmice = (String) table.getModel().getValueAt(red, 1);
		        brojRatica = (int) table.getModel().getValueAt(red, 2);
		    	dozIznosic = (int) table.getModel().getValueAt(red, 3);
		    	 napomena = (String) table.getModel().getValueAt(red, 4);
		       
		        
			}
		});
		table.setRowHeight(40);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC , 11));
		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		table.setForeground(Color.black);
		 table.setBackground(Color.lightGray);
		 table.setDefaultRenderer(Object.class, new MonCellRenderer());
		scrollPane.setViewportView(table);
		Object[]kolone = {"PIB","Naziv firme","br. Rata","Max iznos","Napomena","*"};
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);
		dtm.addColumn(kolone[5]);
		//513
		table.getColumnModel().getColumn(0).setPreferredWidth(89);
		table.getColumnModel().getColumn(1).setPreferredWidth(172);
		table.getColumnModel().getColumn(2).setPreferredWidth(62);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(299);
		table.getColumnModel().getColumn(5).setPreferredWidth(8);
		postaviPodatkeFirme();
		
		tfPretraga = new JTextField();
		tfPretraga.setBounds(10, 60, 129, 20);
		contentPane.add(tfPretraga);
		tfPretraga.setColumns(10);
		
		JButton btnNewButton = new JButton("Traži !");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ime = tfPretraga.getText();
				dtm.setRowCount(0);
				TransferKlasa transferKlasa = new TransferKlasa();
				transferKlasa.setKontrolerFk(KontrolerFKKonstanta.FIRMA);
				transferKlasa.setKontrolerPl(KontrolerPLKonstanta.GET);
				KontrolerFK.getInstanca().execute(transferKlasa);
				List<Firma>list = (List<Firma>) transferKlasa.getResponse();
				for(Firma firma : list ) {
					if(firma.getIme().toLowerCase().contains(ime.toLowerCase())) {
					Object[]red = {firma.getPib(),firma.getIme(),firma.getBrojRata(),firma.getDozvoljeniIznos(),firma.getNapomena(),firma.getStatusFirme()};
					dtm.addRow(red);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(149, 59, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("   Pretraga po nazivu");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNewLabel.setBounds(20, 46, 119, 14);
		contentPane.add(lblNewLabel);
		
		btnPrekiniSaradnju = new JButton("Prekini saradnju");
		btnPrekiniSaradnju.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPrekiniSaradnju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int input = JOptionPane.showConfirmDialog(contentPane, "           Da li ste sigurni da želite da prekinete saradnju? \n (Firma ce pocrveneti ovde, a obicnom korisniku se nece prikazati)");
				
				if(input == 0 ) {
					Firma firma = new Firma(pib);
					TransferKlasa transferKlasa = new TransferKlasa();
				    transferKlasa.setRequest(firma);
				    transferKlasa.setKontrolerFk(KontrolerFKKonstanta.FIRMA);
				    transferKlasa.setKontrolerPl(KontrolerPLKonstanta.UPDATE);
				    try {
				    	KontrolerFK.getInstanca().execute(transferKlasa);
				    	JOptionPane.showMessageDialog(contentPane, "Uspešno ste izmenili firmu");
				    	postaviPodatkeFirme();
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(contentPane, "Dogodila se greška !");
					}
				    
						
					} 
			}
		});
		btnPrekiniSaradnju.setBounds(387, 600, 137, 50);
		contentPane.add(btnPrekiniSaradnju);
		
		btnVratiSaradnju = new JButton("Vrati saradnju");
		btnVratiSaradnju.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(contentPane, "           Da li ste sigurni da želite da vratite saradnju? \n (Firma ce prestati da crveni ovde, a obicnom korisniku ce ponovo biti prikazana)");
				
				if(input == 0 ) {
					Firma firma = new Firma(pib);
					TransferKlasa transferKlasa = new TransferKlasa();
				    transferKlasa.setRequest(firma);
				    transferKlasa.setKontrolerFk(KontrolerFKKonstanta.FIRMA);
				    transferKlasa.setKontrolerPl(KontrolerPLKonstanta.UPDATE_VRATISARADNJU);
				    try {
				    	KontrolerFK.getInstanca().execute(transferKlasa);
				    	JOptionPane.showMessageDialog(contentPane, "Uspešno ste izmenili firmu");
				    	
				    	postaviPodatkeFirme();
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(contentPane, "Dogodila se greška !");
					}
				    
						
					} 
				
			}
		});
		btnVratiSaradnju.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVratiSaradnju.setBounds(571, 600, 137, 50);
		contentPane.add(btnVratiSaradnju);
		
		btnDodajFirmu = new JButton("Dodaj novu firmu");
		btnDodajFirmu.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDodajFirmu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DodajFirmu df = new DodajFirmu();
				df.setVisible(true);
			}
		});
		btnDodajFirmu.setBounds(555, 39, 155, 41);
		contentPane.add(btnDodajFirmu);
		
		btnIzmeniFirmu = new JButton("Izmeni firmu");
		btnIzmeniFirmu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Firma firma = new Firma(pib,imeFirmice,brojRatica,dozIznosic,1,napomena);
				IzmeniFirmu izf = new IzmeniFirmu(firma);
				izf.setVisible(true);
				
			}
		});
		btnIzmeniFirmu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIzmeniFirmu.setBounds(10, 600, 137, 50);
		contentPane.add(btnIzmeniFirmu);
		
		btnNewButton_1 = new JButton("Osveži ");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    table.clearSelection();
			    postaviPodatkeFirme();
			}
		});
		btnNewButton_1.setBounds(423, 57, 89, 23);
		contentPane.add(btnNewButton_1);
	}


	private void postaviPodatkeFirme() {
		dtm.setRowCount(0);
		TransferKlasa transferKlasa = new TransferKlasa();
		transferKlasa.setKontrolerFk(KontrolerFKKonstanta.FIRMA);
		transferKlasa.setKontrolerPl(KontrolerPLKonstanta.GET);
		KontrolerFK.getInstanca().execute(transferKlasa);
		List<Firma>list = (List<Firma>) transferKlasa.getResponse();
		for(Firma firma : list ) {
			Object[]red = {firma.getPib(),firma.getIme(),firma.getBrojRata(),firma.getDozvoljeniIznos(),firma.getNapomena(),firma.getStatusFirme()};
			dtm.addRow(red);
		}
	}
	public class MonCellRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
            int modelRow = table.convertRowIndexToModel(row);
			int type = (int) table.getModel().getValueAt(modelRow, 5);

			if (isSelected) {
            	setBackground(Color.yellow);
            }else if(0 == type ) {
	                setBackground(Color.RED);
            	
            }

            else {
                setBackground(Color.lightGray);
            }
            return this;
		 
	}
}
}
