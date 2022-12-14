package servis;

import broker.Broker;
import domen.Potvrda;
import domen.Pozajmica;
import domen.TransferKlasa;

public class ServisPotvrdaUpisi extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(TransferKlasa transferKlasa) {
		// TODO Auto-generated method stub
		Potvrda potvrda = (Potvrda) transferKlasa.getRequest();
		Broker broker = new Broker();
        broker.sacuvaj(potvrda);

	}

}
