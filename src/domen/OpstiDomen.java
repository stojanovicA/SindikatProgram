package domen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public interface OpstiDomen {
	String vratiNazivTabele();
	String vratiNazivKolona();
	String vratiVrednosti();
	public PreparedStatement sacuvaj(PreparedStatement preparedStatement);
	List<OpstiDomen>selectAll(ResultSet resultSet);

}
