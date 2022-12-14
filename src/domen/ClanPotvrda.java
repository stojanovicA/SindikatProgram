package domen;

import java.time.LocalDate;

public class ClanPotvrda {
	private int id;
	private int brojRata;
	private LocalDate datumIzdavanja;
	private int iznosPotvrde;
	private String ime;
	private String imeFirme;
	public ClanPotvrda(LocalDate datumIzdavanja, int iznosPotvrde, String imeFirme) {
		super();
		this.datumIzdavanja = datumIzdavanja;
		this.iznosPotvrde = iznosPotvrde;
		this.imeFirme = imeFirme;
	}
	public ClanPotvrda() {
		super();
	}
	public LocalDate getDatumIzdavanja() {
		return datumIzdavanja;
	}
	public void setDatumIzdavanja(LocalDate datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}
	public int getIznosPotvrde() {
		return iznosPotvrde;
	}
	public void setIznosPotvrde(int iznosPotvrde) {
		this.iznosPotvrde = iznosPotvrde;
	}

	public String getImeFirme() {
		return imeFirme;
	}
	public void setImeFirme(String imeFirme) {
		this.imeFirme = imeFirme;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public int getBrojRata() {
		return brojRata;
	}
	public void setBrojRata(int brojRata) {
		this.brojRata = brojRata;
	}
	
	
	
	
	
	
}