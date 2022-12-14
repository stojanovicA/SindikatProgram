package domen;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Pozajmica implements OpstiDomen {
	
	private String vrsta;
	private int iznos;
	private LocalDate datum;
	private String pb;
	
	
	public String getVrsta() {
		return vrsta;
	}
	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
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
	public String getPb() {
		return pb;
	}
	public void setPb(String pb) {
		this.pb = pb;
	}
	
	public Pozajmica() {
		super();
	}
	public Pozajmica(String vrsta, int iznos, LocalDate datum, String pb) {
		super();
		this.vrsta = vrsta;
		this.iznos = iznos;
		this.datum = datum;
		this.pb = pb;
	}
	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return " pozajmice ";
	}
	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (`vrsta_pozajmice`, `iznos_pozajmice`, `datum_pozajmice`, `PB`) ";
	}
	@Override
	public String vratiVrednosti() {
		// TODO Auto-generated method stub
		return " (?,?,?,?)  " ;
	}
	@Override
	public PreparedStatement sacuvaj(PreparedStatement preparedStatement) {
		try {
			preparedStatement.setString(1, vrsta);
			preparedStatement.setInt(2, iznos);
			preparedStatement.setDate(3, Date.valueOf(datum));
			preparedStatement.setString(4, pb);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return preparedStatement;
	}
	@Override
	public List<OpstiDomen> selectAll(ResultSet resultSet) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
