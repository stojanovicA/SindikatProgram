package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;


import com.toedter.calendar.JDateChooser;

import controlerFK.KontrolerFK;
import domen.ClanSindikata;
import domen.KontrolerFKKonstanta;
import domen.KontrolerPLKonstanta;
import domen.Pozajmica;
import domen.TransferKlasa;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public  class UpisiPozajmicuForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfIznos;
	private JTextField tfPB;
	private JDateChooser dateChooser;
	private JTextField tfImeClana;
	private ClanSindikata clan;
	

	
	public UpisiPozajmicuForma(ClanSindikata clan) {
		
		this.clan = clan;
		this.setTitle("Izdavanje novčane naknade");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 427, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		tfIznos = new JTextField();
		tfIznos.setBounds(175, 136, 150, 20);
		contentPane.add(tfIznos);
		tfIznos.setColumns(10);
		
		tfPB = new JTextField();
		tfPB.setBounds(175, 301, 150, 20);
		contentPane.add(tfPB);
		tfPB.setColumns(10);
		tfPB.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("Nov\u010Dana naknada");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(42, 70, 111, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblIznos = new JLabel("Iznos");
		lblIznos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIznos.setBounds(42, 139, 101, 14);
		contentPane.add(lblIznos);
		
		JLabel lblNewLabel_1_1 = new JLabel("Datum izdavanja");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(42, 207, 101, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("PB clana");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(42, 304, 101, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(175, 66, 150, 22);
		contentPane.add(comboBox);
		comboBox.addItem("Bespovratna pomoć");
		comboBox.addItem("Smrtni slučaj");
		comboBox.addItem("Pozajmica");
		comboBox.setSelectedIndex(-1);
		
		tfImeClana = new JTextField();
		tfImeClana.setColumns(10);
		tfImeClana.setBounds(175, 343, 150, 20);
		contentPane.add(tfImeClana);
		tfPB.setText(clan.getPb());
		tfImeClana.setText(clan.getIme());
		tfImeClana.setEditable(false);
		
		JButton btnNewButton = new JButton("Upi\u0161i !");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int input = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da želite da upišite ?  ");
				
				if(input == 0 ) {
				
				try {
				String vrsta = (String) comboBox.getSelectedItem();
				int iznos = Integer.parseInt(tfIznos.getText());
				int dan = dateChooser.getJCalendar().getDayChooser().getDay();
				int mesec = dateChooser.getJCalendar().getMonthChooser().getMonth()+1;
				int godina = dateChooser.getJCalendar().getYearChooser().getYear();
				LocalDate datum = LocalDate.of(godina, mesec, dan);
				String pb = tfPB.getText();
			
				Pozajmica pozajmica = new Pozajmica(vrsta,iznos,datum,pb);
				TransferKlasa transferKlasa = new TransferKlasa();
				transferKlasa.setRequest(pozajmica);
				transferKlasa.setKontrolerFk(KontrolerFKKonstanta.POZAJMICA);
				transferKlasa.setKontrolerPl(KontrolerPLKonstanta.POST);
				KontrolerFK.getInstanca().execute(transferKlasa);
				JOptionPane.showMessageDialog(null, "Uspešno ste upisali ! ");
				dispose();
				
				
					
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(dateChooser, "Dogodila se greska, proverite da li su dobro upisana sva polja");
				}
				}
			   
				
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(120, 416, 181, 71);
		contentPane.add(btnNewButton);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(175, 201, 150, 20);
		contentPane.add(dateChooser);
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setDate(new Date());
		
	
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Ime \u010Dlana");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_1.setBounds(42, 346, 101, 14);
		contentPane.add(lblNewLabel_1_2_1);
		
		
	}
}
