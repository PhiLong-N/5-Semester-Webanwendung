<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Meine Bestellungen</title>
<link rel="stylesheet" type="text/css" href="../css/GUI.css" />
</head>
<body>
<jsp:useBean id="abb" class="beans.AllBestellungBean"
		scope="session" />
<jsp:useBean id="acb" class="beans.AccountBean"
		scope="session" />
		
<a href="StartView.jsp"><img src="../img/LogoCut.png" width="800"/></a>		



<h1>Meine Bestellungen</h1>

	<% int kundennr= acb.getAccNr();
	out.print(abb.getAllBestellung(kundennr));%>
	
	
	<br><a href="StartView.jsp">Zurück zur Startseite</a>

</body>
</html>