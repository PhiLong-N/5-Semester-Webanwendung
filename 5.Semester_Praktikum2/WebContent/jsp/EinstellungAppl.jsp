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
String btnAdresse = request.getParameter("btnAdresse");
String btnLoschen = request.getParameter("btnLoschen");


if(btnAdresse==null)btnAdresse="";
if(btnLoschen==null)btnLoschen="";


if (btnAdresse.equals("adresse")){
	response.sendRedirect("AdresseAndernView.jsp");
}
else if(btnLoschen.equals("loschen")){
	acb.deletAcc();
	response.sendRedirect("StartView.jsp");
}
else response.sendRedirect("StartView.jsp");

%>

</body>
</html>