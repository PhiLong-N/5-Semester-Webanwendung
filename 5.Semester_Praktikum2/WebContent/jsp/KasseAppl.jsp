<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="wkb" class="beans.WarenkorbKundeBean"
		scope="session" />
<jsp:useBean id="abb" class="beans.AllBestellungBean"
		scope="session" />
<jsp:useBean id="acb" class="beans.AccountBean" 
		scope="session" />

<%
String btnLieferadresseAndern = request.getParameter("btnLieferadresseAndern");
if (btnLieferadresseAndern == null)btnLieferadresseAndern="";
String btnKaufen = request.getParameter("btnKaufen");
if (btnKaufen==null)btnKaufen="";


if (btnLieferadresseAndern.equals("lieferadresseandern")){
	response.sendRedirect("LieferadresseAndernView.jsp");
}else if (btnKaufen.equals("jetztKaufen")){
	///////////////////////////
	int kundenNr=acb.getAccNr();
	abb.bestellung(kundenNr,acb.getUsername(),acb.getAdresse(),acb.getStadt(),acb.getPlz());
	
	response.sendRedirect("KaufbestatigungView.jsp");
}else response.sendRedirect("StartView.jsp");

%>

</body>
</html>