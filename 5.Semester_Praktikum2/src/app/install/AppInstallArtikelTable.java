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
		insert1();
	}
	
	public void createArtikelTable() throws SQLException {
		String sql = "CREATE TABLE artikel ("
				+ "		artikelNr 		SERIAL NOT NULL PRIMARY KEY,		"
				+ "		artikel			CHAR(64)	NOT NULL UNIQUE,		"
				+ "		artikelLower	Char(64)	NOT NULL,				"
				+ "     kategorie		CHAR(64),							"
				+ "		kategorieLower	Char(64),							"
				+ "		preis      		DECIMAL(10,2)NOT NULL,         		"
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
		String sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,lager,bewertungsum,bewertunganzahl) VALUES ('Kirschbluetenbaum','kirschbluetenbaum','Garten','garten',9.99,'Kirschblütenbaum mit rosa Blüten. Blütezeit ist April-Mai. An einem sonnigen Standort pflanzen. Eignet sich hervorragend für große Gärten.\r\n"
				+ "',11,227,99)";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Baum erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,lager,bewertungsum,bewertunganzahl) VALUES ('Lilie','lilie','Garten','garten',2.99,'Schöne Lilie für den Garten. Wohlduftend. Auch für Zimmer geeignet.\r\n"
				+ "',5,99,55,15)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Lilie erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,bewertungsum,bewertunganzahl) VALUES ('Apfel','apfel','Garten','garten',0.99,'Leckerer Apfel. Zum Verzehr geeignet.\r\n"
				+ "',46,10,8)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Apfel erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,lager) VALUES ('Schlauch','schlauch','Garten','garten',5.99,'Langer Gartenschlauch zur Bewässerung. 10m lang.\r\n"
				+ "',13,52)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Schlauch erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,lager,bewertungsum,bewertunganzahl) VALUES ('Schaufel','schaufel','Garten','garten',9.99,'Schaufel zum Anheben von Erde. Material: Metall. Höhe: 130cm\r\n"
				+ "',3,10,54,15)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Schaufel erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,clicks,lager,bewertungsum,bewertunganzahl) VALUES ('Nintendo Switch','nintendo switch','Elektronik','elektronik',320.99,'Nintendo Switch Konsole zum spielen von tollen Spielen mit Freunden und Familie.\r\n"
				+ "',99,13,90,20)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Switch erfolgreich hinzugefügt");
		
		sql = "INSERT INTO artikel (artikel,artikelLower,kategorie,kategorieLower,preis,beschreibung,lager,bewertungsum,bewertunganzahl) VALUES ('Turnschuh','turnschuh','Bekleidung','bekleidung',99,'Bequeme Turnschuhe mit hohem Komfort. Atmungsaktiv. Für sowohl Outdoor als auch Indoor Aktivitäten geeignet.\r\n"
				+ "',11,227,99)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Turnschuh erfolgreich hinzugefügt");
	}
	
	
	public void insert1() throws SQLException {

		String sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('Digitalkamera','digitalkamera','Elektronik','elektronik', 189.99,15,'Digitalkamera zum Fotografieren und festhalten von Erinnerungen. Überall mitnehmbar.\r\n"
				+ "',500,243,85)";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('GeForce RTX 2070','geforce rtx 2070','Elektronik', 'elektronik' ,800.00,5000,'Eine Grafikkarte mit eigenem Cooling-System und einer 6GB DDR, Fähig für 4k und VR!\r\n"
				+ "' ,20 ,1500 , 1200 )";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES('Ray Ban Sonnenbrille','ray ban sonnenbrille', 'Accessoire', 'accessoire', 350.49, 55 , 'Ray Ban Sonnenbrille mit tollem Design und hohem UV-Schutz.\r\n"
				+ "', 3,830,197 )";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('Duden', 'duden', 'Bücher', 'bücher', 30.00, 37, 'Der Duden beinhaltet die deutsche Rechtschreibung. Der Duden ist ein Buch mit 148.000 Stichwörtern und beinhaltet 1296 Seiten.\r\n"
				+ "', 124, 1048, 952)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('UNO', 'uno', 'Spielzeug', 'spielzeug', 15.00, 98, 'Eines der beliebtesten Kartenspiele! Freude für Freunde und Familie!\r\n"
				+ "', 56, 865, 647)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ( 'Super Mario Spielzeug' ,'super mario spielzeug' ,'Spielzeug'  ,'spielzeug',100.00,1000 ,'Unser Lieblingsklempner von klein auf! Ein Sammlerstück sowohl für groß, als auch klein!\r\n"
				+ "',100,800,500)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('Kaffeevollautomat', 'kaffeevollautomat', 'Haushalt', 'haushalt', 599.00,78, 'Ein Kaffeevollautomat, der in jede Ecke in eine Küche passt! Sehr schneller Aufbau als auch leichte Nutzung.\r\n"
				+ "', 69, 43, 16)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('Wasserkocher', 'wasserkocher', 'Haushalt', 'haushalt', 32.99, 123, 'Der neuste Wasserkocher auf dem Markt, schönes Design und schnelles kochen.\r\n"
				+ "', 431, 45, 21) ";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('Campingset' ,'campingset','Freizeit' ,'freizeit',40.00, 1000,'Leicht verstaubbares Campingset mit leichtem Aufbau. Hält lange und sehr stabil!\r\n"
				+ "' ,20  ,400  ,250  )";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('Hantelset', 'hantelset', 'Sport', 'sport', 15.15, 8, 'Ein Hantelset mit bis zu 30Kg Gewicht, Kompartibel mit anderen Hantelmarken.\r\n"
				+ "', 15, 73,22)";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ('OP-Maske', 'op-maske', 'Bekleidung', 'bekleidung', 9.45, 6164, 'Zertifizierte Einwegsmaske für Mund und Nasenschutz. Eine Packung beinhaltet 20 Masken.\r\n"
				+ "', 436, 794,263) ";
		prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Artikel hinzugefügt.");
		
		sql = "INSERT INTO artikel (artikel, artikellower, kategorie, kategorielower, preis, clicks , beschreibung, lager, bewertungsum, bewertunganzahl) VALUES ( 'Schreibtisch' ,'schreibtisch','Wohnen'  ,'wohnen'  ,80.00, 400 ,'Stabiler Schreibtisch ohne Hohlräume. Kann Gewicht mit bis zu 70 Kg aushalten.\r\n"
				+ "'  ,20  ,400  ,210    ) ";
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
