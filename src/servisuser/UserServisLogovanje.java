package servisuser;

import broker.Broker;
import domen.TransferKlasa;
import domen.User;
import servis.OpstaSistemskaOperacija;

public class UserServisLogovanje extends OpstaSistemskaOperacija {

	@Override
	public void izvrsiKonkretnuSistemskuOperaciju(TransferKlasa transferKlasa) {
		// TODO Auto-generated method stub
		User user = (User) transferKlasa.getRequest();
		Broker broker = new Broker();
		Integer status = broker.login(user);
		transferKlasa.setResponse(status);
		
	}

}
