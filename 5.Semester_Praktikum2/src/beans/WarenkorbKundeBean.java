package beans;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import app.install.NoConnectionException;
import app.install.PostgreSQLAccess;

public class WarenkorbKundeBean {
	int kundenNr;
	int artikelNr;
	int menge;
	double einzelpreis;
	
	Connection dbConn;
	
	public void insertWarenkorb(int kundenNr,int artikelNr,int menge,double preis) throws SQLException {
		this.kundenNr=kundenNr;
		
		String sql="Select menge from warenkorbkunde where kundennr="+kundenNr+"and artikelnr="+artikelNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		if(dbRes.next()) {
			int neueMenge= menge+dbRes.getInt("menge");
			sql = "update warenkorbkunde set menge="+neueMenge+"where kundennr="+kundenNr+"and artikelnr="+artikelNr;
			PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
			prep.executeUpdate();
			System.out.println("Artikel zum Warenkorb hinzugefügt");
		}else {
		sql = "INSERT INTO warenkorbkunde (kundenNr,artikelNr,menge, einzelpreis, gesamtpreis) VALUES(?,?,?,?,?)";
		this.dbConn = new PostgreSQLAccess().getConnection();
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.setInt(1,kundenNr);
		prep.setInt(2,artikelNr);
		prep.setInt(3,menge);
		prep.setDouble(4, preis);
		prep.setDouble(5, preis*menge);
		prep.executeUpdate();
		System.out.println("Neuer Artikel zum Warenkorb hinzugefügt");
		}
	}
	
	public ArrayList<ListArtikel> getWarenkorbKunde(int kundenNr) throws NoConnectionException, SQLException {
		this.kundenNr=kundenNr;
		ArrayList<ListArtikel> coll = new ArrayList<ListArtikel>();
		String sql ="select position, artikelnr,menge, einzelpreis, gesamtpreis from warenkorbkunde where kundennr= "+kundenNr+" order by position asc";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		while(dbRes.next()) {
			int position = dbRes.getInt("position");
			int artikelNr = dbRes.getInt("artikelnr");
			int menge = dbRes.getInt("menge");
			double einzelpreis = dbRes.getDouble("einzelpreis");
			double gesamtpreis = dbRes.getDouble("gesamtpreis");
			ListArtikel a = new ListArtikel(position, artikelNr, menge, einzelpreis,gesamtpreis);
			coll.add(a);
		}
		return coll;
	}
	
	public void changeMenge(int menge, int artikelNr) throws NoConnectionException, SQLException {
		String sql = "update warenkorbkunde set menge="+menge+"where kundennr="+this.kundenNr+"and artikelnr="+artikelNr;
		PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		
		sql = "select einzelpreis from warenkorbkunde where kundennr="+this.kundenNr+" and artikelnr= "+artikelNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		double gesamtpreis= menge* dbRes.getDouble("einzelpreis");
		sql = "update warenkorbkunde set gesamtpreis="+gesamtpreis+"where kundennr="+this.kundenNr+"and artikelnr="+artikelNr;
		prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		
		System.out.println("Menge wurde geändert. /wkkBean");
		
	}
	
	public void deletArtikel(int artikelNr) throws NoConnectionException, SQLException {
		String sql = "DELETE FROM warenkorbkunde WHERE artikelnr="+artikelNr+"and kundennr="+kundenNr;
		PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel wurde gelöscht. /wkkBean"+artikelNr+" kundennr: "+kundenNr);
	}
	
	public double endpreis() throws NoConnectionException, SQLException {
		double endpreis=0;
		String sql="select gesamtpreis from warenkorbkunde where kundennr= "+kundenNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		while(dbRes.next()) {
			endpreis += dbRes.getDouble("gesamtpreis");
		}
		sql = "update komma set decimal="+endpreis+"where position=1";
		PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		
		sql = "select decimal from komma where position=1";
		dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		endpreis=dbRes.getDouble("decimal");
		return endpreis;
	}
	
	
	
}
