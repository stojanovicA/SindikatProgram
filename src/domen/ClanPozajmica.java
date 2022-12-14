package domen;

import java.time.LocalDate;

public class ClanPozajmica {
	private LocalDate datumPozajmice;
	private String vrstaPozajmice;
	private int iznosPozajmice;
	private String imePrezime;
	public ClanPozajmica(LocalDate datumPozajmice, String vrstaPozajmice, int iznosPozajmice, String imePrezime) {
		super();
		this.datumPozajmice = datumPozajmice;
		this.vrstaPozajmice = vrstaPozajmice;
		this.iznosPozajmice = iznosPozajmice;
		this.imePrezime = imePrezime;
	}
	public LocalDate getDatumPozajmice() {
		return datumPozajmice;
	}
	public void setDatumPozajmice(LocalDate datumPozajmice) {
		this.datumPozajmice = datumPozajmice;
	}
	public String getVrstaPozajmice() {
		return vrstaPozajmice;
	}
	public void setVrstaPozajmice(String vrstaPozajmice) {
		this.vrstaPozajmice = vrstaPozajmice;
	}
	public int getIznosPozajmice() {
		return iznosPozajmice;
	}
	public void setIznosPozajmice(int iznosPozajmice) {
		this.iznosPozajmice = iznosPozajmice;
	}
	public String getImePrezime() {
		return imePrezime;
	}
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	public ClanPozajmica() {
		super();
	}
	
	
	
	

}
