package domen;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClanSindikata implements OpstiDomen {
	private String pb;
	private String ime;
	private LocalDate datumOtvaranja;
	private int pibSindikat;
	private String aktivan;
	
	
	
	public String getAktivan() {
		return aktivan;
	}

	public void setAktivan(String aktivan) {
		this.aktivan = aktivan;
	}

	public ClanSindikata(String pb, String ime, LocalDate datumOtvaranja, int pibSindikat, String aktivan) {
		super();
		this.pb = pb;
		this.ime = ime;
		this.datumOtvaranja = datumOtvaranja;
		this.pibSindikat = pibSindikat;
		this.aktivan = aktivan;
	}

	public ClanSindikata(String pb, String ime, LocalDate datumOtvaranja, int pibSindikat) {
		super();
		this.pb = pb;
		this.ime = ime;
		this.datumOtvaranja = datumOtvaranja;
		this.pibSindikat = pibSindikat;
	}
	
	public ClanSindikata() {
		super();
	}
	

	public ClanSindikata(String pb) {
		super();
		this.pb = pb;
	}

	public ClanSindikata(String pB2, String ime2) {
		this.pb = pB2;
		this.ime = ime2;
	}

	public String getPb() {
		return pb;
	}
	public void setPb(String pb) {
		this.pb = pb;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public LocalDate getDatumOtvaranja() {
		return datumOtvaranja;
	}
	public void setDatumOtvaranja(LocalDate datumOtvaranja) {
		this.datumOtvaranja = datumOtvaranja;
	}
	public int getPibSindikat() {
		return pibSindikat;
	}
	public void setPibSindikat(int pibSindikat) {
		this.pibSindikat = pibSindikat;
	}
	@Override
	public String vratiNazivTabele() {
		// TODO Auto-generated method stub
		return " clanovi ";
	}
	@Override
	public String vratiNazivKolona() {
		// TODO Auto-generated method stub
		return " (`PB`, `Ime_Prezime`, `Datum_Clanstva`, `PIB_sindikata`, `aktivanClan`) ";
	}
	@Override
	public String vratiVrednosti() {
		// TODO Auto-generated method stub
		return " (?,?,?,?,?)  ";
	}
	@Override
	public PreparedStatement sacuvaj(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		
		try {
			preparedStatement.setString(1, pb);
			preparedStatement.setString(2, ime);
			preparedStatement.setDate(3, Date.valueOf(datumOtvaranja));
			preparedStatement.setInt(4, 123456789);
			preparedStatement.setString(5, "DA");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return preparedStatement;
	}

	@Override
	public List<OpstiDomen> selectAll(ResultSet resultSet) {
		List<OpstiDomen>list = new ArrayList<OpstiDomen>();
		try {
			while(resultSet.next()) {
				ClanSindikata clan = new ClanSindikata();
				clan.setPb(resultSet.getString("PB"));
				clan.setIme(resultSet.getString("Ime_Prezime"));
				clan.setDatumOtvaranja(resultSet.getDate("Datum_Clanstva").toLocalDate());
				clan.setPibSindikat(resultSet.getInt("PIB_sindikata"));
				clan.setAktivan(resultSet.getString("aktivanClan"));
				list.add(clan);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
		
	
	
	
}
	