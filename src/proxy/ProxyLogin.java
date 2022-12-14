package proxy;

import javax.swing.JOptionPane;

import domen.User;
import view.AdminForma;
import view.KorisnikForma;
import view.LoginForma;

public class ProxyLogin implements Proxy {

	@Override
	public void login(Integer status) {
		// TODO Auto-generated method stub
		if(status == 2){
			KorisnikForma kf = new KorisnikForma();
			
			kf.setVisible(true);
			
	}else if(status == 1) {
		
		AdminForma af = new AdminForma();
		af.setVisible(true);
	}
		else {
			JOptionPane.showMessageDialog(null, "Pogresan password, pokusajte ponovo");
			LoginForma login = new LoginForma();
			login.setLocationRelativeTo(null);
			login.setVisible(true);
		}

}
}
