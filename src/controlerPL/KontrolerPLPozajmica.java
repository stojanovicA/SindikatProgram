package controlerPL;

import domen.KontrolerPLKonstanta;
import domen.TransferKlasa;
import servis.OpstaSistemskaOperacija;
import servis.ServisFirmaVratiClanove;
import servis.ServisPozajmicaUpisi;

public class KontrolerPLPozajmica implements KontrolerCommandBase {

	@Override
	public void execute(TransferKlasa transferKlasa) {
		OpstaSistemskaOperacija operacija = null;
		if(transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.POST) {
			operacija = new ServisPozajmicaUpisi();
		}
		operacija.izvrsiSistemskuOperaciju(transferKlasa);
		
	}

}
