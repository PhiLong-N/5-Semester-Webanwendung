<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insight Shop</title>
</head>
<body>

<jsp:useBean id="acb" class="beans.AccountBean" scope="session" />
<jsp:useBean id="gb" class="beans.GUIBean" scope="session" />
<jsp:useBean id="ab" class="beans.ArtikelBean" scope="session" />

<%	//resetten von GUI-Msg
gb.setLoschenMsg("");
acb.setEmailMsg("");
acb.setUsernameMsg("");
acb.setPasswortMsg("");

if(acb.getLogin()==true)acb.refreshData();
%>			
	
<a href="StartView.jsp"><img src="../img/LogoCut.png" width="800"/></a><br><br>
	
<%
	if (acb.getLogin()) out.print(gb.trueLogin(acb.getUsername()));
	else out.print(gb.falseLogin());
%>

<form action="./StartSearchAppl.jsp" method="get">
	<table>
		<tr>
			<td><%out.print(ab.searchBarKategorie());%></td>
			<td><input type="text" name="searchBar" value="" size="70" /></td>
			<td><button type="submit" name="btnSearch" value="search" />Suchen</button></td>
		</tr>
	</table>
</form>

<table>
	<tr>
		<td>
			<form action='./ArtikelSeiteView.jsp' method='get'>
				<%
				if (acb.getLastArtikelInt() == 0) {
					out.print("<h2>Meist Angeschaut: </h2>");
					int maxArtikel = ab.maxClicksArtikel();
					String artikel = ab.maxClicksArtikelName(maxArtikel);
					String html="";
					html+= "<button type='submit' name='btnArtikel' value="+maxArtikel+" />";
					html+=	"<img src='../img/"+artikel.toLowerCase().trim()+".jpg' width='200' height='200px' /> <br>"+artikel;
					html+= "</button>";
					out.print(html);
				} else
					out.print(gb.lastArtikel(acb.getLastArtikelInt() ));
				%>
			</form>
		</td>
	</tr>
</table>	
<br><br>

<form action="./ArtikelSeiteView.jsp" method="get">
	<%out.print(gb.trend()); %>
</form>
	
</body>
</html>