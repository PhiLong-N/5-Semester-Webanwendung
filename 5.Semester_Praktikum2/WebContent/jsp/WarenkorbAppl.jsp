<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="wkb" class="beans.WarenkorbKundeBean" scope="session" />


<%
String anzahlArtikelAndernString = request.getParameter("anzahlArtikelAndern");
int anzahlArtikelAndern = 0;
if(anzahlArtikelAndernString !=null)anzahlArtikelAndern= Integer.parseInt(anzahlArtikelAndernString);

String btnArtikelAndernString = request.getParameter("btnArtikelAndern");
if (btnArtikelAndernString==null)btnArtikelAndernString="";
int btnArtikelAndern=0;
if(!btnArtikelAndernString.equals(""))btnArtikelAndern = Integer.parseInt(btnArtikelAndernString);

String btnArtikelLoschenString = request.getParameter("btnArtikelLoschen");
if (btnArtikelLoschenString==null)btnArtikelLoschenString="";
int btnArtikelLoschen=0;
if (!btnArtikelLoschenString.equals("")){
	btnArtikelLoschen = Integer.parseInt(btnArtikelLoschenString);
}
String btnKasse = request.getParameter("btnKasse");
if (btnKasse==null)btnKasse="";


if(!btnArtikelAndernString.equals("")){
	wkb.changeMenge(anzahlArtikelAndern, btnArtikelAndern);
	response.sendRedirect("WarenkorbView.jsp");
}else if(!btnArtikelLoschenString.equals("")){
	wkb.deletArtikel(btnArtikelLoschen);
	response.sendRedirect("WarenkorbView.jsp");
}else if (btnKasse.equals("kasse")){
	response.sendRedirect("KasseView.jsp");
}else response.sendRedirect("StartView.jsp");

%>



</body>
</html>