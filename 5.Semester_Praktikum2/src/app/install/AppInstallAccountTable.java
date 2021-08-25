package app.install;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppInstallAccountTable {
	String email;
	String username;
	String password;
	Boolean admin;
	
	Connection dbConn;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AppInstallAccountTable myApplication = new AppInstallAccountTable();
		myApplication.dbConn = new PostgreSQLAccess().getConnection();
		myApplication.doSomething();
	}
	
	public void doSomething() throws SQLException, ClassNotFoundException{
		
		dropAccountTable();
		createAccountTable();
		insertAdmin();
		insertKunde();
		
		//maxClicks();
		//update();
	}
	
	
	public void createAccountTable() throws SQLException {
		String sql = "CREATE TABLE account ("
				+ "		accNr 		SERIAL 			NOT NULL primary key,	"
				+ "		email   	CHAR(64)     	NOT NULL UNIQUE,		"
				+ "     username  	CHAR(64)		NOT NULL UNIQUE,		"
				+ "		password  	CHAR(64)		NOT NULL,				"
				+ "     admin     	BOOLEAN 		DEFAULT 'FALSE',		"
				+ "		lastvisitInt INTEGER		DEFAULT '0',			"
				+ "		Adresse		CHAR(64)		DEfault '',				"
				+ "		Stadt		CHAR(64)		DEFAULT '',				"
				+ "		plz			INTEGER			DEFAULT '0'				"
				+ "	  )";
		System.out.println(sql);
		PreparedStatement prepStat = dbConn.prepareStatement(sql);
		prepStat.executeUpdate();
		System.out.println("Table account erfolgreich angelegt");
	}
	
	public void dropAccountTable() throws SQLException {
		String sql = "DROP TABLE IF EXISTS account";
		System.out.println(sql);
		dbConn.prepareStatement(sql).executeUpdate();
		System.out.println("Table artikelangebot existiert (jetzt) nicht (mehr)");
	}
	
	public void insertAdmin() throws SQLException {
		String sql = "INSERT INTO account (email,username,password,admin) VALUES('a@admin.de','a.admin','geheim',true)";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Admin hinzugefügt");
	}
	
	public void insertKunde() throws SQLException{
		String sql = "INSERT INTO account (email,username,password) VALUES('b@kunde.de','b.kunde','geheim')";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Gast hinzugefügt");
	}
	
	
	
	
	public void maxClicks() throws NoConnectionException, SQLException {
		String sql="select max(clicks) as clicks from artikel";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		int clicks =dbRes.getInt("clicks");
		sql = "select artikelnr, artikel from artikel where clicks ="+clicks;
		dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		String artikel = dbRes.getString("artikel");
		int artikelnr = dbRes.getInt("artikelnr");
	}
	
	public void update() throws SQLException{
		//String sql = "UPDATE account set password = 'bob' where accnr = 1";
		String sql ="UPDATE account set lastvisit='http://localhost:8080/A_Praktikum_test/jsp/ArtikelSeiteView.jsp?btnArtikel=6' where accnr =1";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Gast hinzugefügt");
	}
	
}
