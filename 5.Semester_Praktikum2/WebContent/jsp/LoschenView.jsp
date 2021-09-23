<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="gb" class="beans.GUIBean" scope="session" />

<a href="StartView.jsp"><img src="../img/LogoCut.png" width="800"/></a>
<br><br>

<h4>Löschen Ihres Accounts</h4>
Tippen Sie "BESTAETIGEN" in das Eingabefeld.
<br><br>
<form action="./LoschenAppl.jsp" method="get">	
	
	<input type="text" name="bar" value="" /> <%out.print(gb.getLoschenMsg()); %>
	<br>
	<button type="submit" name="btnLoschen" value="loschen" >Account endgültig Löschen</button>

</form>

	<br><a href="StartView.jsp">Zurück zur Startseite</a>




</body>
</html>