package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AdminForma extends JFrame {

	private JPanel contentPane;


	
	public AdminForma() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Glavni program");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KorisnikForma kf = new KorisnikForma();
				kf.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(63, 60, 290, 65);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Lista firmi");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaFirmiForma lf = new ListaFirmiForma();
				lf.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(63, 163, 290, 65);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Vrati \u010Dlana");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminVratiClana avd = new AdminVratiClana();
				avd.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(135, 304, 132, 38);
		contentPane.add(btnNewButton_2);
	}
}
