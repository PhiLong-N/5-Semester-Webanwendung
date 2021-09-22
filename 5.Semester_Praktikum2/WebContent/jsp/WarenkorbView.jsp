<%@page import="java.util.ArrayList"%>
<%@page import="beans.WarenkorbKundeBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Warenkorb</title>
</head>
<body>
	<jsp:useBean id="wkb" class="beans.WarenkorbKundeBean"
		scope="session" />
	<jsp:useBean id="acb" class="beans.AccountBean"
		scope="session" />
	<jsp:useBean id="ab" class="beans.ArtikelBean"
		scope="session" />
		
<a href="StartView.jsp"><img src="../img/LogoCut.png" width="800"/></a>		
<br><br>
	<h3>Ihr Warenkorb:</h3>
	<br><br>	
	
	<%
	out.print(wkb.getWarenKorbKunde(acb.getAccNr()));
	%>
	
	<form action= "./WarenkorbAppl.jsp" method="get">
		<button type="submit" name="btnKasse" value="kasse">Zur Kasse</button>
	</form>
		
	<br>
	<br>
	<a href="StartView.jsp">Zurück zur Startseite</a>

</body>
</html>