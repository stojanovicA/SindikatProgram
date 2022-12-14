package servis;

import broker.Broker;
import domen.ClanSindikata;
import domen.TransferKlasa;

public class ClanoviServisVratiPozajmice extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(TransferKlasa transferKlasa) {
		Broker broker = new Broker();
		ClanSindikata clan = (ClanSindikata) transferKlasa.getRequest();
		transferKlasa.setResponse(broker.selectClanoviPozajmica(clan));
	}

}
 



//SELECT * FROM pozajmice INNER JOIN clanovi ON pozajmice.PB = clanovi.PB WHERE clanovi.pb = 3 ORDER BY datum_pozajmice ASC