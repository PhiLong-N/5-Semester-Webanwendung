<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="acb" class="beans.AccountBean" scope="session" />

<%
String adresse = request.getParameter("adresse");
String stadt = request.getParameter("stadt");
String plzString = request.getParameter("plz");
if (plzString=="")plzString="0";
int plz = Integer.parseInt(plzString);
String btnAdresseAndern = request.getParameter("btnAdresseAndern");

if (btnAdresseAndern==null)btnAdresseAndern="";

if(btnAdresseAndern.equals("adresseAndern")){
	boolean change= acb.changeAdresse(adresse, stadt, plz);
	if (change==false){
		response.sendRedirect("AdresseAndernView.jsp");
	}
	else{
		response.sendRedirect("StartView.jsp");
	}
}
else response.sendRedirect("StartView.jsp");
%>

</body>
</html>