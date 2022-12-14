package domen;

import controlerFK.KontrolerFK;

public class TransferKlasa {
	private Object  request;
	private Object response;
	private String message;
	private KontrolerFKKonstanta kontrolerFk;
	private KontrolerPLKonstanta kontrolerPl;
	public Object getRequest() {
		return request;
	}
	public void setRequest(Object request) {
		this.request = request;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public KontrolerFKKonstanta getKontrolerFk() {
		return kontrolerFk;
	}
	public void setKontrolerFk(KontrolerFKKonstanta kontrolerFk) {
		this.kontrolerFk = kontrolerFk;
	}
	public KontrolerPLKonstanta getKontrolerPl() {
		return kontrolerPl;
	}
	public void setKontrolerPl(KontrolerPLKonstanta kontrolerPl) {
		this.kontrolerPl = kontrolerPl;
	}
	
	
	
}
	