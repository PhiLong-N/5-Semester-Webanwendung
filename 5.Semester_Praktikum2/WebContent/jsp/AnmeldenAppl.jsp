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
String username = request.getParameter("username");
String password = request.getParameter("password");
String btnAnmelden = request.getParameter("btnLogin");
if(btnAnmelden==null)btnAnmelden="";

if(btnAnmelden.equals("anmelden")){
	acb.setUsername(username);
	acb.setPassword(password);
	if (acb.checkLogin()){
		acb.login();
		response.sendRedirect("StartView.jsp");
	}
	else {
		response.sendRedirect("AnmeldenView.jsp");
	}
}
else response.sendRedirect("StartView.jsp");
%>

</body>
</html>