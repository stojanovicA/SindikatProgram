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
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import com.mysql.cj.jdbc.exceptions.SQLError;
import com.toedter.calendar.JDateChooser;

import controlerFK.KontrolerFK;
import domen.ClanSindikata;
import domen.Firma;
import domen.KontrolerFKKonstanta;
import domen.KontrolerPLKonstanta;
import domen.Potvrda;
import domen.TransferKlasa;
import view.KorisnikForma.MonCellRenderer;

import java.awt.Label;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLNonTransientException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IzdajPotvrduForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfImeFirme;
	private JTextField tfIznos;
	private JTextField tfPB;
	private JTextField tfPIB;
	private JTable table;
	private DefaultTableModel dtm = new DefaultTableModel();
	private JTextField textField;
	private String pB;
	private int pibFirme;
	private String imeFirme;
	
	

	
	public IzdajPotvrduForma(ClanSindikata clan)  {
		
		
		setTitle("Izdavanje potvrde");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1039, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		tfImeFirme = new JTextField();
		tfImeFirme.setBounds(99, 156, 115, 20);
		contentPane.add(tfImeFirme);
		tfImeFirme.setColumns(10);
		tfImeFirme.setEditable(false);
		
		tfIznos = new JTextField();
		tfIznos.setColumns(10);
		tfIznos.setBounds(99, 210, 115, 20);
		contentPane.add(tfIznos);
		tfIznos.setText("0");
		
		tfPB = new JTextField();
		tfPB.setEditable(false);
		tfPB.setColumns(10);
		tfPB.setBounds(99, 370, 115, 20);
		contentPane.add(tfPB);
		tfPB.setText(clan.getPb());
		
		JLabel lblasdasd = new JLabel("Ime firme");
		lblasdasd.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblasdasd.setBounds(16, 159, 73, 14);
		contentPane.add(lblasdasd);
		
		JLabel lblNewLabel = new JLabel("Iznos");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(16, 213, 46, 14);
		contentPane.add(lblNewLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(99, 265, 115, 20);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setDate(new Date());
		
		JLabel lblNewLabel_1 = new JLabel("Datum");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(16, 269, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(" PB \u010Dlana");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(16, 373, 71, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PIB firme");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(16, 423, 65, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("PB - personalni broj \u010Dlana");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(10, 560, 178, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("PIB - pib firme koja izdaje potvrdu");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(10, 579, 276, 14);
		contentPane.add(lblNewLabel_5);
		Integer pib = 123456789;
		
		
		JButton btnUpisi = new JButton("Upi\u0161i potvrdu !");
		
		
		btnUpisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)   {
				
			
					
				int input = JOptionPane.showConfirmDialog(contentPane, "Da li ste sigurni da želite da upišete potvrdu");
				
				if(input == 0 ) {
					try {
				int brojRata = 0;
				int iznos = Integer.parseInt(tfIznos.getText());
				int dan = dateChooser.getJCalendar().getDayChooser().getDay();
				int mesec = dateChooser.getJCalendar().getMonthChooser().getMonth()+1;
				int godina = dateChooser.getJCalendar().getYearChooser().getYear();
				LocalDate datumOtvaranja = LocalDate.of(godina, mesec, dan);
				String pB = tfPB.getText();
				int pibFirme = Integer.parseInt(tfPIB.getText());
				Potvrda potvrda = new Potvrda(brojRata,iznos,datumOtvaranja,pB,pibFirme);
			    TransferKlasa transferKlasa = new TransferKlasa();
			    
			    
			    transferKlasa.setRequest(potvrda);
			    transferKlasa.setKontrolerFk(KontrolerFKKonstanta.POTVRDA);
			    transferKlasa.setKontrolerPl(KontrolerPLKonstanta.POST);
			  
			    KontrolerFK.getInstanca().execute(transferKlasa);
			    JOptionPane.showMessageDialog(contentPane, "Uspešno ste upisali potvrdu");
			    dispose();
			    }catch (Exception s) {
					
					JOptionPane.showMessageDialog(null, "Dogodila se greska, proverite uneta polja");
			    }
			    
				}  
			    
			
				
			
			
			}
		});
		btnUpisi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpisi.setBounds(30, 481, 189, 57);
		contentPane.add(btnUpisi);
		
		tfPIB = new JTextField();
		tfPIB.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tfPIB.setBounds(99, 417, 115, 20);
		contentPane.add(tfPIB);
		tfPIB.setColumns(10);
		tfPIB.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(276, 42, 737, 502);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int red = table.getSelectedRow();
		        
		        pibFirme = (int) table.getModel().getValueAt(red, 0);
		        imeFirme = (String) table.getModel().getValueAt(red, 1);
		        
		        tfPIB.setText(String.valueOf(pibFirme));
		        tfImeFirme.setText(imeFirme);
		        
		        
				
			}
		});
		scrollPane.setViewportView(table);
		
		table.setRowHeight(40);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC , 11));
		table.setFont(new Font("Tahoma", Font.BOLD, 13));
		table.setForeground(Color.black);
		 table.setBackground(Color.lightGray);
		 table.setDefaultRenderer(Object.class, new MonCellRenderer());
		
		JLabel lblImelana = new JLabel("Ime \u010Dlana");
		lblImelana.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImelana.setBounds(16, 93, 73, 14);
		contentPane.add(lblImelana);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(99, 90, 115, 20);
		contentPane.add(textField);
		textField.setText(clan.getIme());
		
		JLabel lblNewLabel_6 = new JLabel("Lista firmi");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_6.setBounds(511, 11, 159, 28);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("*");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(220, 213, 18, 14);
		contentPane.add(lblNewLabel_7);
		Object[]kolone = {"PIB","Naziv firme","br. Rata","Max iznos","Napomena"};
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		dtm.addColumn(kolone[4]);
		table.getColumnModel().getColumn(0).setPreferredWidth(87);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(310);
		
		postaviPodatkeFirme();
		
		
	}
	private void postaviPodatkeFirme() {
		dtm.setRowCount(0);
		TransferKlasa transferKlasa = new TransferKlasa();
		transferKlasa.setKontrolerFk(KontrolerFKKonstanta.FIRMA);
		transferKlasa.setKontrolerPl(KontrolerPLKonstanta.GET);
		KontrolerFK.getInstanca().execute(transferKlasa);
		List<Firma>list = (List<Firma>) transferKlasa.getResponse();
		for(Firma firma : list ) {
			if(firma.getStatusFirme()== 1){
			Object[]red = {firma.getPib(),firma.getIme(),firma.getBrojRata(),firma.getDozvoljeniIznos(),firma.getNapomena()};
			dtm.addRow(red);
		}
		}
	}
	public class MonCellRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
            int modelRow = table.convertRowIndexToModel(row);
			String type = (String)table.getModel().getValueAt(modelRow, 1);

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
