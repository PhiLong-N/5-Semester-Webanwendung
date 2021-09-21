<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<a href="StartView.jsp"><img src="../img/LogoCut.png" width="655"/></a>

<form action="./LieferadresseAndernAppl.jsp" method="get">

<table>
	<tr>
		<td>Name: </td>
		<td><input type="text" name="name" value="" /></td>
	</tr>
	<tr>
		<td>Adresse: </td>
		<td><input type="text" name="adresse" value="" /></td>
	</tr>
	<tr>
		<td>Stadt: </td>
		<td><input type="text" name="stadt" value="" /></td>
	</tr>
	<tr>
		<td>Plz: </td>
		<td><input type="text" name="plz" value="" /></td>
	</tr>
	<tr>
		<td><input type="submit" name="btnAdresseAndern" value="adresseAndern" /></td>
	</tr>	
	

</table>

</body>
</html>