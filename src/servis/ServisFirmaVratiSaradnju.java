package servis;

import broker.Broker;
import domen.Firma;
import domen.TransferKlasa;

public class ServisFirmaVratiSaradnju extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(TransferKlasa transferKlasa) {
		// TODO Auto-generated method stub
		Firma firma = (Firma) transferKlasa.getRequest();
		Broker broker = new Broker();
		broker.firmaVratiSaradnju(firma);
	}

}
