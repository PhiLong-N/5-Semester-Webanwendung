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
		
	
	
	<form action="./StartSearchAppl.jsp" method="get">
		<table>
			<tr>
				<td>
					<%
					out.print(ab.searchBarKategorie());
					%>
				</td>
				<td><input type="text" name="searchBar" value="" /></td>
				<td><input type="submit" name="btnSearch" value="search" /></td>
			</tr>
		</table>
	</form>
	
	<br>
	
	<form action="./StartSearchAppl.jsp" method="get">
		Preisklasse: 
		<select name='preisklasse'>
			<option value="alle" >Alle Preisklassen </option>
			<option value="preis>=0 and preis<=20" >0-20</option>
			<option value="preis>=20 and preis<=50" >20-50</option>
			<option value="preis>=50 and preis<=100">50-100</option>
			<option value="preis>=100 and preis<=200">100-200</option>
			<option value="preis>=200 and preis<=500">200-500</option>
		</select>
		
		<button type="submit" name="btnPreisklasse" value="ubernehmen" > �bernehmen </button>
		
	</form>

	
	<br><br><br><br>
	
	

	<form action="./ArtikelSeiteView.jsp" method="get">
		<%
		out.print(ab.getSearchResult());
		%>
	</form>
	<br>
	<br>
	<a href="StartView.jsp">Zur�ck zur Startseite</a>




</body>
</html>