package controlerPL;

import domen.KontrolerPLKonstanta;
import domen.TransferKlasa;
import servis.ClanoviServisVratiClanove;
import servis.OpstaSistemskaOperacija;
import servisuser.UserServisLogovanje;

public class KontrolerPLUser implements KontrolerCommandBase{

	@Override
	public void execute(TransferKlasa transferKlasa) {
		OpstaSistemskaOperacija operacija = null;
		if(transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.POST) {
			operacija = new UserServisLogovanje();
		}
		operacija.izvrsiSistemskuOperaciju(transferKlasa);
	}

}
  

