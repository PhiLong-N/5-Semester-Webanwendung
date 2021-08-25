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
String btnAnmelden = request.getParameter("btnAnmelden");
String btnRegistrieren = request.getParameter("btnRegistrieren");
String btnLogout =request.getParameter("btnLogout");
String btnWarenkorb = request.getParameter("btnWarenkorb");
String btnEinstellung = request.getParameter("btnEinstellung");
if (btnAnmelden==null)btnAnmelden="";
if (btnRegistrieren==null)btnRegistrieren="";
if (btnLogout==null)btnLogout="";
if (btnWarenkorb==null)btnWarenkorb="";
if (btnEinstellung==null)btnEinstellung="";

if (btnAnmelden.equals("anmelden")){
	response.sendRedirect("AnmeldenView.jsp");
}
else if (btnRegistrieren.equals("registrieren")){
	acb.setEmail("");
	acb.setUsername("");
	response.sendRedirect("RegistrierenView.jsp");
}
else if(btnLogout.equals("logout")){
	acb.logout();
	response.sendRedirect("StartView.jsp");
}
else if (btnWarenkorb.equals("warenkorb")){
	response.sendRedirect("WarenkorbView.jsp");	
}
else if(btnEinstellung.equals("einstellung")){
	response.sendRedirect("EinstellungView.jsp");
}
else response.sendRedirect("StartView.jsp");
%>

</body>
</html>