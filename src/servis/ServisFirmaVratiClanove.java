package servis;

import broker.Broker;
import domen.Firma;
import domen.TransferKlasa;

public class ServisFirmaVratiClanove extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(TransferKlasa transferKlasa) {
		Broker broker = new Broker();
		transferKlasa.setResponse(broker.selectAllFirme(new Firma()));
		

	}

}
