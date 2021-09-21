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


<form action="./EinstellungAppl.jsp" method="get">

	<button type="submit" name="btnAdresse" value="adresse">Adresse Ändern</button>
	<button type="submit" name="btnLoschen" value="loschen">Account Löschen</button>
	<button type="submit" name="btnMeineBestellungen" value="bestellung">Meine Bestellungen</button>
	<%
	if (acb.getAdmin()==true){
		out.print("<button type='submit' name='btnArtikelLoschen' value='artikelLoschen'>Artikel löschen</button>");
		out.print("<button type='submit' name='btnLagerAndern' value='lagerAndern'>Artikel Lageranzahl ändern</button>");
	}
	%>
	
</form>

<br><br>
<a href="StartView.jsp">Zurück zur Startseite</a>

</body>
</html>