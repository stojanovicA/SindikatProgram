package controlerPL;

import domen.KontrolerPLKonstanta;
import domen.TransferKlasa;
import servis.OpstaSistemskaOperacija;
import servis.ServisFirmaVratiClanove;
import servis.ServisPotvrdaUpdate;
import servis.ServisPotvrdaUpisi;

public class KontrolerPLPotvrda implements KontrolerCommandBase {

	@Override
	public void execute(TransferKlasa transferKlasa) {
		OpstaSistemskaOperacija operacija = null;
		if(transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.POST) {
			
			operacija = new ServisPotvrdaUpisi();
			
		}else if(transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.UPDATE) {
			
			operacija = new ServisPotvrdaUpdate();
			
		}
		operacija.izvrsiSistemskuOperaciju(transferKlasa);

	
	}

}
