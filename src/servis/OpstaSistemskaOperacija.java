package servis;

import javax.swing.JOptionPane;

import domen.TransferKlasa;
import konekcija.Konekcija;

public abstract class  OpstaSistemskaOperacija {
	public void izvrsiSistemskuOperaciju(TransferKlasa transferKlasa) {
		try {
			pokreniTransakciju();
			 izvrsiKonkretnuSistemskuOperaciju(transferKlasa);
			 potvrdiTransakciju();
		}catch (Exception e) {
			ponistiTransakciju();
			JOptionPane.showMessageDialog(null, "Niste uneli dobre podatke");
		}finally {
			zatvoriKonekciju();
		}
		
		 
	}

	public void zatvoriKonekciju() {
		// TODO Auto-generated method stub
		Konekcija.getInstanca().zatvoriKonekciju();
		
	}

	public void ponistiTransakciju() {
		// TODO Auto-generated method stub
		Konekcija.getInstanca().potvrdiTransakciju();
	}

	public void potvrdiTransakciju() {
		// TODO Auto-generated method stub
		Konekcija.getInstanca().potvrdiTransakciju();
	}

	public abstract void izvrsiKonkretnuSistemskuOperaciju(TransferKlasa transferKlasa);

	private void pokreniTransakciju() {
		
		Konekcija.getInstanca().pokreniTransakciju();
		
		
		
	}

}
