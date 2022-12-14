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
import domen.ClanPotvrda;
import domen.ClanPozajmica;
import domen.ClanSindikata;
import domen.Firma;
import domen.KontrolerFKKonstanta;
import domen.KontrolerPLKonstanta;
import domen.TransferKlasa;
import servis.ClanSindikataServisSacuvaj;
import view.IzdajPotvrduForma.MonCellRenderer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IstorijaClana extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table1;
	private DefaultTableModel dtm = new DefaultTableModel();
	private DefaultTableModel dtm1 = new DefaultTableModel();
	private ClanSindikata clan;
	private String infoPotvrde;
	int selektovano;
	int iddd;
	String text;
	

	public IstorijaClana(ClanSindikata clan) {
		
		
		this.clan = clan;
		
		
		
		
		
		
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 648);
		this.setTitle(clan.getIme());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("NOV\u010CANE NAKNADE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(155, 32, 182, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPotvrde = new JLabel("POTVRDE");
		lblPotvrde.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPotvrde.setBounds(190, 280, 148, 14);
		contentPane.add(lblPotvrde);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 57, 465, 193);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		Object[]kolonice = {"Datum izdavanja","Vrsta", "Iznos"};
		dtm.addColumn(kolonice[0]);
		dtm.addColumn(kolonice[1]);
		dtm.addColumn(kolonice[2]);
		
		table.setRowHeight(20);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC , 11));
		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		table.setForeground(Color.black);
		 table.setBackground(Color.lightGray);
		postaviPodatke();
		
		JLabel lblSelektovanaPotvrda = new JLabel("New label");
		lblSelektovanaPotvrda.setBounds(228, 518, 97, 14);
		contentPane.add(lblSelektovanaPotvrda);
		lblSelektovanaPotvrda.setText("Nije selektovana");
		
		JScrollPane scrollPane_1 = new JScrollPane(table1);
		scrollPane_1.setBounds(10, 302, 465, 193);
		contentPane.add(scrollPane_1);
		
		table1 = new JTable(dtm1);
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				selektovano  = table1.getSelectedRow();
				
				iddd = (int) table1.getModel().getValueAt(selektovano, 0);
				String text = String.valueOf(iddd);
				
				lblSelektovanaPotvrda.setText(text);
				
		        
			}
		});
		scrollPane_1.setViewportView(table1);
		Object[]kolone = {"ID","Datum izdavanja","Iznos potvrde", "Ime firme"};
		dtm1.addColumn(kolone[0]);
		dtm1.addColumn(kolone[1]);
		dtm1.addColumn(kolone[2]);
		dtm1.addColumn(kolone[3]);
		table1.setRowHeight(20);
		table1.getTableHeader().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC , 11));
		table1.setFont(new Font("Tahoma", Font.BOLD, 13));
		table1.setForeground(Color.black);
		table1.setBackground(Color.lightGray);
		table1.getColumnModel().getColumn(0).setPreferredWidth(60);
		table1.getColumnModel().getColumn(1).setPreferredWidth(130);
		table1.getColumnModel().getColumn(2).setPreferredWidth(110);
		table1.getColumnModel().getColumn(3).setPreferredWidth(165);
		
		JLabel lblNewLabel_1 = new JLabel("Selektovana potvrda sa brojem ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setBounds(10, 518, 219, 14);
		contentPane.add(lblNewLabel_1);
		
		
		
		
		JButton btnNewButton = new JButton("Izbri\u0161i potvrdu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Integer idBrisanje = iddd;
				int input = JOptionPane.showConfirmDialog(contentPane, "           Da li ste sigurni da želite da obrišete potvrdu ?");
				
				if(input == 0 ) {
					
					TransferKlasa transferKlasa = new TransferKlasa();
				    transferKlasa.setRequest(idBrisanje);
				    transferKlasa.setKontrolerFk(KontrolerFKKonstanta.POTVRDA);
				    transferKlasa.setKontrolerPl(KontrolerPLKonstanta.UPDATE);
				    try {
				    	KontrolerFK.getInstanca().execute(transferKlasa);
				    	JOptionPane.showMessageDialog(contentPane, "Uspešno ste izbrisali potvrdu");
				    	postaviPodatke2();
				    	
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(contentPane, "Dogodila se greška !");
					}
				    
						
					} 
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(155, 553, 140, 45);
		contentPane.add(btnNewButton);
		table1.setDefaultRenderer(Object.class, new MonCellRenderer());
		
		postaviPodatke2();
		
	}
	private void postaviPodatke() {
		TransferKlasa transferKlasa = new TransferKlasa();
		transferKlasa.setRequest(clan);
		
		transferKlasa.setKontrolerFk(KontrolerFKKonstanta.CLANSINDIKATA);
		transferKlasa.setKontrolerPl(KontrolerPLKonstanta.GET_POZAJMICA);
		KontrolerFK.getInstanca().execute(transferKlasa);
		
	List<ClanPozajmica>list = (List<ClanPozajmica>) transferKlasa.getResponse();
		for(ClanPozajmica clanPozajmica : list ) {
			Object[]red = {clanPozajmica.getDatumPozajmice(),clanPozajmica.getVrstaPozajmice(),clanPozajmica.getIznosPozajmice(),clanPozajmica.getImePrezime()};
			dtm.addRow(red);
		}
	}
   private void postaviPodatke2() {
	   
	   dtm1.setRowCount(0);
	   TransferKlasa transferKlasa = new TransferKlasa()	;
	   transferKlasa.setRequest(clan);
	   transferKlasa.setKontrolerFk(KontrolerFKKonstanta.CLANSINDIKATA);
	   transferKlasa.setKontrolerPl(KontrolerPLKonstanta.GET_POTVRDA);
	   KontrolerFK.getInstanca().execute(transferKlasa);
	   
	   List<ClanPotvrda>list = (List<ClanPotvrda>) transferKlasa.getResponse();
	   for(ClanPotvrda clanPo : list) {
		   if(clanPo.getBrojRata() == 0) {
		   Object[]red = {clanPo.getId(),clanPo.getDatumIzdavanja(), clanPo.getIznosPotvrde(), clanPo.getImeFirme() };
		   dtm1.addRow(red);
		   }
	   }
	   
   }
   
	public class MonCellRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
           

           if(isSelected) {
            	setBackground(Color.yellow);
            	
            }

            else {
                setBackground(Color.lightGray);
            }
            return this;
		 
	}
}
}
