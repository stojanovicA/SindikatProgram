package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlerFK.KontrolerFK;
import domen.Firma;
import domen.KontrolerFKKonstanta;
import domen.KontrolerPLKonstanta;
import domen.TransferKlasa;

public class IzmeniFirmu extends JFrame {

	private JPanel contentPane;
	private JTextField tfPIB;
	private JTextField tfIme;
	private JTextField tfRate;
	private JTextField tfIznos;
	private JTextField tfNapomena;

	
	public IzmeniFirmu(Firma firma) {
		setTitle("Izmeni podatke o firmi");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Izmeni !");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int pib = Integer.parseInt(tfPIB.getText());
				String ime = tfIme.getText();
				int rate = Integer.parseInt(tfRate.getText());
				int iznos = Integer.parseInt(tfIznos.getText());
				String napomena = tfNapomena.getText();
                int input = JOptionPane.showConfirmDialog(tfRate, "Da li ste sigurni da želite da izmenite firmu  ?  ");
				
				
				if(String.valueOf(pib).length() == 9 && !ime.isBlank()&& input == 0) {
				Firma firma = new Firma(pib, ime, rate, iznos,1,napomena);
				TransferKlasa transferKlasa = new TransferKlasa();
			    transferKlasa.setRequest(firma);
			    transferKlasa.setKontrolerFk(KontrolerFKKonstanta.FIRMA);
			    transferKlasa.setKontrolerPl(KontrolerPLKonstanta.IZMENI_FIRMU);
			    try {
			    	KontrolerFK.getInstanca().execute(transferKlasa);
			    	JOptionPane.showMessageDialog(tfRate, "Uspešna izmena firme");
			    	dispose();
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(tfRate, "Dogodila se greška, proverite sva polja");
				}
			    
					
				}else if (input == 1) {
					dispose();
					
				}else if (input == 2) {
					dispose();
					
				}
				
				
				else {
					JOptionPane.showMessageDialog(tfRate, "Polje PIB mora sadrzati 9 brojeva i polje ime ne sme biti prazno");
				}
				
			}
		});
		btnNewButton.setBounds(141, 401, 144, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("PIB");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(55, 66, 70, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblImeFirme = new JLabel("Ime firme");
		lblImeFirme.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImeFirme.setBounds(55, 135, 86, 14);
		contentPane.add(lblImeFirme);
		
		JLabel lblBrRata = new JLabel("Br. rata");
		lblBrRata.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBrRata.setBounds(55, 201, 86, 14);
		contentPane.add(lblBrRata);
		
		JLabel lblDozvoljeniIznos = new JLabel("Max iznos");
		lblDozvoljeniIznos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDozvoljeniIznos.setBounds(55, 271, 86, 14);
		contentPane.add(lblDozvoljeniIznos);
		
		JLabel lblNapomena = new JLabel("Napomena");
		lblNapomena.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNapomena.setBounds(10, 342, 76, 14);
		contentPane.add(lblNapomena);
		
		tfPIB = new JTextField();
		tfPIB.setBounds(172, 63, 151, 20);
		contentPane.add(tfPIB);
		tfPIB.setColumns(10);
		tfPIB.setText(String.valueOf(firma.getPib()));
		tfPIB.setEditable(false);
		tfIme = new JTextField();
		tfIme.setColumns(10);
		tfIme.setBounds(172, 132, 151, 20);
		contentPane.add(tfIme);
		tfIme.setText(firma.getIme());
		
		tfRate = new JTextField();
		tfRate.setColumns(10);
		tfRate.setBounds(172, 201, 151, 20);
		contentPane.add(tfRate);
		tfRate.setText(String.valueOf(firma.getBrojRata()));
		tfIznos = new JTextField();
		tfIznos.setColumns(10);
		tfIznos.setBounds(172, 268, 151, 20);
		contentPane.add(tfIznos);
		tfIznos.setText(String.valueOf(firma.getDozvoljeniIznos()));
		tfNapomena = new JTextField();
		tfNapomena.setColumns(10);
		tfNapomena.setBounds(89, 339, 335, 20);
		contentPane.add(tfNapomena);
		tfNapomena.setText(firma.getNapomena());
		
		JLabel lblNewLabel_1 = new JLabel("*9 brojeva");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblNewLabel_1.setBounds(325, 66, 99, 14);
		contentPane.add(lblNewLabel_1);
	}
}

