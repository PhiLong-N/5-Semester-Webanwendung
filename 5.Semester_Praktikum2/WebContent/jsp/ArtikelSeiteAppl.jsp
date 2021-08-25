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
<jsp:useBean id="wkb" class="beans.WarenkorbKundeBean" scope="session" />
<jsp:useBean id="ab" class="beans.ArtikelBean" scope="session" />

<%
String stringAnzahlArtikel = request.getParameter("anzahlArtikel");
int anzahlArtikel = Integer.parseInt(stringAnzahlArtikel);
String stringArtikel = request.getParameter("btnArtikel");
int btnArtikel= Integer.parseInt(stringArtikel);
if (stringArtikel==null)stringArtikel="";

if (!stringArtikel.equals("")){
	if (acb.getLogin()){
		wkb.insertWarenkorb(acb.getAccNr(),btnArtikel,anzahlArtikel,ab.getPreis());
		response.sendRedirect("WarenkorbView.jsp");
	}
	else {
		response.sendRedirect("AnmeldenView.jsp");	
	}
}
else response.sendRedirect("StartView.jsp");
%>

</body>
</html>