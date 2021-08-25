package app.install;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppInstallAllBestellungArtikelTable {
	
	Connection dbConn;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AppInstallAllBestellungArtikelTable myApplication = new AppInstallAllBestellungArtikelTable();
		myApplication.dbConn = new PostgreSQLAccess().getConnection();
		myApplication.doSomething();
	}
	
	public void doSomething() throws SQLException, ClassNotFoundException{
		dropAllBestellungArtikelTable();
		//createAllBestellungArtikelTable();	
		//insert();
	}
	
	public void dropAllBestellungArtikelTable() throws SQLException {
		String sql = "DROP TABLE IF EXISTS allBestellungArtikel";
		System.out.println(sql);
		dbConn.prepareStatement(sql).executeUpdate();
		System.out.println("Table allBestellungArtikel existiert (jetzt) nicht (mehr)");
	}
	
	public void createAllBestellungArtikelTable() throws SQLException {
		String sql = "CREATE TABLE allBestellungArtikel ("
				+ "		position		SERIAL PRIMARY KEY,"
				+ "		bestellNr		SERIAL,	"
				+ "		kundenNr		INTEGER,"
				+ "		artikelNr		INTEGER,"
				+ "		menge			INTEGER	"
				+ "		)";
		System.out.println(sql);
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.executeUpdate();
		System.out.println("Table allBestellungArtikel erfolgreich angelegt");
	}
}
