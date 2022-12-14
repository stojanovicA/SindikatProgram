package servis;

import broker.Broker;
import domen.ClanPotvrda;
import domen.ClanSindikata;
import domen.TransferKlasa;

public class ClanoviServisVratiPotvrde extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(TransferKlasa transferKlasa) {
		Broker broker = new Broker();
		ClanSindikata clan = (ClanSindikata) transferKlasa.getRequest();
		transferKlasa.setResponse(broker.selectClanoviPotvrda(clan));
		

	}

}
