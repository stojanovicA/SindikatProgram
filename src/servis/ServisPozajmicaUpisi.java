package servis;

import broker.Broker;
import domen.Pozajmica;
import domen.TransferKlasa;

public class ServisPozajmicaUpisi extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(TransferKlasa transferKlasa) {
		// TODO Auto-generated method stub
		Pozajmica pozajmica = (Pozajmica) transferKlasa.getRequest();
				Broker broker = new Broker();
		broker.sacuvaj(pozajmica);

	}

}
