package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import app.install.NoConnectionException;
import app.install.PostgreSQLAccess;

public class AllBestellungBean {
	
	
	Connection dbConn;
	
	
	public void bestellung(int kundenNr) throws SQLException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String warenkorb = "select position, artikelnr,menge from warenkorbkunde where kundennr = "+kundenNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(warenkorb).executeQuery();
		while(dbRes.next()) {
			int position = dbRes.getInt("position");
			int artikelNr = dbRes.getInt("artikelnr");
			int menge = dbRes.getInt("menge");
			
			String sql="Insert into allbestellung(kundennr,artikelnr,menge,datum) values(?,?,?,?)";
			this.dbConn = new PostgreSQLAccess().getConnection();
			PreparedStatement prep = this.dbConn.prepareStatement(sql);
			prep.setInt(1,kundenNr);
			prep.setInt(2,artikelNr);
			prep.setInt(3,menge);
			prep.setTimestamp(4, timestamp);
			prep.executeUpdate();
			System.out.println("Artikel wurde zur Bestellung hinzugefügt");
			
			sql = "DELETE FROM warenkorbkunde WHERE artikelnr="+artikelNr+"and kundennr="+kundenNr;
			prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
			prep.executeUpdate();
			System.out.println("Artikel wurde gelöscht. /wkkBean"+artikelNr+" kundennr: "+kundenNr);
		}
		
	}
	
	

}
