package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.install.NoConnectionException;
import app.install.PostgreSQLAccess;

public class GUIBean {
	
	Connection dbConn;
	
	
	public String falseLogin() {
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
	
	public String trueLogin(String username) {
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
	

	public String lastArtikel(int artikelnr) throws NoConnectionException, SQLException {
		String sql = "select artikel from artikel where artikelnr="+artikelnr;
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		dbRes.next();
		String artikel = dbRes.getString("artikel");
		
		String html="Zuletzt Angesehen: ";
		html += "<button type='sub"
				+ "mit' name='btnArtikel' value=<''/>";
		html += "<img src='../img/caipi.jpg' height='100px' /><br>"+artikel;
		html += "</button>";
		return html;
	}
	
	
	public String trend() throws NoConnectionException, SQLException {
		String html="<table><tr><td>Im Trend</td></tr> <tr>";
		String sql = "select artikelnr,artikel from artikel order by clicks desc";
		ResultSet dbRes = new PostgreSQLAccess().getConnection().prepareStatement(sql).executeQuery();
		int counter=0;
		while(dbRes.next() & counter<5) {
			counter+=1;
			int artikelnr=dbRes.getInt("artikelnr");
			String artikel = dbRes.getString("artikel");
			html+="<td><button type='submit' name='btnArtikel' value="+artikelnr+"><img src='../img/caipi.jpg' height='100px' /><br>"+artikel+"</button> </td>";
		}
		
		html+="</tr></table>";
		return html;
	}

}

