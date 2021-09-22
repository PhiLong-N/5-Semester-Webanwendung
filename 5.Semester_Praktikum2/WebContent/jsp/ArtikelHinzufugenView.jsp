<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<a href="StartView.jsp"><img src="../img/LogoCut.png" width="800"/></a>	

<br><br>

<form action="./ArtikelHinzufugenAppl.jsp" name="get">
	
	Bitte achten Sie auf die Groß- und Kleinschreibung!
	<table>
		<tr>
			<td>Artikelname: </td>
			<td><input type="text" name="name" value=""/></td>
		</tr>
		<tr>
			<td>Kategorie: </td>
			<td><input type="text" name="kategorie" value="" /></td>
		</tr>
		<tr>
			<td>Preis: </td>
			<td><input type="text" name="preis" value="" /></td>
			<td>(Bitte nur Zahlen und Punkt verwernden)</td>
		</tr>
		<tr>
			<td>Beschreibung: </td>
			<td><input type="text" name="beschreibung" value="" /></td>
		</tr>
		<tr>
			<td>Lageranzahl: </td>
			<td><input type="text" name="lager" value="" /></td>
			<td>Bitte nur ganze Zahlen eingeben</td>
		</tr>
	</table>
	
	<br>
	
	<button type="submit" name="btnArtikelHinzufugen" value="artikelHinzufugen">Artikel Hinzufügen</button></td>

</form>


<br><br>
<a href="StartView.jsp">Zurück zur Startseite</a>

</body>
</html>