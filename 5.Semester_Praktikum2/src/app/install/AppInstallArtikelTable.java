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
		//dropArtikelTable();
		//createArtikelTable();	
		//insert();
		insert1();
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
				+ "		lager			INTEGER		DEFAULT '0',			"
				+ "		bewertungsum	DECIMAL		DEFAULT '0',			"
				+ "		bewertunganzahl DECIMAL		DEFAULT	'1'				"
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
		String sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,lager,bewertungsum,bewertunganzahl) VALUES ('Kirschbaum','kirschbaum','Garten','garten',9.99,'ein Kirschbaum',11,227,99)";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Baum erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,lager,bewertungsum,bewertunganzahl) VALUES ('Lilie','lilie','Garten','garten',2.99,'eine Blume',5,99,55,15)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Lilie erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,bewertungsum,bewertunganzahl) VALUES ('Apfel','apfel','Garten','garten',0.99,'ein Apfel',46,10,8)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Apfel erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,lager) VALUES ('Schlauch','schlauch','Garten','garten',5.99,'eine Blume',13,52)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Schlauch erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,lager,bewertungsum,bewertunganzahl) VALUES ('Schaufel','schaufel','Garten','garten',9.99,'eine Blume',3,10,54,15)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Schaufel erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,lager,bewertungsum,bewertunganzahl) VALUES ('Nintendo Switch','nintendo switch','Elektro','elektro',320.99,'eine Switch',99,13,90,20)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Switch erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,lager,bewertungsum,bewertunganzahl) VALUES ('Turnschuh','turnschuh','Bekleidung','bekleidung',99,'ein Nikeschuh',11,227,99)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Turnschuh erfolgreich hinzugefügt");
	}
	
	
	public void insert1() throws SQLException {
/*
		String sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('Digitalkamera','digitalkamera','Elektronik','elektronik', 189.99,15,'Kamera',500,243,85)";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		
		String sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('Nvidia GTX 3060','nvidia gtx 3060','Elektronik', 'elektronik' ,800.00,5000,'Grafikkarte' ,20 ,1500 , 1200 )";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		
		String sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES('Ray Ban Sonnenbrille','ray ban sonnenbrille', 'Accessoire', 'accessoire', 350.49, 55 , 'eine sonnenBrille', 3,830,197 )";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('Duden', 'duden', 'Bücher', 'bücher', 30.00, 37, 'Wörterbuch', 124, 1048, 952)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		*/
		
		
		String sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('UNO', 'uno', 'Spielzeug', 'spielzeug', 15.00, 98, 'Kartenspiel', 56, 865, 647)";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ( 'Super Mario Puppe' ,'super mario puppe' ,'Spielzeug'  ,'spielzeug',100.00,1000 ,'Plüschspielzeug',100,800,500)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('Kaffeevollautomat', 'kaffeevollautomat', 'Haushalt', 'haushalt', 599.00,78, 'Kaffeemaschiene', 69, 43, 16)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('Wasserkocher', 'wasserkocher', 'Haushalt', 'haushalt', 32.99, 123, 'Kochen', 431, 45, 21) ";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('Campingset' ,'campingset','Freizeit' ,'freizeit',40.00, 1000,'Campingset' ,20  ,400  ,250  )";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('3 Kg Hantel', '3 kg hantel', 'Sport', 'sport', 15.15, 8, 'Sporthantel die 3kg wiegt', 15, 73,22)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('OP-Maske', 'op-maske', 'Bekleidung', 'bekleidung', 9.45, 6164, 'Corona', 436, 794,263) ";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ( 'Schreibtisch' ,'schreibtisch','Wohnen'  ,'wohnen'  ,80.00, 400 ,'Schreibtisch groß'  ,20  ,400  ,210    ) ";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
	}
	
	public void insert2() {
		/*
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		*/
	}
	

}
