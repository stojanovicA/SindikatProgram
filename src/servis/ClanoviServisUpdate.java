package servis;

import broker.Broker;
import domen.ClanSindikata;
import domen.TransferKlasa;

public class ClanoviServisUpdate extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(TransferKlasa transferKlasa) {
		// TODO Auto-generated method stub
		ClanSindikata clan =  (ClanSindikata) transferKlasa.getRequest();
		Broker broker = new Broker();
		broker.UpdateClana(clan);
	}

}
