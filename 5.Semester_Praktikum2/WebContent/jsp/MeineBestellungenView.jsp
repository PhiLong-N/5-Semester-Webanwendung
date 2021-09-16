<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meine Bestellungen</title>
</head>
<body>
<jsp:useBean id="abb" class="beans.AllBestellungBean"
		scope="session" />
<jsp:useBean id="acb" class="beans.AccountBean"
		scope="session" />

Datum,Summe bestellnummer, versandadresse
Artikel Menge, Einzelpreis (Artikelbewertung)


<h1>Meine Bestellungen</h1>

	<table>
		<tr>
			<td>
				<% int kundennr= acb.getAccNr();
				Timestamp time=abb.getBestellung(kundennr);
				out.print(time);
				%>
			</td>
			<td></td><td></td><td></td><td></td>
			<td>
				<% 
				double summe=abb.getSumme(1);/////bestellnr/////////////1 durch variabel ersetzten
				out.print("Gesamtbetrag: "+summe+" Euro");
				%>
			</td>
			<td></td><td></td><td></td><td></td>
			<td>
				<%out.print("Bestellnummer: "+158);/////////////1 durch bestellnr %>
			</td>
		</tr>
		
		<tr>
			<td>
				phi <br>
				Koenigsbacherstr.76<br>
				ludwigshafen<br>
				67067<br>
			</td>
		</tr>
		
		<tr>
			<td>
			Artikel Menge, Einzelpreis (Artikelbewertung)
			</td>
		</tr>
		
	
	</table>




</body>
</html>