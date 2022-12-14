package servis;

import broker.Broker;
import domen.ClanSindikata;
import domen.TransferKlasa;

public class ClanoviServisVratiClanauSindikat extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(TransferKlasa transferKlasa) {
		// TODO Auto-generated method stub
		ClanSindikata clan =  (ClanSindikata) transferKlasa.getRequest();
		Broker broker = new Broker();
		broker.vratiClanaUSindikat(clan);
	}

}
