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


<%
String btnArtikelAnzahl = request.getParameter("btnArtikelAnzahl");
String btnSearch = request.getParameter("btnSearch");
String searchBar = request.getParameter("searchBar");


if (btnSearch==null)btnSearch="";
if (btnArtikelAnzahl==null)btnArtikelAnzahl="";

if(btnSearch.equals("search")){
	ab.setLagerArtikel(searchBar);
	response.sendRedirect("LagerAnzahlAndernView.jsp");
}
else if (!btnArtikelAnzahl.equals("")){
	int artikelNr= Integer.parseInt(btnArtikelAnzahl);
	
	try{
		String lagerNeuString = request.getParameter("lagerNeu"+artikelNr);
		int lagerNeu = Integer.parseInt(lagerNeuString);
		ab.updateLager(artikelNr,lagerNeu);
	}catch(Exception e){
	}
	//anzahl ändern
	ab.setLagerArtikel(searchBar);
	response.sendRedirect("LagerAnzahlAndernView.jsp");
}
else response.sendRedirect("StartView.jsp");

%>

</body>
</html>