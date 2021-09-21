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
String btnArtikelLoschen = request.getParameter("btnArtikelLoschen");
String btnSearch = request.getParameter("btnSearch");
String searchBar = request.getParameter("searchBar");

if (btnSearch==null)btnSearch="";
if (btnArtikelLoschen==null)btnArtikelLoschen="";


if(btnSearch.equals("search")){
	ab.setDeleteArtikel(searchBar);
	response.sendRedirect("ArtikelLoschenView.jsp");
}
else if(!btnArtikelLoschen.equals("")){
	int artikelnr= Integer.parseInt(btnArtikelLoschen);
	ab.deleteArtikel(artikelnr);
	ab.setDeleteArtikel(searchBar);
	acb.setLastArtikelInt(0);
	response.sendRedirect("ArtikelLoschenView.jsp");
}
else response.sendRedirect("StartView.jsp");
%>

</body>
</html>