<%@page import="java.util.ArrayList"%>
<%@page import="beans.ListArtikel"%>
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

	Ihr Warenkorb:
	<br>
	<br>
	<br>


	<table>
		<%
	ArrayList<ListArtikel> coll = wkb.getWarenkorbKunde(acb.getAccNr());
	for (ListArtikel o : coll) {
		out.print("<form action='./WarenkorbAppl.jsp' method='get'>");
		int position = o.getPosition();
		ab.getAllInfo(o.getArtikelNr());
		out.print("<tr><td rowspan='4'><a href='http://localhost:8080/5.Semester_Praktikum/jsp/ArtikelSeiteView.jsp?btnArtikel="+o.getArtikelNr()+"'> <img src='../img/caipi.jpg' height='100px' /></a></td>");
		
		
		out.print("<tr><td>"+ab.getArtikel()+" \b </td>");
		
		//out.print("<td>Menge: "+o.getMenge() +" </td>");
		
		String html="<select name='anzahlArtikelAndern'>";
		if (o.getMenge()>30){
			for (int i=1;i<=o.getMenge()+1;i++){
				if (i==o.getMenge()) html += "<option value="+i+" selected>"+i+"</option>";
				else html += "<option value="+i+">"+i+"</option>";	
			}
		}else{
			for (int i=1;i<=30;i++){
				if (i==o.getMenge()) html += "<option value="+i+" selected>"+i+"</option>";
				else html += "<option value="+i+">"+i+"</option>";	
			}
		}
		html += "</select>";
		out.print("<td>Menge: "+html +" ");
		out.print("<button type='submit' name='btnArtikelAndern' value="+o.getArtikelNr()+">Menge ändern</button> </td>");
		out.print("<td><button type='submit' name='btnArtikelLoschen' value="+o.getArtikelNr()+">Artikel Löschen</button> </td>");
		out.print("<td> </td>");
		
		out.print("</tr>");
		out.print("</form>");
		
		out.print("<tr>");
		out.print("<td> </td>");
		out.print("<td> Einzelpreis: "+o.getEinzelpreis()+" Euro</td>");
		out.print("</tr>");
		
		out.print("<tr>");
		out.print("<td> </td>");
		out.print("<td> Gesamtpreis: "+o.getGesamtpreis()+" Euro </td>");
		out.print("</tr>");
		
	}	
	%>
	</table>
	
	<br><br><br>
	
	<%
		out.print("Gesamtbetrag: "+wkb.endpreis()+" Euro");
	%>
	<br><br>
	<form action= "./WarenkorbAppl.jsp" method="get">
		<button type="submit" name="btnKasse" value="kasse">Zur Kasse</button>
	</form>
		
	


	<br>
	<br>
	<a href="StartView.jsp">Zurück zur Startseite</a>



</body>
</html>