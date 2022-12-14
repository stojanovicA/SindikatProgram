package broker;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.*;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

import domen.ClanPotvrda;
import domen.ClanPozajmica;
import domen.ClanSindikata;
import domen.Firma;
import domen.OpstiDomen;
import domen.User;
import konekcija.Konekcija;
import servis.ClanSindikataServisSacuvaj;

public class Broker {

	public Integer login(User user) {
		String sql = "select * from users where username = ? and password = ?";
		Integer status = -1;
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        while(resultSet.next()) {
	        	status = resultSet.getInt("status");
	        }
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	public void sacuvaj(OpstiDomen opstiDomen) {
		String sql = "INSERT INTO" + opstiDomen.vratiNazivTabele() + opstiDomen.vratiNazivKolona() + "VALUES" + opstiDomen.vratiVrednosti();
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			opstiDomen.sacuvaj(preparedStatement);
			preparedStatement.execute();
		
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nije dobar PB clana, proveriti i ostala polja da li su dobro uneta");
			e.printStackTrace();
		}
		
		
		
		
		
	}
	public void sacuvajFirmu(OpstiDomen opstiDomen) {
		String sql = "INSERT INTO" + opstiDomen.vratiNazivTabele() + opstiDomen.vratiNazivKolona() + "VALUES" + opstiDomen.vratiVrednosti();
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			opstiDomen.sacuvaj(preparedStatement);
			preparedStatement.execute();
		
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Dogodila se greska. PIB vec postoji, proverite da li firma vec postoji ili promenite PIB");
			e.printStackTrace();
		}
	}

	public List<OpstiDomen> selectAllClanovi(OpstiDomen opstiDomen) {
		
		String sql = "select * from" + opstiDomen.vratiNazivTabele()+"order by Ime_Prezime";
		ResultSet resultSet = null;
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return opstiDomen.selectAll(resultSet);
		
	}
public List<OpstiDomen> selectAllFirme(OpstiDomen opstiDomen) {
		
		String sql = "select * from" + opstiDomen.vratiNazivTabele()+"order by Ime_Firme";
		ResultSet resultSet = null;
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return opstiDomen.selectAll(resultSet);
		
	}

	public List<ClanPozajmica> selectClanoviPozajmica(ClanSindikata clan) {
		String s = " " + clan.getPb()+ " ";
		String sql = " SELECT * FROM clanovi INNER JOIN pozajmice ON clanovi.PB = pozajmice.PB WHERE clanovi.PB = "+s+" ORDER by datum_pozajmice DESC " ;
		ResultSet resultSet = null;
		List<ClanPozajmica>list = new ArrayList<ClanPozajmica>();
		
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ClanPozajmica clanP = new ClanPozajmica();
				clanP.setDatumPozajmice(resultSet.getDate("datum_pozajmice").toLocalDate());
				clanP.setVrstaPozajmice(resultSet.getString("vrsta_pozajmice"));
				clanP.setIznosPozajmice(resultSet.getInt("iznos_pozajmice"));
				clanP.setImePrezime(resultSet.getString("Ime_Prezime"));
				list.add(clanP);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
		
	}

	public List<ClanPotvrda> selectClanoviPotvrda(ClanSindikata clan) {
		String s = clan.getPb();
		String sql =" SELECT * FROM potvrde INNER JOIN firme on potvrde.PIB_firme = firme.PIB_firme WHERE PB = "+clan.getPb()+" ORDER BY potvrde.datum_izdavanja DESC ";
		
		//SELECT * FROM potvrde INNER JOIN firme on potvrde.PIB_firme = firme.PIB_firme WHERE PB = 000001 ORDER BY potvrde.datum_izdavanja ASC  ZAMENITI SA OVIM !!!
		ResultSet resultSet = null;
		List<ClanPotvrda>list = new ArrayList<ClanPotvrda>();

		PreparedStatement preparedStatement;
		try {
			preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				ClanPotvrda clanPo = new ClanPotvrda()	;
				clanPo.setId(resultSet.getInt("id_potvrde"));
				clanPo.setDatumIzdavanja(resultSet.getDate("datum_izdavanja").toLocalDate());
				clanPo.setIznosPotvrde(resultSet.getInt("iznos_potvrde"));
				clanPo.setImeFirme(resultSet.getString("Ime_Firme"));
				clanPo.setBrojRata(resultSet.getInt("broj_rata"));
				list.add(clanPo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			
			e.printStackTrace();
		}
		return list;
	}

	public void UpdateClana(ClanSindikata clan) {
		// TODO Auto-generated method stub
		String sql = "UPDATE `clanovi` SET `aktivanClan`='NE' WHERE clanovi.PB = '"+clan.getPb()+"'";
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			
			preparedStatement.execute();
		
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nije dobar PB clana, proveriti i ostala polja da li su dobro uneta");
			e.printStackTrace();
		}
	}

	public void prekiniSaradnju(Firma firma) {
		// TODO Auto-generated method stub
		//UPDATE `firme` SET `PIB_firme`=[value-1],`Ime_Firme`=[value-2],`Broj_rata`=[value-3],`Dozvoljeni_iznos`=[value-4],`statusFirme`=[value-5],`napomena`=[value-6] WHERE 1
		String sql = "UPDATE `firme` SET `statusFirme`= 0 WHERE PIB_firme = '"+firma.getPib()+"'";
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			
			preparedStatement.execute();
		
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Dogodila se greska");
			e.printStackTrace();
		}
	}

	public void firmaVratiSaradnju(Firma firma) {
		
		
		String sql = "UPDATE `firme` SET `statusFirme`= 1 WHERE PIB_firme = '"+firma.getPib()+"'";
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			
			preparedStatement.execute();
		
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Dogodila se greska");
			e.printStackTrace();
		}
		
	}

	public void izmeniFirmu(Firma firma) {
		// TODO Auto-generated method stub
		String sql = "UPDATE `firme` SET `Ime_Firme`= ?,`Broj_rata`= ?,`Dozvoljeni_iznos`= ?,`statusFirme`= 1,`napomena`= ? WHERE PIB_firme = '"+firma.getPib()+"'";
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			
			preparedStatement.setString(1, firma.getIme());
			preparedStatement.setInt(2, firma.getBrojRata());
			preparedStatement.setInt(3, firma.getDozvoljeniIznos());
			preparedStatement.setString(4, firma.getNapomena());
			
			preparedStatement.execute();
		
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Dogodila se greska");
			e.printStackTrace();
		}
	}

	public void vratiClanaUSindikat(ClanSindikata clan) {
		// TODO Auto-generated method stub
		String sql = "UPDATE `clanovi` SET `aktivanClan`='DA' WHERE clanovi.PB = '"+clan.getPb()+"'";
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			
			preparedStatement.execute();
		
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nije dobar PB clana, proveriti i ostala polja da li su dobro uneta");
			e.printStackTrace();
		}
		
	}

	public void updatePotvrda(int id) {
		String sql = "UPDATE `potvrde` SET `broj_rata`= 1 WHERE id_potvrde = '"+id+"'";
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			
			preparedStatement.execute();
		
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Dogodila se greska");
			e.printStackTrace();
		}
		
	}

	



}
