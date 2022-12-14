package controlerFK;

import controlerPL.KontrolerClanSindikata;
import controlerPL.KontrolerCommandBase;
import controlerPL.KontrolerPLFirma;
import controlerPL.KontrolerPLPotvrda;
import controlerPL.KontrolerPLPozajmica;
import controlerPL.KontrolerPLUser;
import domen.TransferKlasa;

public class KontrolerFK {
	
	private static KontrolerFK kontrolerFk;
	private KontrolerFK() {
		
	}
	
	public static KontrolerFK getInstanca() {
		if(kontrolerFk == null) {
			kontrolerFk = new KontrolerFK();
		}
		return kontrolerFk;
	}
	
	public void execute(TransferKlasa transferKlasa) {
		KontrolerCommandBase commandBase = null;
		switch(transferKlasa.getKontrolerFk()) {
		case USER:
			commandBase = new KontrolerPLUser();
			break;
	    
		case CLANSINDIKATA:
			commandBase = new KontrolerClanSindikata();
			break;
			
		case FIRMA:
			commandBase = new KontrolerPLFirma();
			break;
			
		case POZAJMICA:
			commandBase = new KontrolerPLPozajmica();
			break;
			
		case POTVRDA:
			commandBase = new KontrolerPLPotvrda();
			break;
	    
			
		default:
			break;
		}
		commandBase.execute(transferKlasa);
		
		
	}

}
