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
String email = request.getParameter("email");
String username = request.getParameter("username");
String password = request.getParameter("password");
String passwordwdh = request.getParameter("passwordwdh");
String btnRegistrieren = request.getParameter("btnRegistrieren");
if(btnRegistrieren==null)btnRegistrieren="";

acb.setEmail(email);
acb.setUsername(username);
if (!password.equals(passwordwdh)){
	response.sendRedirect("RegistrierenView.jsp");
}

else if(btnRegistrieren.equals("registrieren")){
	acb.setPassword(password);
	if (acb.insertKunde())response.sendRedirect("RegistrierenView.jsp");
	else {
		acb.login();
		response.sendRedirect("StartView.jsp");
	}
}
else response.sendRedirect("StartView.jsp");


%>

</body>
</html>