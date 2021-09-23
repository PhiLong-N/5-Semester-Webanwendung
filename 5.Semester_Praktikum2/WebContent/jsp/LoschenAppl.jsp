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
<jsp:useBean id="gb" class="beans.GUIBean" scope="session" />

<%

String btnLoschen = request.getParameter("btnLoschen");
String bar = request.getParameter("bar");

if(btnLoschen==null)btnLoschen="";


if (btnLoschen.equals("loschen")){
	if(bar.equals("BESTAETIGEN")){
		acb.deletAcc();
		gb.setLoschenMsg("");
		response.sendRedirect("StartView.jsp");
	}
	else{
		gb.setLoschenMsg("Falsche Eingabe");
		response.sendRedirect("LoschenView.jsp");
	}
}
else{
	gb.setLoschenMsg("");
	response.sendRedirect("StartView.jsp");
}

%>

</body>
</html>