package app.install;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppInstallWarenkorbKundeTable {
	
	Connection dbConn;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AppInstallWarenkorbKundeTable myApplication = new AppInstallWarenkorbKundeTable();
		myApplication.dbConn = new PostgreSQLAccess().getConnection();
		myApplication.doSomething();
	}
	
	public void doSomething() throws SQLException, ClassNotFoundException{
		dropWarenkorbKundeTable();
		createWarenkorbKundeTable();
	}
	
	public void createWarenkorbKundeTable() throws SQLException {
		String sql = "CREATE TABLE warenkorbKunde (							"
				+ "		position		SERIAL PRIMARY KEY,					"
				+ "		kundenNr 		INTEGER,							"
				+ "		artikelNr		INTEGER,							"
				+ "		menge			INTEGER,							"
				+ "		einzelpreis		DECIMAL(10,2),						"	
				+ "		gesamtpreis		DECIMAL(10,2)						"
				+ "		)";
		System.out.println(sql);
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.executeUpdate();
		System.out.println("Table warenkorbKunde erfolgreich angelegt");
	}
	
	public void dropWarenkorbKundeTable() throws SQLException {
		String sql = "DROP TABLE IF EXISTS warenkorbKunde";
		System.out.println(sql);
		dbConn.prepareStatement(sql).executeUpdate();
		System.out.println("Table warenkorbKunde existiert (jetzt) nicht (mehr)");
	}

}
