package servis;

import broker.Broker;
import domen.TransferKlasa;

public class ServisPotvrdaUpdate extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(TransferKlasa transferKlasa) {
		int id = (int) transferKlasa.getRequest();
		Broker b = new Broker();
		b.updatePotvrda(id);

	}

}
