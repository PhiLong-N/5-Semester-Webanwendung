package app.install;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppInstall2KommaTable {
	
Connection dbConn;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AppInstall2KommaTable myApplication = new AppInstall2KommaTable();
		myApplication.dbConn = new PostgreSQLAccess().getConnection();
		myApplication.doSomething();
	}
	
	public void doSomething() throws SQLException, ClassNotFoundException{
		drop2KommaTable();
		//create2KommaTable();
		//insert2Komma();
	}
	
	public void create2KommaTable() throws SQLException {
		String sql = "CREATE TABLE Komma(				"
				+ "		position	SERIAL PRIMARY KEY,		"
				+ " 	decimal		DECIMAL(10,2)			"
				+ "		)";
		System.out.println(sql);
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.executeUpdate();
		System.out.println("Table Komma erfolgreich angelegt");
	}
	
	public void drop2KommaTable() throws SQLException {
		String sql = "DROP TABLE IF EXISTS Komma";
		System.out.println(sql);
		dbConn.prepareStatement(sql).executeUpdate();
		System.out.println("Table Komma existiert (jetzt) nicht (mehr)");
	}
	
	public void insert2Komma() throws SQLException {
		String sql = "INSERT into komma (decimal) values (0.00)";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Dezimal erfolgreich hinzugefügt");
	}

}
