package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.tomcat.jni.Global;

import app.install.NoConnectionException;
import app.install.PostgreSQLAccess;

public class AllBestellungBean {
	
	int kundennr;
	
	Connection dbConn;
	
	
	
	public void bestellung(int kundenNr, String name, String adresse, String stadt, int plz) throws SQLException {
		int bestellnr=1;
		String sql="select bestellnr from allbestellung order by bestellnr desc";
		ResultSet dbRes3 = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		try {
			dbRes3.next();
			int letzteBestellnr = dbRes3.getInt("bestellnr");
			bestellnr= letzteBestellnr+1;
		}catch (Exception e) {
		}
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String warenkorb = "select position, artikelnr,menge from warenkorbkunde where kundennr = "+kundenNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(warenkorb).executeQuery();
		while(dbRes.next()) {
			int position = dbRes.getInt("position");
			int artikelNr = dbRes.getInt("artikelnr");
			int menge = dbRes.getInt("menge");
			
			sql="Insert into allbestellung(bestellnr,kundennr,artikelnr,menge,datum,name,adresse,stadt,plz) values(?,?,?,?,?,?,?,?,?)";

//			sql="Insert into allbestellung(bestellnr,kundennr,artikelnr,menge,datum) values(?,?,?,?,?)";
			this.dbConn = new PostgreSQLAccess().getConnection();
			PreparedStatement prep = this.dbConn.prepareStatement(sql);
			prep.setInt(1, bestellnr);
			prep.setInt(2,kundenNr);
			prep.setInt(3,artikelNr);
			prep.setInt(4,menge);
			prep.setTimestamp(5, timestamp);
			prep.setString(6, name);
			prep.setString(7, adresse);
			prep.setString(8, stadt);
			prep.setInt(9, plz);
			prep.executeUpdate();
			System.out.println("Artikel wurde zur Bestellung hinzugefügt");
			
			sql = "DELETE FROM warenkorbkunde WHERE artikelnr="+artikelNr+"and kundennr="+kundenNr;
			prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
			prep.executeUpdate();
			System.out.println("Artikel wurde gelöscht vom warenkorb. /wkkBean"+artikelNr+" kundennr: "+kundenNr);
			
			sql = "select lager from artikel where artikelnr="+artikelNr;
			ResultSet dbRes2 = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
			int lagervorrat=0;
			while(dbRes2.next()) {
				lagervorrat= dbRes2.getInt("lager");
			}
			int lagerneu = lagervorrat - menge;
			sql = "update artikel set lager="+lagerneu+" where artikelnr="+artikelNr;
			prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
			prep.executeUpdate();
			System.out.println("Lagerbestand wurde reduziert.");
		}
	}
	
	public Timestamp getBestellung(int kundennr) throws NoConnectionException, SQLException {
		this.kundennr=kundennr;
		String sql="select datum from allbestellung where kundennr="+kundennr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		Timestamp time = dbRes.getTimestamp("datum");
		return time;
	}
	
	public double getSumme(int bestellnr ) throws NoConnectionException, SQLException {
		double summe=0;
		String sql ="select artikelnr,menge from allbestellung where kundennr="+kundennr+" and bestellnr="+bestellnr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		while(dbRes.next()) {
			int artikelnr= dbRes.getInt("artikelnr");
			int menge = dbRes.getInt("menge");
			sql = "select preis from artikel where artikelnr="+artikelnr;
			ResultSet dbRes2 = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
			dbRes2.next();
			double preis = dbRes2.getDouble("preis");
			summe += menge*preis;
		}
		return summe;
	}
	
	public String getVersandadresse(int bestellnummer) throws NoConnectionException, SQLException {
		String sql = "select name,adresse,stadt,plz from allbestellung where bestellnr="+bestellnummer;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		String name=dbRes.getString("name");
		String adresse = dbRes.getString("adresse");
		String stadt = dbRes.getString("stadt");
		int plz = dbRes.getInt("plz");
		
		
		String html="";
		return html;
	}
	
	
	
	

}
