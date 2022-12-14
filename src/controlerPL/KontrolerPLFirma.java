package controlerPL;

import domen.KontrolerPLKonstanta;
import domen.TransferKlasa;
import servis.ClanoviServisVratiClanove;
import servis.OpstaSistemskaOperacija;
import servis.ServisFirmaIzmeniFirmu;
import servis.ServisFirmaPrekini;
import servis.ServisFirmaSacuvajNovuFirmu;
import servis.ServisFirmaVratiClanove;
import servis.ServisFirmaVratiSaradnju;

public class KontrolerPLFirma implements KontrolerCommandBase {

	@Override
	public void execute(TransferKlasa transferKlasa) {
		OpstaSistemskaOperacija operacija = null;
		if(transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.GET) {
			operacija = new ServisFirmaVratiClanove();
		}else if(transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.UPDATE) {
			operacija = new ServisFirmaPrekini();
		}else if(transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.UPDATE_VRATISARADNJU) {
			operacija = new ServisFirmaVratiSaradnju();
		}else if(transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.POST) {
			operacija = new ServisFirmaSacuvajNovuFirmu();
		}else if(transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.IZMENI_FIRMU) {
			operacija = new ServisFirmaIzmeniFirmu();
		}
		operacija.izvrsiSistemskuOperaciju(transferKlasa);

	

	}

}
