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
		
<a href="StartView.jsp"><img src="../img/LogoCut.png" width="800"/></a>
		
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
		<td><% out.print("<img src='../img/"+ab.getArtikel().toLowerCase().trim()+".jpg' height='200px' width='200px' />"); %> </td>
		<td valign="bottom"><h2><%out.print(ab.getPreis());%>Euro</h2></td>
	</tr>
</table>
<table>
	<tr><td width='400px'><%out.print(ab.getBeschreibung()); %></td></tr>
	<tr><td><% out.print(ab.bewertung()); %> </td></tr>
</table>

<br><br>

<form action="./ArtikelSeiteAppl.jsp" method="get">
<%
String html="<select name='anzahlArtikel'>";
	int lager = ab.getLager();
	int max=30;
	int start=1;
	if (lager<1)start=0;
	if (lager<30)max=lager;
	for (int i=start;i<=max;i++){
		if (i==1) html += "<option value="+i+" selected>"+i+"</option>";
		else html += "<option value="+i+">"+i+"</option>";	
	}
	html += "</select>";
	out.print(html);
%>
	<button type="submit" name="btnArtikel" value=<%out.print(artikelNr); %> > In den Warenkorb </button>
</form>


<br><br>
<a href="StartView.jsp">Zurück zur Startseite</a>


</body>
</html>