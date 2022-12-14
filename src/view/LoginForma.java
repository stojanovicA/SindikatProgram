package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlerFK.KontrolerFK;
import domen.KontrolerFKKonstanta;
import domen.KontrolerPLKonstanta;
import domen.TransferKlasa;
import domen.User;
import proxy.Proxy;
import proxy.ProxyLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField pfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForma frame = new LoginForma();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForma() {
		setTitle("Sindikat IGB");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(83, 73, 85, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(83, 130, 85, 14);
		contentPane.add(lblPassword);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(178, 72, 119, 20);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(178, 129, 119, 20);
		contentPane.add(pfPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = tfUsername.getText();
				String password = String.copyValueOf(pfPassword.getPassword());
				User user = new User(username, password);
				TransferKlasa transferKlasa = new TransferKlasa();
				transferKlasa.setRequest(user);
			    transferKlasa.setKontrolerFk(KontrolerFKKonstanta.USER);
			    transferKlasa.setKontrolerPl(KontrolerPLKonstanta.POST);
			    KontrolerFK.getInstanca().execute(transferKlasa);
			    Integer status = (Integer) transferKlasa.getResponse();
				Proxy proxy = new ProxyLogin();
				proxy.login(status);
			    dispose();
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(192, 187, 89, 23);
		contentPane.add(btnLogin);
		
	}
}
