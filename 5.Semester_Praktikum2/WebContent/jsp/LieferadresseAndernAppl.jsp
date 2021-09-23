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
String btnAdresseAndern = request.getParameter("btnAdresseAndern");
String name = request.getParameter("name");
String adresse = request.getParameter("adresse");
String stadt = request.getParameter("stadt");
String plzString = request.getParameter("plz");
if (plzString=="")plzString="0";
int plz = Integer.parseInt(plzString);

if (btnAdresseAndern == null)btnAdresseAndern="";

if(btnAdresseAndern.equals("adresseAndern")){
	if(name.equals("") || adresse.equals("") || stadt.equals("") || plz>99999 || plz<=9999){
		response.sendRedirect("LieferadresseAndernView.jsp");
	}else{
		acb.setUsername(name);
		acb.setAdresse(adresse);
		acb.setStadt(stadt);
		acb.setPlz(plz);
		response.sendRedirect("KasseView.jsp");	
	}
}else response.sendRedirect("StartView.jsp");
%>

</body>
</html>