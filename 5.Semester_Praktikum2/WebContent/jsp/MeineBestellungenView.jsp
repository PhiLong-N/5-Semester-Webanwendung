<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meine Bestellungen</title>
</head>
<body>
<jsp:useBean id="abb" class="beans.AllBestellungBean"
		scope="session" />
<jsp:useBean id="acb" class="beans.AccountBean"
		scope="session" />

<a href="StartView.jsp">Zurück zur Startseite</a>
<br>(Artikelbewertung)


<h1>Meine Bestellungen</h1>

	<% int kundennr= acb.getAccNr();
	out.print(abb.test(kundennr));%>
	
	<br><a href="StartView.jsp">Zurück zur Startseite</a>

</body>
</html>