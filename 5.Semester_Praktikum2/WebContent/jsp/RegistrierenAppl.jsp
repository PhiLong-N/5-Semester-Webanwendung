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
String adresse = request.getParameter("adresse");
String stadt = request.getParameter("stadt");
String plzString = request.getParameter("plz");

String btnRegistrieren = request.getParameter("btnRegistrieren");
if(btnRegistrieren==null)btnRegistrieren="";

acb.setEmail(email);
acb.setUsername(username);
acb.setAdresse(adresse);
acb.setStadt(stadt);

boolean check= true;
try {
	int plz = Integer.parseInt(plzString);
	if (plz>=100000 || plz<=9999)check = false;
	else acb.setPlz(plz);
}catch(Exception e){check = false;
}

if (!password.equals(passwordwdh) || check==false ||username.equals("") || email.equals("") ||stadt.equals("") || adresse.equals("")){
	response.sendRedirect("RegistrierenView.jsp");
}
else if(btnRegistrieren.equals("registrieren")){
	acb.setPassword(password);
	if (acb.getAdmin())
		if (acb.insertAdmin()) response.sendRedirect("RegistrierenView.jsp");
		else {
			acb.login();
			response.sendRedirect("StartView.jsp");
		}
	else{
		if (acb.insertKunde()) response.sendRedirect("RegistrierenView.jsp");
		else {
			acb.login();
			response.sendRedirect("StartView.jsp");
		}
	}
}
else response.sendRedirect("StartView.jsp");


%>

</body>
</html>