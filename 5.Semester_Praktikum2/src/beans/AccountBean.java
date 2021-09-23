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
	Boolean admin=false;
	int lastArtikelInt=0;
	String adresse;
	String stadt;
	int plz;

	Boolean login=false;
	String emailMsg="";
	String usernameMsg="";
	String passwortMsg="";

	

	Connection dbConn;

	public boolean insertAdmin() throws SQLException {
		boolean ergebnis=checkIfExist(); // true = existiert bereits
		if (ergebnis)
			System.out.println("nope");
		else {
			String sql = "INSERT INTO account (email,username,password,admin,adresse,stadt,plz) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
			prep.setString(1, this.email);
			prep.setString(2, this.username);
			prep.setString(3, this.password);
			prep.setBoolean(4, true);
			prep.setString(5, this.adresse);
			prep.setString(6, this.stadt);
			prep.setInt(7, this.plz);
			prep.executeUpdate();
			System.out.println("Admin hinzugefügt");
		}
		return ergebnis;
	}

	public boolean insertKunde() throws SQLException {
		boolean ergebnis=checkIfExist(); // true = existiert bereits
		if (ergebnis)
			System.out.println("Wurde nicht hinzugefügt");
		else {
			String sql = "INSERT INTO account (email,username,password,adresse,stadt,plz) VALUES(?,?,?,?,?,?)";
			PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
			prep.setString(1, this.email);
			prep.setString(2, this.username);
			prep.setString(3, this.password);
			prep.setString(4, this.adresse);
			prep.setString(5, this.stadt);
			prep.setInt(6, this.plz);
			prep.executeUpdate();
			System.out.println("Gast hinzugefügt");
		}
		return ergebnis;
	}
	
	public void deletAcc() throws NoConnectionException, SQLException {
		String sql = "delete from account where accnr="+this.accNr;
		PreparedStatement prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		
		sql = "delete from warenkorbkunde where kundennr="+this.accNr;
		prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		
		sql = "delete from allbestellung where kundennr="+this.accNr;
		prep = new PostgreSQLAccess().getConnection().prepareStatement(sql);
		prep.executeUpdate();
		
		logout();
	}
	
	public boolean checkIfExist() throws NoConnectionException, SQLException {
		//wenn true existiert email oder username
		boolean check = false;
		
		String sql = "select email from account where email = '" + this.email + "'";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		if (dbRes.next() == true) {
			check = true;
			setEmailMsg("E-mail Adresse existiert bereits.");
		}else setEmailMsg("");
		sql = "select username from account where username = '" + this.username + "'";
		dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		if (dbRes.next() == true) {
			check = true;
			setUsernameMsg("Benutzername existiert bereits.");
		}
		else setUsernameMsg("");
		
		return check;
	}
	
	public boolean checkLogin() throws NoConnectionException, SQLException {
		String sql = "select username,password from account where username = '" + this.username + "'";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		if (dbRes.next() == true) {
			return this.password.equals(dbRes.getString("password").trim());
		}
		else return false;
	}
	
	public void login() throws NoConnectionException, SQLException {
		String sql = "select accNr,email,admin,lastvisitInt,adresse,stadt,plz from account where username = '" + this.username + "'";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		setAccNr(dbRes.getInt("accnr"));
		setEmail(dbRes.getString("email").trim());
		setAdmin(dbRes.getBoolean("admin"));
		int merker = dbRes.getInt("lastvisitInt");
		if (merker !=0)setLastArtikelInt(merker);
		setAdresse(dbRes.getString("adresse"));
		setStadt(dbRes.getString("stadt"));
		setPlz(dbRes.getInt("plz"));
		setLogin(true);
	}
	
	public void refreshData() throws NoConnectionException, SQLException { //wird verwendet wenn Lieferadresse bei der Bestellung verändert wird
		String sql = "select username,adresse,stadt,plz from account where accnr =" + this.accNr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		setUsername(dbRes.getString("username"));
		setAdresse(dbRes.getString("adresse"));
		setStadt(dbRes.getString("stadt"));
		setPlz(dbRes.getInt("plz"));
	}
	
	public void logout() throws NoConnectionException, SQLException {
		setAccNr(0);
		setEmail(null);
		setPassword(null);
		setAdmin(false);
		setLogin(false);
		setLastArtikelInt(0);
		setAdresse(null);
		setStadt(null);
		setPlz(0);
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
		setAdresse(adresse);
		setStadt(stadt);
		setPlz(plz);
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
	
	public String getEmailMsg() {
		return emailMsg;
	}

	public void setEmailMsg(String emailMsg) {
		this.emailMsg = emailMsg;
	}

	public String getUsernameMsg() {
		return usernameMsg;
	}

	public void setUsernameMsg(String usernameMsg) {
		this.usernameMsg = usernameMsg;
	}

	public String getPasswortMsg() {
		return passwortMsg;
	}

	public void setPasswortMsg(String passwortMsg) {
		this.passwortMsg = passwortMsg;
	}

}
