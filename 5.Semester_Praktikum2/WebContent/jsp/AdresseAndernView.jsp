<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adresse Ändern</title>
</head>
<body>



<form action="./AdresseAndernAppl.jsp" method="get">

<table>
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

</form>

<br><br>
<a href="StartView.jsp">Zurück zur Startseite</a>




</body>
</html>