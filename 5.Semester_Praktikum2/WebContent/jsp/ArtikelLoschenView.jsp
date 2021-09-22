<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="ab" class="beans.ArtikelBean" scope="session" />

<a href="StartView.jsp"><img src="../img/LogoCut.png" width="800"/></a>

<br>
	<a href="EinstellungView.jsp">Zurück zu den Einstellungen</a>
	<br><br>
	

	<form action="./ArtikelLoschenAppl.jsp" method="get">
		<table>
			<tr>
			
				<td><input type="text" name="searchBar" value="" /></td>
				<td><input type="submit" name="btnSearch" value="search" /></td>
			</tr>

	<%out.print(ab.getSearchResult());
	%>
	
			</table>
	
		</form>
	
	<br>
	<a href="EinstellungView.jsp">Zurück zu den Einstellungen</a>



</body>
</html>