package app.install;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


public class AppInstallAllBestellungTable {
	
Connection dbConn;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AppInstallAllBestellungTable myApplication = new AppInstallAllBestellungTable();
		myApplication.dbConn = new PostgreSQLAccess().getConnection();
		myApplication.doSomething();
	}
	
	public void doSomething() throws SQLException, ClassNotFoundException{
		dropAllBestellungTable();
		createAllBestellungTable();	
	}
	
	public void dropAllBestellungTable() throws SQLException {
		String sql = "DROP TABLE IF EXISTS allBestellung";
		System.out.println(sql);
		dbConn.prepareStatement(sql).executeUpdate();
		System.out.println("Table allBestellung existiert (jetzt) nicht (mehr)");
	}
	
	public void createAllBestellungTable() throws SQLException {
		String sql = "CREATE TABLE allBestellung ("
				+ "		bestellNr		INTEGER,			"
				+ "		kundenNr		INTEGER,			"
				+ "		artikelnr		INTEGER,			"
				+ "		menge			INTEGER,			"
				+ "		datum			TIMESTAMP, 			"
				+ "		name			CHAR(64),			"
				+ "		Adresse			CHAR(64),			"
				+ "		Stadt			CHAR(64),			"
				+ "		plz				INTEGER				"	
				+ "		)";
		System.out.println(sql);
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.executeUpdate();
		System.out.println("Table allBestellung erfolgreich angelegt");
	}

}
