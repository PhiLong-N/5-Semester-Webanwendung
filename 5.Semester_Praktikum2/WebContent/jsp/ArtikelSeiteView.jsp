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
<jsp:useBean id="acb" class="beans.AccountBean" scope="session" />
		
		
<%
String btnArtikel = request.getParameter("btnArtikel");
int artikelNr;
try{
	artikelNr = Integer.parseInt(btnArtikel);
}catch(Exception e){artikelNr= acb.getLastArtikelInt();
}
if (btnArtikel==null) response.sendRedirect("./StartView.jsp");
acb.setLastArtikelInt(artikelNr);
ab.increaseClick(artikelNr);
ab.getAllInfo(artikelNr);
out.print("<h1>"+ ab.getArtikel()+"</h1>");
%>

<table>
	<tr>
		<td>bild</td>
		<td><%out.print(ab.getPreis());%>Euro</td>
	</tr>
	<tr>
		<td><%out.print(ab.getBeschreibung()); %></td>
	</tr>
</table>

<form action="./ArtikelSeiteAppl.jsp" method="get">
<%
String html="<select name='anzahlArtikel'>";
	for (int i=1;i<=30;i++){
		html += "<option value="+i+">"+i+"</option>";	
	}
	html += "</select>";
	out.print(html);
%>
	<button type="submit" name="btnArtikel" value=<%out.print(artikelNr); %> > in den warenkorb </button>
</form>

<a href="StartView.jsp">Zurück zur Startseite</a>


</body>
</html>