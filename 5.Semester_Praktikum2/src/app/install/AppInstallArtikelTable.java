package app.install;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppInstallArtikelTable {
	
	Connection dbConn;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AppInstallArtikelTable myApplication = new AppInstallArtikelTable();
		myApplication.dbConn = new PostgreSQLAccess().getConnection();
		myApplication.doSomething();
	}
	
	public void doSomething() throws SQLException, ClassNotFoundException{
		dropArtikelTable();
		createArtikelTable();	
		insert();
	}
	
	public void createArtikelTable() throws SQLException {
		String sql = "CREATE TABLE artikel ("
				+ "		artikelNr 		SERIAL NOT NULL PRIMARY KEY,		"
				+ "		artikel			CHAR(64)	NOT NULL UNIQUE,		"
				+ "		artikelLower	Char(64)	NOT NULL,				"
				+ "     kategorie		CHAR(64),							"
				+ "		kategorieLower	Char(64),							"
				+ "		preis      		DECIMAL(5,2)NOT NULL,         		"
				+ "     clicks     		INTEGER 	DEFAULT '0',      		"
				+ "     beschreibung 	VARCHAR,                   			"
				+ "		lager			INTEGER		DEFAULT '0'				"
				+ "	  )";
		System.out.println(sql);
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.executeUpdate();
		System.out.println("Table artikel erfolgreich angelegt");
	}
	
	public void dropArtikelTable() throws SQLException {
		String sql = "DROP TABLE IF EXISTS artikel";
		System.out.println(sql);
		dbConn.prepareStatement(sql).executeUpdate();
		System.out.println("Table artikel existiert (jetzt) nicht (mehr)");
	}
	
	public void insert() throws SQLException {
		String sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,lager) VALUES ('Kirschbaum','kirschbaum','Garten','garten',9.99,'ein Kirschbaum',11)";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Baum erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,lager) VALUES ('Lilie','lilie','Garten','garten',2.99,'eine Blume',5,99)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Lilie erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks) VALUES ('Apfel','apfel','Garten','garten',0.99,'ein Apfel',46)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Apfel erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,lager) VALUES ('Schlauch','schlauch','Garten','garten',5.99,'eine Blume',13,52)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Schlauch erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,lager) VALUES ('Schaufel','schaufel','Garten','garten',9.99,'eine Blume',3,10)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Schaufel erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,lager) VALUES ('Nintendo Switch','nintendo switch','Elektro','elektro',320.99,'eine Switch',99,13)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Switch erfolgreich hinzugefügt");
	}
	

}
