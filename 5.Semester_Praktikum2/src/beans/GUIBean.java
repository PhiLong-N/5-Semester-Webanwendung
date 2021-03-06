package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.install.NoConnectionException;
import app.install.PostgreSQLAccess;

public class GUIBean {
	
	String loschenMsg="";
	
	Connection dbConn;
	
	
	public String falseLogin() {		//StartView falls nicht eingeloggt
		String html="";
		html +="<form action='./StartAccountAppl.jsp' method='get'>\n";
		html +="<table><tr>";
		html +="		<td>Bitte melden Sie sich hier an</td>\n";
		html +="		<td><input type='submit' name='btnAnmelden' value='Anmelden' /> </td>";
		html +="		<td> Oder erstellen Sie hier einen neuen Account.</td>\n";
		html +="		<td><input type='submit' name='btnRegistrieren' value='Registrieren' /> </td>\n";
		html +="</table>\n";
		html +="</form>\n";
		return html;
	}
	
	public String trueLogin(String username) { //falls eingeloggt
		String html="";
		html+="<form action='./StartAccountAppl.jsp' method='get'>";
		html+="<table>";
		html+="<tr>";
		html+="<td>Willkommen "+ username+"<td>";
		html+="		<td><input type='submit' name='btnLogout' value='Logout' /></td>";
		html+="		<td><input type='submit' name='btnWarenkorb' value='Warenkorb' /></td>";
		html+="		<td><input type='submit' name='btnEinstellung' value='Einstellung' /></td>";
		html+="</tr>";
		html+="</table>";
		html+="</form>";
		html+="";
		return html;
	}
	

	public String lastArtikel(int artikelnr) throws NoConnectionException, SQLException { //zuletzt angeschauter Artikel
		String sql = "select artikel from artikel where artikelnr="+artikelnr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		String artikel = dbRes.getString("artikel");
		
		String html="<h2>Zuletzt Angesehen: </h2>";
		html += "<button type='sub"
				+ "mit' name='btnArtikel' value=<''/>";
		html += "<img src='../img/"+artikel.toLowerCase().trim()+".jpg' height='200px' width='200px' /><br>"+artikel;
		html += "</button>";
		return html;
		
	}
	
	
	public String trend() throws NoConnectionException, SQLException {
		String html="<table><tr><td><h2>Im Trend</h2></td></tr> <tr>";
		String sql = "select artikelnr,artikel from artikel order by clicks desc";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		int counter=0;
		while(dbRes.next() & counter<5) {
			counter+=1;
			int artikelnr=dbRes.getInt("artikelnr");
			String artikel = dbRes.getString("artikel");
			html+="<td><button type='submit' name='btnArtikel' value="+artikelnr+"><img src='../img/"+artikel.toLowerCase().trim()+".jpg' height='140px' width='140px' /><br>"+artikel+"</button> </td>";
		}
		
		html+="</tr></table>";
		return html;
	}
	
	
	
	public void setLoschenMsg(String msg) {	//GUI-teil f?r L?schenView
		this.loschenMsg= msg;
	}
	
	public String getLoschenMsg() {
		return loschenMsg;
	}

}

