package beans;

public class GUIBean {
	
	
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

}

