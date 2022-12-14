package domen;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

public class Potvrda implements OpstiDomen {
	private int brojRata;
	private int iznos;
	private LocalDate datum;
	private String pB;
	private int pIb;
	public int getBrojRata() {
		return brojRata;
	}
	public void setBrojRata(int brojRata) {
		this.brojRata = brojRata;
	}
	public int getIznos() {
		return iznos;
	}
	public void setIznos(int iznos) {
		this.iznos = iznos;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public String getpB() {
		return pB;
	}
	public void setpB(String pB) {
		this.pB = pB;
	}
	public int getpIb() {
		return pIb;
	}
	public void setpIb(int pIb) {
		this.pIb = pIb;
	}
	public Potvrda(int brojRata, int iznos, LocalDate datum, String pB, int pIb) {
		super();
		this.brojRata = brojRata;
		this.iznos = iznos;
		this.datum = datum;
		this.pB = pB;
		this.pIb = pIb;
	}
	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return " potvrde ";
	}
	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (`broj_rata`, `iznos_potvrde`, `datum_izdavanja`, `PB`, `PIB_firme`) ";
	}
	@Override
	public String vratiVrednosti() {
		// TODO Auto-generated method stub
		return " (?,?,?,?,?) " ;
	}
	@Override
	public PreparedStatement sacuvaj(PreparedStatement preparedStatement) {
		try {
			preparedStatement.setInt(1, brojRata);
			preparedStatement.setInt(2, iznos);
			preparedStatement.setDate(3, Date.valueOf(datum));
			preparedStatement.setString(4, pB);
			preparedStatement.setInt(5, pIb);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		
		return preparedStatement;
	}
	@Override
	public List<OpstiDomen> selectAll(ResultSet resultSet) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
