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

<%
String kategorieDropdown = request.getParameter("kategorieDropdown");
String searchBar = request.getParameter("searchBar");
String btnSearch = request.getParameter("btnSearch");
if (btnSearch==null)btnSearch="";

String preisklasse = request.getParameter("preisklasse");
String btnPreisklasse= request.getParameter("btnPreisklasse");
if (btnPreisklasse==null)btnPreisklasse="";

if (btnSearch.equals("search")){
	if (kategorieDropdown.equals("Alle")){
		ab.searchAll(searchBar);
	}
	else{
		ab.searchKategorie(searchBar, kategorieDropdown);
	}
	response.sendRedirect("SearchView.jsp");
}
else if(btnPreisklasse.equals("ubernehmen")){
	ab.searchPreisklasse(preisklasse);
	response.sendRedirect("SearchView.jsp");
}
else response.sendRedirect("StartView.jsp");


%>

</body>
</html>