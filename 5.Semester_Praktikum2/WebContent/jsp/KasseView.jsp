<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="acb" class="beans.AccountBean"
		scope="session" />
<jsp:useBean id="wkb" class="beans.WarenkorbKundeBean"
		scope="session" />

<a href="StartView.jsp"><img src="../img/LogoCut.png" width="800"/></a>


<h1>Bitte prüfen Sie ihre Bestellung</h1>

<table>

<td>

<p>Ihre Lieferadresse: </p>

<%
out.print(acb.getUsername());
out.print("<br>");
out.print(acb.getAdresse());
out.print("<br>");
out.print(acb.getStadt());
out.print("<br>");
out.print(acb.getPlz());
%>
<br><br>
<form action="./KasseAppl.jsp" method="get">
	<button type="submit" name="btnLieferadresseAndern" value="lieferadresseandern"> Lieferadresse ändern</button>
</form>

</td>
<td>
<p>Zahlungsart:	 </p>
Rechnung
<br>Die Rechnung kommt mit Ihrer Bestellung.
<br><br><br><br><br>
</td>
</table>
<br>
<%out.print("Gesamtbetrag: "+wkb.endpreis()+" Euro");%>
<br><br>
<form action="./KasseAppl.jsp" method="get">
	<button type="submit" name="btnKaufen" value="jetztKaufen"> Jetzt kaufen</button>
</form>

</body>
</html>