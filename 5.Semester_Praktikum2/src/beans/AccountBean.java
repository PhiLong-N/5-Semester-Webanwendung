package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.install.NoConnectionException;
import app.install.PostgreSQLAccess;

public class AccountBean {
	int accNr;
	String email;
	String username;
	String password;
	Boolean admin;
	int lastArtikelInt=0;
	String adresse;
	String stadt;
	int plz;

	Boolean login=false;

	Connection dbConn;

	public void insertAdmin() throws SQLException {
		if (checkIfExist())
			System.out.println("nope");
		else {
			String sql = "INSERT INTO account (email,username,password,admin) VALUES(?,?,?,true)";
			PreparedStatement prep = this.dbConn.prepareStatement(sql);
			prep.setString(1, this.email);
			prep.setString(2, this.username);
			prep.setString(3, this.password);
			prep.executeUpdate();
			System.out.println("Admin hinzugefügt");
		}
	}

	public boolean insertKunde() throws SQLException {
		boolean ergebmis=checkIfExist(); // true = existiert bereits
		if (ergebmis)
			System.out.println("nope");
		else {
			String sql = "INSERT INTO account (email,username,password) VALUES(?,?,?)";
			PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
			prep.setString(1, this.email);
			prep.setString(2, this.username);
			prep.setString(3, this.password);
			prep.executeUpdate();
			System.out.println("Gast hinzugefügt");
		}
		return ergebmis;
	}
	
	public void deletAcc() throws NoConnectionException, SQLException {
		String sql = "delete from account where accnr="+this.accNr;
		PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		
		sql = "delete from warenkorbkunde where kundennr="+this.accNr;
		prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		
		logout();
		
	}
	
	public void checkIfInsertDataOk() {
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

	public boolean checkIfExist() throws NoConnectionException, SQLException {
		//wenn true existiert email oder password (falls kein fehler wenn dann beide gleichzeitig oder gar keins)
		boolean check = false;
		
		String sql = "select email from account where email = '" + this.email + "'";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		if (dbRes.next() == true)
			check = true;
		sql = "select username from account where username = '" + this.username + "'";
		dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		if (dbRes.next() == true)
			check = true;

		return check;
	}
	
	public boolean checkLogin() throws NoConnectionException, SQLException {
		String sql = "select username,password from account where username = '" + this.username + "'";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		if (dbRes.next() == true) {
			return this.password.equals(dbRes.getString("password").trim());
		}
		else { 
			return false;
			}
	}
	
	public void login() throws NoConnectionException, SQLException {
		String sql = "select accNr,email,admin,lastvisitInt from account where username = '" + this.username + "'";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		setAccNr(dbRes.getInt("accnr"));
		setEmail(dbRes.getString("email").trim());
		setAdmin(dbRes.getBoolean("admin"));
		int merker = dbRes.getInt("lastvisitInt");
		if (merker !=0)setLastArtikelInt(merker);
		setLogin(true);
	}
	
	public void logout() throws NoConnectionException, SQLException {
		setAccNr(0);
		setEmail(null);
		setPassword(null);
		setAdmin(null);
		setLogin(false);
		setLastArtikelInt(0);
	}
	

	public void setLastArtikelInt(int artikel) throws NoConnectionException, SQLException {
		this.lastArtikelInt=artikel;
		if (login) {
			String sql ="UPDATE account set lastvisitInt='"+this.lastArtikelInt+"' where accnr ="+this.accNr;
			PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
			prep.executeUpdate();
		}
	}
	
	public boolean changeAdresse(String adresse, String stadt, int plz) throws NoConnectionException, SQLException {
		boolean check=false;
		if(adresse.equals("") || stadt.equals("") || plz>99999 || plz<=9999)return check;
		String sql = "update account set adresse ='"+adresse+"', stadt='"+stadt+"', plz="+plz+" where accnr ="+this.accNr;
		PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		check=true;
		return check;
	}
	
	
	
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getStadt() {
		return stadt;
	}

	public void setStadt(String stadt) {
		this.stadt = stadt;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public int getLastArtikelInt() {
		return this.lastArtikelInt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public int getAccNr() {
		return accNr;
	}

	public void setAccNr(int accNr) {
		this.accNr = accNr;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	public Boolean getLogin() {
		return login;
	}

	public void setLogin(Boolean login) {
		this.login = login;
	}

}
