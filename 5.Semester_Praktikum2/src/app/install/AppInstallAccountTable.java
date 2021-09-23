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
		String sql = "INSERT INTO account (email,username,password,admin,adresse,stadt,plz) VALUES('a@admin.de','a.admin','geheim',true,'koenigs str.15','ludwigshafen',67067)";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Admin hinzugefügt");
	}
	
	public void insertKunde() throws SQLException{
		String sql = "INSERT INTO account (email,username,password,adresse, stadt,plz) VALUES('b@kunde.de','b.kunde','geheim','schul 15b','mannheim',67059)";
		PreparedStatement prep = this.dbConn.prepareStatement(sql);
		prep.executeUpdate();
		System.out.println("Gast hinzugefügt");
	}
	
}
