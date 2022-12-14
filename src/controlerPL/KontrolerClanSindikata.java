package controlerPL;

import domen.KontrolerPLKonstanta;
import domen.TransferKlasa;
import servis.ClanSindikataServisSacuvaj;
import servis.ClanoviServisUpdate;
import servis.ClanoviServisVratiClanauSindikat;
import servis.ClanoviServisVratiClanove;
import servis.ClanoviServisVratiPotvrde;
import servis.ClanoviServisVratiPozajmice;
import servis.OpstaSistemskaOperacija;

public class KontrolerClanSindikata implements KontrolerCommandBase {

	@Override
	public void execute(TransferKlasa transferKlasa) {
		// TODO Auto-generated method stub
		OpstaSistemskaOperacija operacija = null;
		if(transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.POST) {
			operacija = new ClanSindikataServisSacuvaj();
		}else if(transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.GET) {
			operacija = new ClanoviServisVratiClanove();
		}else if(transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.GET_POZAJMICA) {
			operacija = new ClanoviServisVratiPozajmice();
		}else if (transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.GET_POTVRDA) {
			operacija = new ClanoviServisVratiPotvrde();
		}else if (transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.UPDATE) {
			operacija = new ClanoviServisUpdate();
		}else if (transferKlasa.getKontrolerPl() == KontrolerPLKonstanta.VRATI_CLANA) {
			operacija = new ClanoviServisVratiClanauSindikat();
		}
		operacija.izvrsiSistemskuOperaciju(transferKlasa);

	}

}

