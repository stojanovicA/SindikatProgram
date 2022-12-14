package domen;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Firma implements OpstiDomen {
	
	private int pib;
	private String ime;
	private int brojRata;
	private int dozvoljeniIznos;
	private int statusFirme;
	private String napomena;
	
	

	public Firma(int pib) {
		super();
		this.pib = pib;
	}

	public Firma(int pib, String ime, int brojRata, int dozvoljeniIznos, int statusFirme, String napomena) {
		super();
		this.pib = pib;
		this.ime = ime;
		this.brojRata = brojRata;
		this.dozvoljeniIznos = dozvoljeniIznos;
		this.statusFirme = statusFirme;
		this.napomena = napomena;
	}
	
	public int getStatusFirme() {
		return statusFirme;
	}

	public void setStatusFirme(int statusFirme) {
		this.statusFirme = statusFirme;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public int getPib() {
		return pib;
	}
	public void setPib(int pib) {
		this.pib = pib;
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
	public int getDozvoljeniIznos() {
		return dozvoljeniIznos;
	}
	public void setDozvoljeniIznos(int dozvoljeniIznos) {
		this.dozvoljeniIznos = dozvoljeniIznos;
	}
	public Firma() {
		super();
	}
	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return " firme ";
	}
public Firma(int pib, String ime, int brojRata) {
		super();
		this.pib = pib;
		this.ime = ime;
		this.brojRata = brojRata;
	}

	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return  " (`PIB_firme`, `Ime_Firme`, `Broj_rata`, `Dozvoljeni_iznos`,`statusFirme`,`napomena`) ";
	}

	@Override
	public String vratiVrednosti() {
		// TODO Auto-generated method stub
		return " (?,?,?,?,?,?) ";
	}
	
	

	@Override
	public PreparedStatement sacuvaj(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		
		try {
			preparedStatement.setInt(1, pib);
			preparedStatement.setString(2, ime);
			preparedStatement.setInt(3, brojRata);
			preparedStatement.setInt(4, dozvoljeniIznos);
			preparedStatement.setInt(5, statusFirme);
			preparedStatement.setString(6, napomena);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return preparedStatement;
	}
	@Override
	public List<OpstiDomen> selectAll(ResultSet resultSet) {
		List<OpstiDomen>lista = new ArrayList<OpstiDomen>();
		try {
			while(resultSet.next()) {
				Firma firma = new Firma();
				firma.setPib(resultSet.getInt("PIB_firme"));
				firma.setIme(resultSet.getString("Ime_Firme"));
				firma.setBrojRata(resultSet.getInt("Broj_rata"));
				firma.setDozvoljeniIznos(resultSet.getInt("Dozvoljeni_iznos"));
				firma.setStatusFirme(resultSet.getInt("statusFirme"));
				firma.setNapomena(resultSet.getString("napomena"));
				lista.add(firma);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

}
