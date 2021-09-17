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
		html +="<table>\n";
		html +="	<tr>\n";
		html +="		<td>Bitte melden Sie sich hier an</td>\n";
		html +="		<td><input type='submit' name='btnAnmelden' value='anmelden' /> </td>\n";
		html +="		<td> Oder erstellen Sie hier einen neuen Account.</td>\n";
		html +="		<td><input type='submit' name='btnRegistrieren' value='registrieren' /> </td>\n";
		html +="	</tr>\n";
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
		html+="		<td><input type='submit' name='btnLogout' value='logout' /></td>";
		html+="		<td><input type='submit' name='btnWarenkorb' value='warenkorb' /></td>";
		html+="		<td><input type='submit' name='btnEinstellung' value='einstellung' /></td>";
		html+="</tr>";
		html+="</table>";
		html+="</form>";
		html+="";
		return html;
	}
	

	public String lastArtikel() {
		String html="Zuletzt Angesehen: ";
		html += "<button type='submit' name='btnArtikel' value=<''/>";
		//jsp:getProperty name='acb' property='lastArtikelInt'
		html += "<img src='../img/caipi.jpg' height='100px' />";
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

