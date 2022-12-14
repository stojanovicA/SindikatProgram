package servis;

import broker.Broker;
import domen.ClanSindikata;
import domen.TransferKlasa;

public class ClanoviServisVratiClanove extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(TransferKlasa transferKlasa) {
		// TODO Auto-generated method stub
		Broker broker = new Broker();
		transferKlasa.setResponse(broker.selectAllClanovi(new ClanSindikata()));
	}

}
