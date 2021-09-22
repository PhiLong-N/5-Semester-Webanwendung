<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Einstellung</title>
</head>
<body>

<jsp:useBean id="acb" class="beans.AccountBean" scope="session" />

<a href="StartView.jsp"><img src="../img/LogoCut.png" width="800"/></a>
<br><br>
<form action="./EinstellungAppl.jsp" method="get">

<table>
	
	<tr><td><button type="submit" name="btnMeineBestellungen" value="bestellung">Meine Bestellungen</button></td></tr>
	<tr><td><button type="submit" name="btnAdresse" value="adresse">Adresse Ändern</button></td></tr>
	<tr><td><button type="submit" name="btnLoschen" value="loschen">Account Löschen</button></td></tr>

	<%
	if (acb.getAdmin()==true){
		out.print("<tr><td><br><br></td></tr><tr><td><h4>Nur für Adminstratoren</h4></td></tr>");
		out.print("<tr><td><button type='submit' name='btnArtikelLoschen' value='artikelLoschen'>Artikel löschen</button></td></tr>");
		out.print("<tr><td><button type='submit' name='btnLagerAndern' value='lagerAndern'>Artikel Lageranzahl ändern</button></td></tr>");
		out.print("<tr><td><button type='submit' name='btnArtikelHinzufugen' value='artikelHinzufugen'>Neuen Artikel hinzufügen</button></td></tr>");
		out.print("<tr><td><button type='submit' name='btnAdminHinzufugen' value='adminHinzufugen'>Einen neuen Admin hinzufügen</button></td></tr>");
	}
	%>
	
</table>
</form>

<br><br>
<a href="StartView.jsp">Zurück zur Startseite</a>

</body>
</html>