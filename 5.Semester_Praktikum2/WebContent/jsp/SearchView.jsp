<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="ab" class="beans.ArtikelBean"
		scope="session" />

	<form action="./ArtikelSeiteView.jsp" method="get">
		<%
		out.print(ab.getSearchResult());
		%>
	</form>
	<br>
	<br>
	<a href="StartView.jsp">Zurück zur Startseite</a>




</body>
</html>