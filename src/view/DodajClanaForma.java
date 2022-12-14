package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlerFK.KontrolerFK;
import domen.ClanSindikata;
import domen.KontrolerFKKonstanta;
import domen.KontrolerPLKonstanta;
import domen.TransferKlasa;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import com.toedter.calendar.JDateChooser;

public class DodajClanaForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfPB;
	private JTextField tfIme;
	private JDateChooser dateChooser;

	
	public DodajClanaForma() {
		setTitle("Dodaj novog člana");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 298, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		tfPB = new JTextField();
		tfPB.setBounds(141, 79, 120, 20);
		contentPane.add(tfPB);
		tfPB.setColumns(10);
		
		tfIme = new JTextField();
		tfIme.setBounds(141, 133, 120, 20);
		contentPane.add(tfIme);
		tfIme.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Personalni broj");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 82, 102, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ime i prezime");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 136, 102, 14);
		contentPane.add(lblNewLabel_1);
		String pib = "IGB Sindikat";
		
		JLabel lblNewLabel_3 = new JLabel(" Datum učlanjenja");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 199, 121, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Dodaj člana !");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pb = tfPB.getText();
				String ime = tfIme.getText();
				int dan = dateChooser.getJCalendar().getDayChooser().getDay();
				int mesec = dateChooser.getJCalendar().getMonthChooser().getMonth()+1;
				int godina = dateChooser.getJCalendar().getYearChooser().getYear();
				LocalDate datumOtvaranja = LocalDate.of(godina, mesec, dan);
				int pibSindikat = 123456789 ;
                int input = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da dodate člana  ?  ");
				
				
				if(pb.length() == 6 && !ime.isBlank()&& input == 0) {
				ClanSindikata clan = new ClanSindikata(pb, ime, datumOtvaranja, pibSindikat);
				TransferKlasa transferKlasa = new TransferKlasa();
			    transferKlasa.setRequest(clan);
			    transferKlasa.setKontrolerFk(KontrolerFKKonstanta.CLANSINDIKATA);
			    transferKlasa.setKontrolerPl(KontrolerPLKonstanta.POST);
			    try {
			    	KontrolerFK.getInstanca().execute(transferKlasa);
			    	JOptionPane.showMessageDialog(null, "Uspešno dodavanje člana");
			    	dispose();
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Dogodila se greška, proverite sva polja");
				}
			    
					
				} else {
					JOptionPane.showMessageDialog(null, "Personalni broj clana mora sadrzati 6 brojeva i polje ime ne sme biti prazno");
				}
				
			    
			    
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(79, 260, 127, 23);
		contentPane.add(btnNewButton);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(141, 193, 120, 20);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setDate(new Date());
	}
}
